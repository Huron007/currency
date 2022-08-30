package com.kodilla.currency.mapper;

import com.kodilla.currency.dto.CurrencyDto;
import com.kodilla.currency.entity.Code;
import com.kodilla.currency.entity.Currency;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CurrencyMapperTest {

    @Autowired
    private CurrencyMapper mapper = new CurrencyMapper();

    @Test
    void mapToCurrency() {
        //Given
        Currency currency = new Currency(1L, "test", Code.USD, LocalDate.now(), 1.0);
        CurrencyDto currencyDto = new CurrencyDto(1L, "test", Code.USD, LocalDate.now(), 1.0);
        //When
        Currency mappedCurrency = mapper.mapToCurrency(currencyDto);
        //Then
        assertEquals(currency, mappedCurrency);
    }

    @Test
    void mapToCurrencyDto() {
        //Given
        Currency currency = new Currency(1L, "test", Code.USD, LocalDate.now(), 1.0);
        CurrencyDto currencyDto = new CurrencyDto(1L, "test", Code.USD, LocalDate.now(), 1.0);
        //When
        CurrencyDto mappedCurrency = mapper.mapToCurrencyDto(currency);
        //Then
        assertEquals(currencyDto, mappedCurrency);
    }

    @Test
    void mapToCurrencyList() {
        //Given
        Currency currency = new Currency(1L, "test", Code.USD, LocalDate.now(), 1.0);
        CurrencyDto currencyDto = new CurrencyDto(1L, "test", Code.USD, LocalDate.now(), 1.0);
        List<Currency> list = List.of(currency);
        List<CurrencyDto> dtoList = List.of(currencyDto);
        //When
        List<Currency> mappedList = mapper.mapToCurrencyList(dtoList);
        //Then
        assertEquals(list, mappedList);
    }

    @Test
    void mapToCurrencyListDto() {
        //Given
        Currency currency = new Currency(1L, "test", Code.USD, LocalDate.now(), 1.0);
        CurrencyDto currencyDto = new CurrencyDto(1L, "test", Code.USD, LocalDate.now(), 1.0);
        List<Currency> list = List.of(currency);
        List<CurrencyDto> dtoList = List.of(currencyDto);
        //When
        List<CurrencyDto> mappedList = mapper.mapToCurrencyListDto(list);
        //Then
        assertEquals(dtoList, mappedList);
    }
}