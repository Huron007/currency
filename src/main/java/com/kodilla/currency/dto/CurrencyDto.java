package com.kodilla.currency.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.kodilla.currency.entity.Code;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrencyDto {
    private Long id;
    @JsonProperty("currency")
    private String name;
    @JsonProperty("code")
    private Code code;
    @JsonProperty("effectiveDate")
    private LocalDate effectiveDate;
    @JsonProperty("mid")
    private Double exchangeRate;
}
