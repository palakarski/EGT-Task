package com.example.egttask.utils;

import static com.example.egttask.enumeration.ServiceType.EXT_SERVICE_1;
import static com.example.egttask.enumeration.ServiceType.EXT_SERVICE_2;
import static com.example.egttask.utils.providers.DateProvider.get;
import static java.util.Objects.isNull;

import com.example.egttask.exception.BadRequestException;
import com.example.egttask.model.dto.CommandRequest;
import com.example.egttask.model.dto.CurrentRateRequest;
import com.example.egttask.model.dto.RequestDto;
import com.example.egttask.service.RequestService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class RequestValidator {

    private final RequestService requestService;

    public void validateAndSave(CurrentRateRequest currentRateRequest) {
        var requestHistoryDto = RequestDto.builder()
            .requestId(currentRateRequest.getRequestId())
            .clientId(currentRateRequest.getClient())
            .localDate(get())
            .serviceName(EXT_SERVICE_1).build();
        if (requestService.isDuplicatedRequest(requestHistoryDto)) {
            throw new BadRequestException("The request could not be processed due to duplication of the requestId");
        }
        requestService.saveRequest(requestHistoryDto);
    }

    public void validateAndSave(CommandRequest commandRequest) {
        var requestHistoryDto = RequestDto.builder()
            .requestId(commandRequest.getId())
            .clientId(isNull(commandRequest.getHistoryXMLRequest()) ?
                commandRequest.getCurrentXMLRequest().getConsumer() : commandRequest.getHistoryXMLRequest().getConsumer())
            .localDate(get())
            .serviceName(EXT_SERVICE_2).build();
        if (requestService.isDuplicatedRequest(requestHistoryDto)) {
            throw new BadRequestException("The request could not be processed due to duplication of the requestId");
        }
        requestService.saveRequest(requestHistoryDto);
    }
}
