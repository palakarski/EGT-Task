package com.example.egttask.repository;

import com.example.egttask.model.entity.ExchangeRateEntity;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeRateRepository extends JpaRepository<ExchangeRateEntity, Long> {

    @Override
    List<ExchangeRateEntity> findAll();

    @Query(value = "SELECT * FROM exchange_rates as f order by f.id DESC LIMIT 1", nativeQuery = true)
    Optional<ExchangeRateEntity> findLatest();

    Optional<ExchangeRateEntity> findFirstByDateIsBeforeOrderByDateDesc(LocalDate date);

    Optional<List<ExchangeRateEntity>> findAllByTimestampIsAfter(LocalDateTime localDateTime);
}
