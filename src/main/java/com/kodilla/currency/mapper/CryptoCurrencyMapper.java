package com.kodilla.currency.mapper;

import com.kodilla.currency.dto.CryptoCurrencyDto;
import com.kodilla.currency.entity.CryptoCurrency;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CryptoCurrencyMapper {

    public CryptoCurrency mapToCryptoCurrency(CryptoCurrencyDto cryptoCurrencyDto){
        if(cryptoCurrencyDto.getId() == null){
            return CryptoCurrency.builder()
                    .id(0L)
                    .name(cryptoCurrencyDto.getName())
                    .code(cryptoCurrencyDto.getCode())
                    .effectiveDate(cryptoCurrencyDto.getEffectiveDate())
                    .exchangeRate(cryptoCurrencyDto.getExchangeRate())
                    .build();
        } else {
            return CryptoCurrency.builder()
                    .id(cryptoCurrencyDto.getId())
                    .name(cryptoCurrencyDto.getName())
                    .code(cryptoCurrencyDto.getCode())
                    .effectiveDate(cryptoCurrencyDto.getEffectiveDate())
                    .exchangeRate(cryptoCurrencyDto.getExchangeRate())
                    .build();
        }
    }

    public CryptoCurrencyDto mapToCryptoCurrencyDto(CryptoCurrency cryptoCurrency){
        return new CryptoCurrencyDto(
                cryptoCurrency.getId(),
                cryptoCurrency.getName(),
                cryptoCurrency.getCode(),
                cryptoCurrency.getEffectiveDate(),
                cryptoCurrency.getExchangeRate()
        );
    }

    public List<CryptoCurrency> mapToCryptoCurrencyList(List<CryptoCurrencyDto> cryptoCurrencyDtos){
        return cryptoCurrencyDtos.stream()
                .map(this::mapToCryptoCurrency)
                .collect(Collectors.toList());
    }

    public List<CryptoCurrencyDto> mapToCryptoCurrencyListDto(List<CryptoCurrency> cryptoCurrencies){
        return cryptoCurrencies.stream()
                .map(this::mapToCryptoCurrencyDto)
                .collect(Collectors.toList());
    }
}
