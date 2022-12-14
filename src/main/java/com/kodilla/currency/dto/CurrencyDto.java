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
public class CurrencyDto {
    private Long id;
    private String name;
    private Code code;
    private LocalDate effectiveDate;
    private Double exchangeRate;
}
