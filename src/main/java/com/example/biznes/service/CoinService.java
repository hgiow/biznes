package com.example.biznes.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CoinService {

    private final OkHttpClient client = new OkHttpClient();
    private final ObjectMapper mapper = new ObjectMapper();

    public void FetchCoinPrice(String coinID){

        String url = "https://api.coingecko.com/api/v3/coins/markets?vs_currency=usd&ids=" + coinID;
        Request request = new Request.Builder().url(url).build();

        try (Response response = client.newCall(request).execute()) {

            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            JsonNode json = mapper.readTree(response.body().string());
            if (json.isEmpty()) throw new IOException("Coin not found");

            String name = json.get(0).get("name").asText();
            double price = json.get(0).get("current_price").asDouble();
            System.out.println("Coin: " + name + ", Price: $" + price);

        } catch (IOException e) {
            System.err.println("Error fetching coin price: " + e.getMessage());
        }
    }

}




