package com.kodilla.currency.mapper;

import com.kodilla.currency.dto.CurrencyDto;
import com.kodilla.currency.entity.Currency;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class CurrencyMapper {

    public Currency mapToCurrency(CurrencyDto currencyDto){
        if(currencyDto.getId() == null){
            return Currency.builder()
                    .id(0L)
                    .name(currencyDto.getName())
                    .code(currencyDto.getCode())
                    .effectiveDate(currencyDto.getEffectiveDate())
                    .exchangeRate(currencyDto.getExchangeRate())
                    .build();
        } else {
            return Currency.builder()
                    .id(currencyDto.getId())
                    .name(currencyDto.getName())
                    .code(currencyDto.getCode())
                    .effectiveDate(currencyDto.getEffectiveDate())
                    .exchangeRate(currencyDto.getExchangeRate())
                    .build();
        }
    }

    public CurrencyDto mapToCurrencyDto(Currency currency){
        return new CurrencyDto(
                currency.getId(),
                currency.getName(),
                currency.getCode(),
                currency.getEffectiveDate(),
                currency.getExchangeRate()
        );
    }

    public List<Currency> mapToCurrencyList(List<CurrencyDto> currencyDtos){
        return currencyDtos.stream()
                .map(this::mapToCurrency)
                .collect(Collectors.toList());
    }

    public List<CurrencyDto> mapToCurrencyListDto(List<Currency> currencies){
        return currencies.stream()
                .map(this::mapToCurrencyDto)
                .collect(Collectors.toList());
    }
}
