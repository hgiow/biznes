package pet.albert.cryptoTracker;

import pet.albert.cryptoTracker.service.CoinService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BiznesApplication {

    public static void main(String[] args) {
        SpringApplication.run(BiznesApplication.class, args);
    }

    @Bean
    CommandLineRunner run(CoinService coinService) {
        return args -> {
            var coin = coinService.fetchCoinPrice("bitcoin");
            System.out.println("Saved coin: " + coin.getName() + ", Price: $" + coin.getPrice());
        };
    }
}
