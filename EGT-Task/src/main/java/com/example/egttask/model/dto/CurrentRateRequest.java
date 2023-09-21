package com.example.egttask.model.dto;

import com.example.egttask.enumeration.CurrencyType;
import com.example.egttask.utils.CustomTimestampDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CurrentRateRequest {

    @NotBlank(message = "Request Id is mandatory")
    private String requestId;
    @NotNull(message = "Timestamp is mandatory")
    @JsonDeserialize(using = CustomTimestampDeserializer.class)
    private LocalDateTime timestamp;
    @NotBlank(message = "Client Id is mandatory")
    private String client;
    @NotNull(message = "Currency is mandatory")
    private CurrencyType currency;
    private Integer period;

}
