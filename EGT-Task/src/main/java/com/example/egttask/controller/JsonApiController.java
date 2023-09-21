package com.example.egttask.controller;

import static com.example.egttask.utils.Constants.CURRENT;
import static com.example.egttask.utils.Constants.JSON_API;
import static com.example.egttask.utils.Constants.PERIOD;
import static java.util.Objects.isNull;

import com.example.egttask.exception.BadRequestException;
import com.example.egttask.model.dto.CurrentRateRequest;
import com.example.egttask.model.dto.CurrentRateResponse;
import com.example.egttask.service.ExchangeRateService;
import com.example.egttask.service.RequestService;
import com.example.egttask.utils.RequestValidator;
import jakarta.validation.Valid;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = JSON_API)
@AllArgsConstructor
public class JsonApiController {

    private final ExchangeRateService statisticCollectorService;
    private final RequestService requestService;
    private final RequestValidator requestValidator;

    @GetMapping(value = CURRENT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CurrentRateResponse> getCurrentRate(@Valid @RequestBody CurrentRateRequest currentRateRequest) {
        requestValidator.validateAndSave(currentRateRequest);
        return ResponseEntity.ok(statisticCollectorService.getCurrentExchangeRate(currentRateRequest));
    }

    @GetMapping(value = PERIOD, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CurrentRateResponse>> getPeriodRate(@Valid @RequestBody CurrentRateRequest currentRateRequest) {

        requestValidator.validateAndSave(currentRateRequest);
        if (isNull(currentRateRequest.getPeriod())) {
            throw new BadRequestException("Period cannot be null");
        }
        return ResponseEntity.ok(statisticCollectorService.getExchangeRateForPeriod(currentRateRequest));


    }
}