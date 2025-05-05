import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ExchangeRateAPI {
    private static final String API_KEY = "edd61eced25cf84feab53ee3";
    private static final String BASE_URL = "https://v6.exchangerate-api.com/v6/";
    private static final Gson gson = new Gson();
    private static final HttpClient client = HttpClient.newHttpClient();

    public double getConversionRate(double amount, String fromCurrency, String toCurrency) throws Exception {
        String url = BASE_URL + API_KEY + "/pair/" + fromCurrency + "/" + toCurrency + "/" + amount;

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new Exception("Erro na requisição: " + response.statusCode());
        }

        JsonObject jsonResponse = gson.fromJson(response.body(), JsonObject.class);

        if (!jsonResponse.get("result").getAsString().equals("success")) {
            throw new Exception("Erro na conversão: " + jsonResponse.get("error-type").getAsString());
        }

        return jsonResponse.get("conversion_result").getAsDouble();
    }
} 