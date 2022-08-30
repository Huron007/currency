package com.kodilla.currency.service;

import com.kodilla.currency.entity.Code;
import com.kodilla.currency.entity.Currency;
import com.kodilla.currency.exception.CurrencyNotFoundException;
import com.kodilla.currency.exception.DuplicateCurrencyException;
import com.kodilla.currency.repository.CurrencyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CurrencyServiceTest {

    @InjectMocks
    private CurrencyService currencyService;

    @Mock
    private CurrencyRepository currencyRepository;

    @Test
    void getAllCurrencies() {
        //Given
        Currency currency = new Currency(1L, "test", Code.USD, LocalDate.now(), 1.0);
        List<Currency> list = List.of(currency);
        when(currencyRepository.findAll()).thenReturn(list);
        //When
        List<Currency> result = currencyService.getAllCurrencies();
        //Then
        assertEquals(list, result);
    }

    @Test
    void getCurrency() throws CurrencyNotFoundException {
        //Given
        Currency currency = new Currency(1L, "test", Code.USD, LocalDate.now(), 1.0);
        when(currencyRepository.existsById(1L)).thenReturn(true);
        when(currencyRepository.findById(1L)).thenReturn(Optional.of(currency));
        //When
        Currency result = currencyService.getCurrency(1L);
        //Then
        assertEquals(currency, result);
    }

    @Test
    void saveCurrency() throws DuplicateCurrencyException {
        //Given
        Currency currency = new Currency(1L, "test", Code.USD, LocalDate.now(), 1.0);
        when(currencyRepository.save(any(Currency.class))).thenReturn(currency);
        //When
        Currency result = currencyService.saveCurrency(currency);
        //Then
        assertEquals(currency, result);
    }

    @Test
    void saveCurrencyList() {
        //Given
        Currency currency = new Currency(1L, "test", Code.USD, LocalDate.now(), 1.0);
        List<Currency> list = List.of(currency);
        when(currencyRepository.save(any(Currency.class))).thenReturn(currency);
        //When
        List<Currency> result = currencyService.saveCurrencyList(list);
        //Then
        assertEquals(list, result);
    }
    @Test
    void updateCurrency() throws CurrencyNotFoundException {
        //Given
        Currency currency = new Currency(1L, "test", Code.USD, LocalDate.now(), 1.0);
        when(currencyRepository.save(any(Currency.class))).thenReturn(currency);
        when(currencyRepository.existsById(1L)).thenReturn(true);
        //When
        Currency result = currencyService.updateCurrency(currency);
        //Then
        assertEquals(currency, result);
    }

    @Test
    void updateCurrencyList() throws CurrencyNotFoundException {
        //Given
        Currency currency = new Currency(1L, "test", Code.USD, LocalDate.now(), 1.0);
        List<Currency> list = List.of(currency);
        when(currencyRepository.save(any(Currency.class))).thenReturn(currency);
        //When
        List<Currency> result = currencyService.updateCurrencyList(list);
        //Then
        assertEquals(list, result);
    }

    @Test
    void deleteCurrency() throws CurrencyNotFoundException {
        //Given
        when(currencyRepository.existsById(1L)).thenReturn(true);
        //When
        currencyService.deleteCurrency(1L);
        //Then
        verify(currencyRepository, times(1)).deleteById(1L);
    }
    
    @Test
    void getAllCurrenciesWithGivenCode() throws CurrencyNotFoundException {
        //Given
        Currency currency = new Currency(1L, "test", Code.USD, LocalDate.now(), 1.0);
        List<Currency> list = List.of(currency);
        when(currencyRepository.findByCode(Code.USD)).thenReturn(list);
        //When
        List<Currency> result = currencyService.getAllCurrenciesWithGivenCode(Code.USD);
        //Then
        assertEquals(list, result);
    }

    @Test
    void getLatestCurrencyList() {
        //Given
        Currency currency = new Currency(1L, "test", Code.USD, LocalDate.now(), 1.0);
        List<Currency> list = List.of(currency);
        when(currencyRepository.findByEffectiveDate(LocalDate.now())).thenReturn(list);
        //When
        List<Currency> result = currencyService.getLatestCurrencyList();
        //Then
        assertEquals(list, result);
    }
}