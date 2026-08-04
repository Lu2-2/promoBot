package promobot.integration;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;

public class AmazonService {

    private HttpClient client = HttpClient.newHttpClient();

    public void autentificar(){
        String clientId = "";
        String clientSecret = "";
        String body = String.format("{\"grant_type\": \"client_credentials\", \"client_id\": \"%s\", \"client_secret\": \"%s\", \"scope\": \"creatorsapi::default\"}", clientId, clientSecret);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.amazon.com/auth/o2/token"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

}
