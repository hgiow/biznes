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
    public ResponseEntity<?> getCoin(@RequestParam(required = false) String coinID) {
        try {
            if (coinID != null && !coinID.trim().isEmpty()) {

                Coin coin = coinService.fetchCoinPrice(coinID);
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
}