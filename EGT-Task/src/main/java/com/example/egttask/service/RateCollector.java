package com.example.egttask.service;

import com.example.egttask.config.FixerConfiguration;
import com.example.egttask.model.dto.ExchangeRateResponse;
import jakarta.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@AllArgsConstructor
@Slf4j
@Data
public class RateCollector {

    private final FixerConfiguration fixerConfiguration;
    private final RateCollectorService rateCollectorService;
    private final RestTemplate restTemplate = new RestTemplate();

    @Scheduled(cron = "${myapp.scheduling.period}")
    public void getDailyExchangeRates() {

        var response = restTemplate.exchange(
            fixerConfiguration.getBaseUrl() + "latest" + fixerConfiguration.getAccessParameter() + fixerConfiguration.getAccessKey(),
            HttpMethod.POST,
            null,
            ExchangeRateResponse.class).getBody();
        rateCollectorService.saveFixerExchangeRate(response);

    }

    @PostConstruct
    public void populateExchangeRate() {
        Long period = 36L;
        LocalDate currentDate = LocalDate.now();

        int numberOfThreads = Runtime.getRuntime().availableProcessors();
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);

        for (int i = 0; i < period; i++) {
            final LocalDate dateToFetch = currentDate.minusMonths(i);

            if (rateCollectorService.findFixerExchangeRateByDate(dateToFetch)) {
                break;
            }

            executorService.submit(() -> {
                try {
                    String url = fixerConfiguration.getBaseUrl() + dateToFetch + fixerConfiguration.getAccessParameter()
                        + fixerConfiguration.getAccessKey();
                    var response = restTemplate.exchange(url, HttpMethod.POST, null, ExchangeRateResponse.class).getBody();
                    rateCollectorService.saveFixerExchangeRate(response);
                } catch (Exception e) {
                    log.error("Error occurred: ", e);
                }
            });
        }
        executorService.shutdown();
    }

}
