package com.example.egttask.controller;

import static com.example.egttask.utils.Constants.COMMAND;
import static com.example.egttask.utils.Constants.XML_API;
import static java.util.Objects.isNull;

import com.example.egttask.enumeration.CurrencyType;
import com.example.egttask.model.dto.CommandRequest;
import com.example.egttask.model.dto.CurrentRateRequest;
import com.example.egttask.model.dto.CurrentXMLRequest;
import com.example.egttask.model.dto.HistoryXMLRequest;
import com.example.egttask.service.ExchangeRateService;
import com.example.egttask.service.RequestService;
import com.example.egttask.utils.RequestValidator;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = XML_API)
@AllArgsConstructor
public class XmlApiController {

    private final ExchangeRateService statisticCollectorService;
    private final RequestService requestService;
    private final RequestValidator requestValidator;

    @GetMapping(value = COMMAND, consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Object> handleXmlRequest(@RequestBody CommandRequest commandRequest) throws Exception {

        String id = commandRequest.getId();
        CurrentXMLRequest currentXMLRequest;
        HistoryXMLRequest historyXMLRequest;
        if (isNull(commandRequest.getCurrentXMLRequest()) && isNull(commandRequest.getHistoryXMLRequest())) {
            throw new Exception();
        }
        currentXMLRequest = commandRequest.getCurrentXMLRequest();

        if (isNull(commandRequest.getHistoryXMLRequest())) {
            requestValidator.validateAndSave(commandRequest);

            return ResponseEntity.ok(statisticCollectorService.getCurrentExchangeRate(
                CurrentRateRequest.builder().client(commandRequest.getId()).requestId(currentXMLRequest.getConsumer())
                    .currency(CurrencyType.valueOf(currentXMLRequest.getCurrency()))
                    .build()));
        }
        requestValidator.validateAndSave(commandRequest);

        historyXMLRequest = commandRequest.getHistoryXMLRequest();
        return ResponseEntity.ok(statisticCollectorService.getExchangeRateForPeriod(
            CurrentRateRequest.builder().client(commandRequest.getId()).requestId(historyXMLRequest.getConsumer())
                .currency(CurrencyType.valueOf(
                    historyXMLRequest.getCurrency())).period(
                    historyXMLRequest.getPeriod()).build()));
    }
}