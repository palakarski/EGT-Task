package com.example.egttask.model.dto;

import com.example.egttask.utils.CustomTimestampDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;
import lombok.Data;

@Data
public class ExchangeRateResponse {

    private Boolean success;
    @JsonDeserialize(using = CustomTimestampDeserializer.class)
    private LocalDateTime timestamp;
    private String base;
    private LocalDate date;
    private Map<String, Double> rates;

}
