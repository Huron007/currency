package com.kodilla.currency.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class NBPConfig {

    @Value("${nbp.api.endpoint.prod}")
    private String nbpApiEndpoint;
}
