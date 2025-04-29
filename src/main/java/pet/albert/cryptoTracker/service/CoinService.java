package pet.albert.cryptoTracker.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Service;
import pet.albert.cryptoTracker.entity.Coin;
import pet.albert.cryptoTracker.repository.CoinRepository;
import java.io.IOException;
import java.time.LocalDateTime;

@Service
public class CoinService {

    private final OkHttpClient client = new OkHttpClient();
    private final ObjectMapper mapper = new ObjectMapper();
    private final CoinRepository coinRepository;

    public CoinService(CoinRepository coinRepository) {
        this.coinRepository = coinRepository;
    }

    public Coin fetchCoinPrice(String coinID){

        String url = "https://api.coingecko.com/api/v3/coins/markets?vs_currency=usd&ids=" + coinID;
        Request request = new Request.Builder().url(url).build();

        try (Response response = client.newCall(request).execute()) {

            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);
            JsonNode coinData = mapper.readTree(response.body().string());
            if (coinData.isEmpty()) throw new IOException("Coin not found");

            Coin coin = new Coin();
            coin.setCoinID(coinID);
            coin.setName(coinData.get(0).get("name").asText());
            coin.setPrice(coinData.get(0).get("current_price").asDouble());
            coin.setTimestamp(LocalDateTime.now());

            return coinRepository.save(coin);

        } catch (IOException e) {
            System.err.println("Error fetching coin price: " + e.getMessage());
        }

        return null;
    }
}




