package com.example.egttask.model.dto;

import com.example.egttask.enumeration.ServiceType;
import java.time.LocalDate;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RequestDto {

    private ServiceType serviceName;
    private String requestId;
    private LocalDate localDate;
    private String clientId;

}
