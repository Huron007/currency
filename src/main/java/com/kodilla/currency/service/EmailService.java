package com.kodilla.currency.service;

import com.kodilla.currency.entity.Alert;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService {

    public void send(final Alert alert){
        log.info("Email send " + alert.getTrackedMargin() + " " + alert.getCreationDate() + " " + alert.getCode());
    }
}
