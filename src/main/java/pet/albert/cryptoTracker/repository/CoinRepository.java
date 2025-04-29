package pet.albert.cryptoTracker.repository;

import pet.albert.cryptoTracker.entity.Coin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoinRepository extends JpaRepository<Coin, Long> {
}