package com.example.egttask.model.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class PeriodRateRequest extends CurrentRateRequest {

    @NotNull(message = "Period is mandatory")
    private Integer period;

}
