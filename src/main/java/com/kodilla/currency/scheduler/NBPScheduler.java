package com.kodilla.currency.scheduler;

import com.kodilla.currency.client.NBPClient;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NBPScheduler {

    private final NBPClient nbpClient;

    @Scheduled(cron = "0 16 * * 1-5 *")
    public void updateCurrencyTable(){
        nbpClient.getTable();
    }
}
