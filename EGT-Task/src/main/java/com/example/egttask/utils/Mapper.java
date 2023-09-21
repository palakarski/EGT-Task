package com.example.egttask.utils;

import com.example.egttask.model.dto.CurrentRateRequest;
import com.example.egttask.model.dto.CurrentRateResponse;
import com.example.egttask.model.entity.ExchangeRateEntity;
import java.util.Map;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Mapper {

    public CurrentRateResponse mapCurrentResponse(ExchangeRateEntity entity, CurrentRateRequest currentRateRequest) {
        return CurrentRateResponse.builder()
            .base(entity.getBase())
            .localDate(entity.getDate())
            .timestamp(entity.getTimestamp())
            .rate(Map.of(currentRateRequest.getCurrency().name(), entity.getRates().get(currentRateRequest.getCurrency().name())))
            .build();
    }
}
