package com.example.egttask.service.impl;

import com.example.egttask.model.dto.ExchangeRateResponse;
import com.example.egttask.model.entity.ExchangeRateEntity;
import com.example.egttask.repository.ExchangeRateRepository;
import com.example.egttask.service.RateCollectorService;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RateCollectorServiceImpl implements RateCollectorService {

    private final ExchangeRateRepository exchangeRateRepository;
    private final ModelMapper modelMapper;

    @Override
    public void saveFixerExchangeRate(ExchangeRateResponse rateExchangeResponseDTO) {
        var entity = modelMapper.map(rateExchangeResponseDTO, ExchangeRateEntity.class);
        exchangeRateRepository.save(entity);
    }

    @Override
    public boolean findFixerExchangeRateByDate(LocalDate localDate) {
        return exchangeRateRepository.findFirstByDateIsBeforeOrderByDateDesc(localDate).isPresent();
    }
}
