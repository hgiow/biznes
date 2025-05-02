package pet.albert.cryptoTracker.controller;

import pet.albert.cryptoTracker.entity.Coin;
import pet.albert.cryptoTracker.service.CoinService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/coins")
public class CoinController {

    private final CoinService coinService;

    public CoinController(CoinService coinService) {
        this.coinService = coinService;
    }

    @GetMapping
    public ResponseEntity<?> getCoin(@RequestParam(required = false) String coinId) {

        try {
            if (coinId != null && !coinId.trim().isEmpty()) {

                Coin coin = coinService.fetchCoinPrice(coinId);
                return ResponseEntity.ok(coin);

            } else {

                List<Coin> coins = coinService.getAllCoins();
                return ResponseEntity.ok(coins);
            }
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Error fetching coin data: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(400).body("Invalid coinId: " + e.getMessage());
        }
    }

    @GetMapping("/by-id")
    public ResponseEntity<List<Coin>> getCoinsById(@RequestParam String coinId) {
        List<Coin> coins = coinService.getCoinsById(coinId);
        if (coins.isEmpty()) {
            return ResponseEntity.status(404).body(null);
        }
        return ResponseEntity.ok(coins);
    }
}