package com.example.egttask.utils;

import static com.example.egttask.utils.providers.DateProvider.get;

import com.example.egttask.enumeration.ServiceType;
import com.example.egttask.exception.BadRequestException;
import com.example.egttask.model.dto.CommonRequest;
import com.example.egttask.model.dto.RequestDto;
import com.example.egttask.service.RequestService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RequestValidator {

    private final RequestService requestService;

    public <T extends CommonRequest> void validateAndSave(T request, ServiceType serviceName) {
        var requestHistoryDto = RequestDto.builder()
            .requestId(request.getRequestId())
            .clientId(request.getCustomerId())
            .localDate(get())
            .serviceName(serviceName)
            .build();
        if (requestService.isDuplicatedRequest(requestHistoryDto)) {
            throw new BadRequestException("The request could not be processed due to duplication of the requestId");
        }
        requestService.saveRequest(requestHistoryDto);
    }

}
