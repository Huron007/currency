package com.kodilla.currency.client;

import com.kodilla.currency.config.NBPConfig;
import com.kodilla.currency.domain.NBPResponseConverter;
import com.kodilla.currency.dto.*;
import com.kodilla.currency.entity.Code;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class NBPClientTest {

    @InjectMocks
    private NBPClient nbpClient;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private NBPConfig config;

    @Mock
    private NBPResponseConverter converter;

    @Test
    void getSingleCurrency() throws URISyntaxException {
        //Given
        CurrencyDto currencyDto = new CurrencyDto(1L, "test", Code.USD, LocalDate.now(), 1.0);
        Rates[] rates = new Rates[0];
        NBPResponseDto nbpResponseDto = new NBPResponseDto("test", Code.USD, rates);
        URI url = new URI("http://test.com/rates/a/USD");
        when(config.getNbpApiEndpoint()).thenReturn("http://test.com");
        when(restTemplate.getForObject(url, NBPResponseDto.class)).thenReturn(nbpResponseDto);
        when(converter.convertToSingleCurrency(any(NBPResponseDto.class))).thenReturn(currencyDto);
        //When
        CurrencyDto result = nbpClient.getSingleCurrency(Code.USD);
        //Then
        assertEquals(currencyDto, result);
    }

    @Test
    void getTable() throws URISyntaxException {
        //Given
        CurrencyDto currencyDto = new CurrencyDto(1L, "test", Code.USD, LocalDate.now(), 1.0);
        List<CurrencyDto> list = List.of(currencyDto);
        NBPResponseTableDto[] nbpResponseDto = new NBPResponseTableDto[0];
        URI url = new URI("http://test.com/tables/a/");
        when(config.getNbpApiEndpoint()).thenReturn("http://test.com");
        when(restTemplate.getForObject(url, NBPResponseTableDto[].class)).thenReturn(nbpResponseDto);
        when(converter.convertToCurrencyTable(any(NBPResponseTableDto[].class))).thenReturn(list);
        //When
        List<CurrencyDto> result = nbpClient.getTable();
        //Then
        assertEquals(list, result);
    }

    @Test
    void getSingleCurrencyFromWholeMonth() throws URISyntaxException {
        //Given
        CurrencyDto currencyDto = new CurrencyDto(1L, "test", Code.USD, LocalDate.now(), 1.0);
        List<CurrencyDto> list = List.of(currencyDto);
        Rates[] rates = new Rates[0];
        NBPResponseDto nbpResponseDto = new NBPResponseDto("test", Code.USD, rates);
        URI url = new URI("http://test.com/rates/a/USD/last/30");
        when(config.getNbpApiEndpoint()).thenReturn("http://test.com");
        when(restTemplate.getForObject(url, NBPResponseDto.class)).thenReturn(nbpResponseDto);
        when(converter.convertToSingleCurrencyFromWholeMonth(any(NBPResponseDto.class))).thenReturn(list);
        //When
        List<CurrencyDto> result = nbpClient.getSingleCurrencyFromWholeMonth(Code.USD);
        //Then
        assertEquals(list, result);
    }

    @Test
    void getTableFromWholeMonth() throws URISyntaxException {
        //Given
        CurrencyDto currencyDto = new CurrencyDto(1L, "test", Code.USD, LocalDate.now(), 1.0);
        List<CurrencyDto> list = List.of(currencyDto);
        NBPResponseTableDto[] nbpResponseDto = new NBPResponseTableDto[0];
        URI url = new URI("http://test.com/tables/a/last/30");
        when(config.getNbpApiEndpoint()).thenReturn("http://test.com");
        when(restTemplate.getForObject(url, NBPResponseTableDto[].class)).thenReturn(nbpResponseDto);
        when(converter.convertToCurrencyTableFromWholeMonth(any(NBPResponseTableDto[].class))).thenReturn(list);
        //When
        List<CurrencyDto> result = nbpClient.getTableFromWholeMonth();
        //Then
        assertEquals(list, result);
    }
}