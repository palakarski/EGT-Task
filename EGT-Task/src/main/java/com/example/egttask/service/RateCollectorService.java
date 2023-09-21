package com.example.egttask.service;

import com.example.egttask.model.dto.ExchangeRateResponse;
import java.time.LocalDate;

public interface RateCollectorService {

    void saveFixerExchangeRate(ExchangeRateResponse rateExchangeResponseDTO);

    boolean findFixerExchangeRateByDate(LocalDate currentDate);
}
