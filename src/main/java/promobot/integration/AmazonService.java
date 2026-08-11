package promobot.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.cdimascio.dotenv.Dotenv;
import promobot.model.SearchResponse;
import promobot.model.TokenResponse;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;

public class AmazonService {

    private HttpClient client = HttpClient.newHttpClient();
    private Dotenv dotenv = Dotenv.load();

    public TokenResponse autentificar(){
        String clientId = dotenv.get("AMAZON_CLIENT_ID");
        String clientSecret = dotenv.get("AMAZON_CLIENT_SECRET");
        String body = String.format("{\"grant_type\": \"client_credentials\", \"client_id\": \"%s\", \"client_secret\": \"%s\", \"scope\": \"creatorsapi::default\"}", clientId, clientSecret);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.amazon.com/auth/o2/token"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String bodyResponse = response.body();

            if (response.statusCode() != 200) {
                throw new RuntimeException("Erro na autenticação: " + response.body());
            }

            var objectMapper = new ObjectMapper();
            var token = objectMapper.readValue(bodyResponse, TokenResponse.class);
            return token;

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    public SearchResponse buscarProdutos(String keywords){

        var accessToken = autentificar().getAccessToken();
        String partnerTag = dotenv.get("AMAZON_PARTNER_TAG");
        String body = String.format("{\"partnerTag\": \"%s\",\"keywords\": \"%s\",\"marketplace\": \"www.amazon.com.br\",\"resources\": [\"images.primary.medium\", \"itemInfo.title\", \"offersV2.listings.price\"]}", partnerTag , keywords);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://creatorsapi.amazon/catalog/v1/searchItems"))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + accessToken)
                .header("x-marketplace", "www.amazon.com.br")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            String bodyResponse = response.body();

            if (response.statusCode() != 200){
                throw new RuntimeException("Erro na busca: " + response.body());
            }

            var objectMapper = new ObjectMapper();
            var productResponse = objectMapper.readValue(bodyResponse, SearchResponse.class);
            return productResponse;

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
