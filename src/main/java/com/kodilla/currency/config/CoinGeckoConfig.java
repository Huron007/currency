package com.kodilla.currency.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class CoinGeckoConfig {
    @Value("${coingecko.api.endpoint.prod}")
    private String coinGeckoApiEndpoint;
}
