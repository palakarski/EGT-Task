package com.example.egttask.service;

import com.example.egttask.model.dto.CurrentRateRequest;
import com.example.egttask.model.dto.CurrentRateResponse;
import com.example.egttask.model.dto.PeriodRateRequest;
import java.util.List;

public interface ExchangeRateService {

    CurrentRateResponse getCurrentExchangeRate(CurrentRateRequest currentRateRequest);

    List<CurrentRateResponse> getExchangeRateForPeriod(PeriodRateRequest periodRateRequest);
}
