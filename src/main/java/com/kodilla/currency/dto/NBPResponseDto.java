package com.kodilla.currency.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.kodilla.currency.entity.Code;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class NBPResponseDto {
    @JsonProperty("currency")
    private String currency;
    @JsonProperty("code")
    private Code code;
    @JsonProperty("rates")
    private Rates[] rates;
}
