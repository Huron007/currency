package com.kodilla.currency.scheduler;

import com.kodilla.currency.client.CurrencyCalculator;
import com.kodilla.currency.entity.Alert;
import com.kodilla.currency.repository.AlertRepository;
import com.kodilla.currency.repository.CurrencyRepository;
import com.kodilla.currency.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class AlertScheduler {

    private final AlertRepository alertRepository;
    private final CurrencyCalculator currencyCalculator;
    private final EmailService emailService;

    @Scheduled(cron = "5 16 * * 1-5 *")
    public void executeAlerts() {
        List<Alert> alertList = alertRepository.findAll().stream()
                .filter(Alert::isActive)
                .filter(e -> currencyCalculator.getLatestExchangeRate(e.getCode()) >= e.getTrackedMargin())
                .collect(Collectors.toList());

        for (Alert alert : alertList) {
            emailService.send(alert);
        }
    }
}
