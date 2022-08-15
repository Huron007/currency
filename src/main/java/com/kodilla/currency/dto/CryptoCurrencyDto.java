package com.kodilla.currency.dto;

import com.kodilla.currency.entity.Code;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CryptoCurrencyDto {
    private Long id;
    private String name;
    private Code code;
    private LocalDate effectiveDate;
    private Double exchangeRate;
}
