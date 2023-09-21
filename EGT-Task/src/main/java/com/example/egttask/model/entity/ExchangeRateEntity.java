package com.example.egttask.model.entity;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapKeyColumn;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "exchange_rates")
public class ExchangeRateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "success")
    private Boolean success;
    @Column(name = "timestamp")
    private LocalDateTime timestamp;
    @Column(name = "base")
    private String base;
    @Column(name = "date")
    private LocalDate date;
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "rates",
        joinColumns = @JoinColumn(name = "exchange_rates_id"))
    @MapKeyColumn(name = "currency_code")
    @Column(name = "rate")
    private Map<String, Double> rates;
}
