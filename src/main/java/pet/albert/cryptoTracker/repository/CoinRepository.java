package pet.albert.cryptoTracker.repository;

import org.springframework.data.jpa.repository.Query;
import pet.albert.cryptoTracker.entity.Coin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CoinRepository extends JpaRepository<Coin, Long> {

    @Query("SELECT c FROM Coin c WHERE c.coinID = :coinId")
    List<Coin> findByCoinId(String coinId);
}