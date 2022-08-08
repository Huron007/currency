package com.kodilla.currency.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Rates {
    @JsonProperty("effectiveDate")
    private LocalDate effectiveDate;
    @JsonProperty("mid")
    private Double exchangeRate;
}
