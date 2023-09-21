package com.example.egttask.service.impl;

import com.example.egttask.exception.BadRequestException;
import com.example.egttask.model.dto.CurrentRateRequest;
import com.example.egttask.model.dto.CurrentRateResponse;
import com.example.egttask.model.entity.ExchangeRateEntity;
import com.example.egttask.repository.ExchangeRateRepository;
import com.example.egttask.service.ExchangeRateService;
import com.example.egttask.utils.Mapper;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ExchangeRateServiceImpl implements ExchangeRateService {

    private final ExchangeRateRepository exchangeRateRepository;

    @Override
    public CurrentRateResponse getCurrentExchangeRate(CurrentRateRequest currentRateRequest) {
        var entity = exchangeRateRepository.findLatest().orElseThrow(() -> new BadRequestException("Data is missing"));
        return Mapper.mapCurrentResponse(entity, currentRateRequest);

    }

    @Override
    public List<CurrentRateResponse> getExchangeRateForPeriod(CurrentRateRequest currentRateRequest) {

        var entityList = exchangeRateRepository.findAllByTimestampIsAfter(
                LocalDateTime.now().minus(Duration.ofHours(currentRateRequest.getPeriod())))
            .orElseThrow(() -> new BadRequestException("Data is missing"));
        List<CurrentRateResponse> listResponse = new ArrayList<>();
        for (ExchangeRateEntity entity : entityList) {
            listResponse.add(Mapper.mapCurrentResponse(entity, currentRateRequest));
        }

        return listResponse;
    }
}
