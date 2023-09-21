package com.example.egttask.model.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CurrentRateResponse {

    private String base;
    private LocalDate localDate;
    private LocalDateTime timestamp;
    private Map<String, Double> rate;

}
