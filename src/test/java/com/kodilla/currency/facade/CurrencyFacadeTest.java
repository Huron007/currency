package com.kodilla.currency.facade;

import com.kodilla.currency.client.CurrencyCalculator;
import com.kodilla.currency.client.NBPClient;
import com.kodilla.currency.dto.CurrencyDto;
import com.kodilla.currency.entity.Code;
import com.kodilla.currency.entity.Currency;
import com.kodilla.currency.exception.CodeNotFoundException;
import com.kodilla.currency.exception.CurrencyNotFoundException;
import com.kodilla.currency.exception.DuplicateCurrencyException;
import com.kodilla.currency.mapper.CurrencyMapper;
import com.kodilla.currency.service.CurrencyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CurrencyFacadeTest {

    @InjectMocks
    private CurrencyFacade currencyFacade;

    @Mock
    private CurrencyService currencyService;

    @Mock
    private CurrencyMapper currencyMapper;

    @Mock
    private NBPClient nbpClient;

    @Mock
    private CurrencyCalculator calculator;


    @Test
    void getAllCurrencies() {
        //Given
        CurrencyDto currencyDto = new CurrencyDto(1L, "test", Code.USD, LocalDate.now(), 1.0);
        List<CurrencyDto> dtoList = List.of(currencyDto);
        Currency currency = new Currency(1L, "test", Code.USD, LocalDate.now(), 1.0);
        List<Currency> list = List.of(currency);
        when(currencyService.getAllCurrencies()).thenReturn(list);
        when(currencyMapper.mapToCurrencyListDto(anyList())).thenReturn(dtoList);
        //When
        List<CurrencyDto> result = currencyFacade.getAllCurrencies();
        //Then
        assertThat(result).isNotNull();
        assertEquals(dtoList, result);
    }

    @Test
    void getSingleCurrency() throws CurrencyNotFoundException {
        //Given
        Currency currency = new Currency(1L, "test", Code.USD, LocalDate.now(), 1.0);
        CurrencyDto currencyDto = new CurrencyDto(1L, "test", Code.USD, LocalDate.now(), 1.0);
        when(currencyService.getCurrency(1L)).thenReturn(currency);
        when(currencyMapper.mapToCurrencyDto(any(Currency.class))).thenReturn(currencyDto);
        //When
        CurrencyDto result = currencyFacade.getSingleCurrency(1L);
        //Then
        assertEquals(currencyDto, result);
    }

    @Test
    void saveSingleCurrency() throws DuplicateCurrencyException {
        //Given
        Currency currency = new Currency(1L, "test", Code.USD, LocalDate.now(), 1.0);
        CurrencyDto currencyDto = new CurrencyDto(1L, "test", Code.USD, LocalDate.now(), 1.0);
        when(currencyService.saveCurrency(any(Currency.class))).thenReturn(currency);
        when(currencyMapper.mapToCurrency(any(CurrencyDto.class))).thenReturn(currency);
        when(currencyMapper.mapToCurrencyDto(any(Currency.class))).thenReturn(currencyDto);
        //When
        CurrencyDto result = currencyFacade.saveSingleCurrency(currencyDto);
        //Then
        assertEquals(currencyDto, result);
    }

    @Test
    void saveCurrencyList() {
        //Given
        CurrencyDto currencyDto = new CurrencyDto(1L, "test", Code.USD, LocalDate.now(), 1.0);
        List<CurrencyDto> dtoList = List.of(currencyDto);
        Currency currency = new Currency(1L, "test", Code.USD, LocalDate.now(), 1.0);
        List<Currency> list = List.of(currency);
        when(currencyService.saveCurrencyList(anyList())).thenReturn(list);
        when(currencyMapper.mapToCurrencyListDto(anyList())).thenReturn(dtoList);
        when(currencyMapper.mapToCurrencyList(anyList())).thenReturn(list);
        //When
        List<CurrencyDto> result = currencyFacade.saveCurrencyTable(dtoList);
        //Then
        assertThat(result).isNotNull();
        assertEquals(dtoList, result);

    }

    @Test
    void updateSingleCurrency() throws CurrencyNotFoundException {
        //Given
        Currency currency = new Currency(1L, "test", Code.USD, LocalDate.now(), 1.0);
        CurrencyDto currencyDto = new CurrencyDto(1L, "test", Code.USD, LocalDate.now(), 1.0);
        when(currencyService.updateCurrency(any(Currency.class))).thenReturn(currency);
        when(currencyMapper.mapToCurrency(any(CurrencyDto.class))).thenReturn(currency);
        when(currencyMapper.mapToCurrencyDto(any(Currency.class))).thenReturn(currencyDto);
        //When
        CurrencyDto result = currencyFacade.updateSingleCurrency(currencyDto);
        //Then
        assertEquals(currencyDto, result);
    }

    @Test
    void updateCurrencyList() throws CurrencyNotFoundException {
        //Given
        CurrencyDto currencyDto = new CurrencyDto(1L, "test", Code.USD, LocalDate.now(), 1.0);
        List<CurrencyDto> dtoList = List.of(currencyDto);
        Currency currency = new Currency(1L, "test", Code.USD, LocalDate.now(), 1.0);
        List<Currency> list = List.of(currency);
        when(currencyService.updateCurrencyList(anyList())).thenReturn(list);
        when(currencyMapper.mapToCurrencyListDto(anyList())).thenReturn(dtoList);
        when(currencyMapper.mapToCurrencyList(anyList())).thenReturn(list);
        //When
        List<CurrencyDto> result = currencyFacade.updateCurrencyTable(dtoList);
        //Then
        assertThat(result).isNotNull();
        assertEquals(dtoList, result);
    }

    @Test
    void deleteCurrency() throws CurrencyNotFoundException {
        //Given & When
        currencyFacade.deleteCurrency(1L);
        //Then
        verify(currencyService, times(1)).deleteCurrency(1L);
    }

    @Test
    void fetchCurrencyList() {
        //Given
        CurrencyDto currencyDto = new CurrencyDto(1L, "test", Code.USD, LocalDate.now(), 1.0);
        List<CurrencyDto> dtoList = List.of(currencyDto);
        Currency currency = new Currency(1L, "test", Code.USD, LocalDate.now(), 1.0);
        List<Currency> list = List.of(currency);
        when(nbpClient.getTable()).thenReturn(dtoList);
        when(currencyMapper.mapToCurrencyList(anyList())).thenReturn(list);
        when(currencyMapper.mapToCurrencyListDto(anyList())).thenReturn(dtoList);
        when(currencyService.saveCurrencyList(anyList())).thenReturn(list);
        //When
        List<CurrencyDto> result = currencyFacade.fetchCurrencyList();
        //Then
        assertThat(result).isNotNull();
        assertEquals(dtoList, result);
    }

    @Test
    void fetchSingleCurrency() throws DuplicateCurrencyException {
        //Given
        CurrencyDto currencyDto = new CurrencyDto(1L, "test", Code.USD, LocalDate.now(), 1.0);
        Currency currency = new Currency(1L, "test", Code.USD, LocalDate.now(), 1.0);
        when(nbpClient.getSingleCurrency(Code.USD)).thenReturn(currencyDto);
        when(currencyMapper.mapToCurrency(any(CurrencyDto.class))).thenReturn(currency);
        when(currencyMapper.mapToCurrencyDto(any(Currency.class))).thenReturn(currencyDto);
        when(currencyService.saveCurrency(any(Currency.class))).thenReturn(currency);
        //When
        CurrencyDto result = currencyFacade.fetchSingleCurrency(Code.USD);
        //Then
        assertEquals(currencyDto, result);
    }

    @Test
    void fetchCurrencyListFromWholeMonth() {
        //Given
        CurrencyDto currencyDto = new CurrencyDto(1L, "test", Code.USD, LocalDate.now(), 1.0);
        List<CurrencyDto> dtoList = List.of(currencyDto);
        Currency currency = new Currency(1L, "test", Code.USD, LocalDate.now(), 1.0);
        List<Currency> list = List.of(currency);
        when(currencyMapper.mapToCurrencyList(anyList())).thenReturn(list);
        when(currencyMapper.mapToCurrencyListDto(anyList())).thenReturn(dtoList);
        when(currencyService.saveCurrencyList(anyList())).thenReturn(list);
        //When
        List<CurrencyDto> result = currencyFacade.fetchCurrencyListFromWholeMonth();
        //Then
        assertThat(result).isNotNull();
        assertEquals(dtoList, result);
    }

    @Test
    void fetchSingleCurrencyFromWholeMonth() {
        //Given
        CurrencyDto currencyDto = new CurrencyDto(1L, "test", Code.USD, LocalDate.now(), 1.0);
        List<CurrencyDto> dtoList = List.of(currencyDto);
        Currency currency = new Currency(1L, "test", Code.USD, LocalDate.now(), 1.0);
        List<Currency> list = List.of(currency);
        when(nbpClient.getSingleCurrencyFromWholeMonth(Code.USD)).thenReturn(dtoList);
        when(currencyMapper.mapToCurrencyList(anyList())).thenReturn(list);
        when(currencyMapper.mapToCurrencyListDto(anyList())).thenReturn(dtoList);
        when(currencyService.saveCurrencyList(anyList())).thenReturn(list);
        //When
        List<CurrencyDto> result = currencyFacade.fetchSingleCurrencyFromWholeMonth(Code.USD);
        //Then
        assertThat(result).isNotNull();
        assertEquals(dtoList, result);
    }

    @Test
    void getAllCurrenciesWithGivenCode() throws CurrencyNotFoundException {
        //Given
        CurrencyDto currencyDto = new CurrencyDto(1L, "test", Code.USD, LocalDate.now(), 1.0);
        List<CurrencyDto> dtoList = List.of(currencyDto);
        Currency currency = new Currency(1L, "test", Code.USD, LocalDate.now(), 1.0);
        List<Currency> list = List.of(currency);
        when(currencyService.getAllCurrenciesWithGivenCode(Code.USD)).thenReturn(list);
        when(currencyMapper.mapToCurrencyListDto(anyList())).thenReturn(dtoList);
        //When
        List<CurrencyDto> result = currencyFacade.getAllCurrenciesWithGivenCode(Code.USD);
        //Then
        assertThat(result).isNotNull();
        assertEquals(dtoList, result);
    }

    @Test
    void getLatestCurrencyList() {
        //Given
        CurrencyDto currencyDto = new CurrencyDto(1L, "test", Code.USD, LocalDate.now(), 1.0);
        List<CurrencyDto> dtoList = List.of(currencyDto);
        Currency currency = new Currency(1L, "test", Code.USD, LocalDate.now(), 1.0);
        List<Currency> list = List.of(currency);
        when(currencyService.getLatestCurrencyList()).thenReturn(list);
        when(currencyMapper.mapToCurrencyListDto(anyList())).thenReturn(dtoList);
        //When
        List<CurrencyDto> result = currencyFacade.getLatestCurrencyList();
        //Then
        assertThat(result).isNotNull();
        assertEquals(dtoList, result);
    }
    
    @Test
    void getLatestExchangeRate() throws CodeNotFoundException {
        //Given
        Double rate = 1.0;
        Code code = Code.USD;
        when(calculator.getLatestExchangeRate(code)).thenReturn(rate);
        //When
        Double result = currencyFacade.getLatestExchangeRate(code);
        //Then
        assertEquals(rate, result);
    }

    @Test
    void calculate() throws CodeNotFoundException {
        //Given
        Double rate = 1.0;
        Double value = 1.0;
        Code code1 = Code.USD;
        Code code2 = Code.EUR;
        when(calculator.calculateCurrency(code1, code2, value)).thenReturn(rate);
        //When
        Double result = currencyFacade.calculate(code1, code2, value);
        //Then
        assertEquals(rate, value);
    }
}