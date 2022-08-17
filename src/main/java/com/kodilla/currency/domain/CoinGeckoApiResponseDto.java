package com.kodilla.currency.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CoinGeckoApiResponseDto {
    @JsonProperty("symbol")
    private String code;
    @JsonProperty("name")
    private String name;
    @JsonProperty("current_price")
    private Double exchangeRate;
    @JsonProperty("last_updated")
    private LocalDateTime effectiveDate;
}
