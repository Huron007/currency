package com.kodilla.currency.mapper;

import com.kodilla.currency.dto.CryptoCurrencyDto;
import com.kodilla.currency.entity.Code;
import com.kodilla.currency.entity.CryptoCurrency;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CryptoCurrencyMapperTest {

    @Autowired
    private CryptoCurrencyMapper mapper = new CryptoCurrencyMapper();

    @Test
    void mapToCryptoCurrency() {
        //Given
        CryptoCurrency cryptoCurrency = new CryptoCurrency(1L, "test", Code.btc, LocalDate.now(), 1.0);
        CryptoCurrencyDto cryptoCurrencyDto = new CryptoCurrencyDto(1L, "test", Code.btc, LocalDate.now(), 1.0);
        //When
        CryptoCurrency mappedCurrency = mapper.mapToCryptoCurrency(cryptoCurrencyDto);
        //Then
        assertEquals(cryptoCurrency, mappedCurrency);
    }

    @Test
    void mapToCryptoCurrencyDto() {
        //Given
        CryptoCurrency cryptoCurrency = new CryptoCurrency(1L, "test", Code.btc, LocalDate.now(), 1.0);
        CryptoCurrencyDto cryptoCurrencyDto = new CryptoCurrencyDto(1L, "test", Code.btc, LocalDate.now(), 1.0);
        //When
        CryptoCurrencyDto mappedCurrency = mapper.mapToCryptoCurrencyDto(cryptoCurrency);
        //Then
        assertEquals(cryptoCurrencyDto, mappedCurrency);
    }

    @Test
    void mapToCryptoCurrencyList() {
        //Given
        CryptoCurrency cryptoCurrency = new CryptoCurrency(1L, "test", Code.btc, LocalDate.now(), 1.0);
        CryptoCurrencyDto cryptoCurrencyDto = new CryptoCurrencyDto(1L, "test", Code.btc, LocalDate.now(), 1.0);
        List<CryptoCurrency> list = List.of(cryptoCurrency);
        List<CryptoCurrencyDto> dtoList = List.of(cryptoCurrencyDto);
        //When
        List<CryptoCurrency> mappedList = mapper.mapToCryptoCurrencyList(dtoList);
        //Then
        assertEquals(list, mappedList);
    }

    @Test
    void mapToCryptoCurrencyListDto() {
        //Given
        CryptoCurrency cryptoCurrency = new CryptoCurrency(1L, "test", Code.btc, LocalDate.now(), 1.0);
        CryptoCurrencyDto cryptoCurrencyDto = new CryptoCurrencyDto(1L, "test", Code.btc, LocalDate.now(), 1.0);
        List<CryptoCurrency> list = List.of(cryptoCurrency);
        List<CryptoCurrencyDto> dtoList = List.of(cryptoCurrencyDto);
        //When
        List<CryptoCurrencyDto> mappedList = mapper.mapToCryptoCurrencyListDto(list);
        //Then
        assertEquals(dtoList, mappedList);
    }
}