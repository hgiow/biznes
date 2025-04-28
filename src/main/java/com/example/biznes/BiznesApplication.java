package com.example.biznes;

import com.example.biznes.service.CoinService;
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
            coinService.FetchCoinPrice("bitcoin");
        };
    }
}
