package com.example.egttask.model.dto;

import com.example.egttask.enumeration.CurrencyType;
import com.example.egttask.utils.CustomTimestampDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CurrentRateRequest {

    @NonNull
    private String requestId;
    @JsonDeserialize(using = CustomTimestampDeserializer.class)
    private LocalDateTime timestamp;
    @NonNull
    private String client;
    @NonNull
    private CurrencyType currency;
    private Integer period;

}
