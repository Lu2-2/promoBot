package promobot.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.cdimascio.dotenv.Dotenv;
import promobot.model.AliexpressRefreshToken;
import promobot.model.AliexpressTokenResponse;
import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class AliexpressService {

    public static final String CHARSET_UTF8 = "UTF-8";
    public static final String SIGN_METHOD_SHA256 = "sha256";
    public static final String SIGN_METHOD_HMAC_SHA256 = "HmacSHA256";
    private Dotenv dotenv = Dotenv.load();
    private String appSecret = dotenv.get("ALIEXPRESS_APP_SECRET");
    private String appKey = dotenv.get("ALIEXPRESS_APP_KEY");
    private HttpClient client = HttpClient.newHttpClient();

    public AliexpressTokenResponse gerarToken(String code) throws IOException{
        var timestamp = Long.toString(System.currentTimeMillis());

        HashMap<String, String> request = new HashMap<>();
        request.put("app_key", appKey);
        request.put("timestamp", timestamp);
        request.put("sign_method", SIGN_METHOD_SHA256);
        request.put("code", code);

        var sign = signApiRequest(request, SIGN_METHOD_SHA256, "/auth/token/create");
        request.put("sign", sign);

        StringBuilder strbuild = new StringBuilder();
        for(HashMap.Entry<String,String> entry: request.entrySet()){
            String key = entry.getKey();
            String value = entry.getValue();
            strbuild.append(key + "=" + value + "&");
        }

        strbuild.deleteCharAt(strbuild.length() - 1);

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create("https://api-sg.aliexpress.com/rest/auth/token/create"))
                .header("Content-Type", "application/x-www-form-urlencoded;charset=utf-8")
                .POST(HttpRequest.BodyPublishers.ofString(strbuild.toString()))
                .build();

        try{
            HttpResponse<String> httpResponse = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            String bodyResponse = httpResponse.body();

            if (httpResponse.statusCode() != 200) {
                throw new RuntimeException("Erro na autenticação: status " + httpResponse.statusCode() + " - corpo: " + httpResponse.body());
            }

            var objectMapper = new ObjectMapper();
            var token = objectMapper.readValue(bodyResponse, AliexpressTokenResponse.class);
            return token;

        }catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public AliexpressRefreshToken renovarToken(String resfreshToken) throws IOException {
        var timestamp = Long.toString(System.currentTimeMillis());

        HashMap<String, String> request = new HashMap<>();
        request.put("app_key", appKey);
        request.put("timestamp", timestamp);
        request.put("sign_method", SIGN_METHOD_SHA256);

        var sign = signApiRequest(request, SIGN_METHOD_SHA256, "/auth/token/create");
        request.put("sign", sign);
        request.put("refresh_token", resfreshToken);

        StringBuilder strbuild = new StringBuilder();
        for(HashMap.Entry<String,String> entry: request.entrySet()){
            String key = entry.getKey();
            String value = entry.getValue();
            strbuild.append(key + "=" + value + "&");
        }

        strbuild.deleteCharAt(strbuild.length() - 1);

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create("https://api-sg.aliexpress.com/rest/auth/token/refresh"))
                .header("Content-Type", "application/x-www-form-urlencoded;charset=utf-8")
                .POST(HttpRequest.BodyPublishers.ofString(strbuild.toString()))
                .build();

        try{
            HttpResponse<String> httpResponse = client.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            String bodyResponse = httpResponse.body();

            if (httpResponse.statusCode() != 200) {
                throw new RuntimeException("Erro na autenticação: status " + httpResponse.statusCode() + " - corpo: " + httpResponse.body());
            }

            var objectMapper = new ObjectMapper();
            var refreshToken = objectMapper.readValue(bodyResponse, AliexpressRefreshToken.class);
            return refreshToken;

        }catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public String signApiRequest(Map<String, String> params, String signMethod, String apiName) throws IOException {
        String[] keys = params.keySet().toArray(new String[0]);
        Arrays.sort(keys);

        StringBuilder query = new StringBuilder();
        query.append(apiName);
        for (String key : keys) {
            String value = params.get(key);
            if (areNotEmpty(key, value)) {
                query.append(key).append(value);
            }
        }

        byte[] bytes = null;

        if (signMethod.equals(SIGN_METHOD_SHA256)) {
            bytes = encryptHMACSHA256(query.toString(), appSecret);
        }

        return byte2hex(bytes);
    }

    private static byte[] encryptHMACSHA256(String data, String secret) throws IOException {
        byte[] bytes = null;
        try {
            SecretKey secretKey = new SecretKeySpec(secret.getBytes(CHARSET_UTF8), SIGN_METHOD_HMAC_SHA256);
            Mac mac = Mac.getInstance(secretKey.getAlgorithm());
            mac.init(secretKey);
            bytes = mac.doFinal(data.getBytes(CHARSET_UTF8));
        } catch (GeneralSecurityException gse) {
            throw new IOException(gse.toString());
        }
        return bytes;
    }

    public static String byte2hex(byte[] bytes) {
        StringBuilder sign = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if (hex.length() == 1) {
                sign.append("0");
            }
            sign.append(hex.toUpperCase());
        }
        return sign.toString();
    }


    private boolean areNotEmpty(String key, String value) {
        return !key.isEmpty() && !value.isEmpty();
    }

}

