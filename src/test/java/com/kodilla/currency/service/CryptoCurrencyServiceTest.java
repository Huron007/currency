package com.kodilla.currency.service;

import com.kodilla.currency.entity.Code;
import com.kodilla.currency.entity.CryptoCurrency;
import com.kodilla.currency.entity.Currency;
import com.kodilla.currency.exception.CryptoCurrencyNotFoundException;
import com.kodilla.currency.exception.CurrencyNotFoundException;
import com.kodilla.currency.exception.DuplicateCryptoCurrencyException;
import com.kodilla.currency.exception.DuplicateCurrencyException;
import com.kodilla.currency.repository.CryptoCurrencyRepository;
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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CryptoCurrencyServiceTest {

    @InjectMocks
    private CryptoCurrencyService cryptoCurrencyService;

    @Mock
    private CryptoCurrencyRepository cryptoCurrencyRepository;

    @Test
    void getAllCurrencies() {
        //Given
        CryptoCurrency cryptoCurrency = new CryptoCurrency(1L, "test", Code.USD, LocalDate.now(), 1.0);
        List<CryptoCurrency> list = List.of(cryptoCurrency);
        when(cryptoCurrencyRepository.findAll()).thenReturn(list);
        //When
        List<CryptoCurrency> result = cryptoCurrencyService.getAllCurrencies();
        //Then
        assertEquals(list, result);
    }

    @Test
    void getCryptoCurrency() throws CryptoCurrencyNotFoundException {
        //Given
        CryptoCurrency cryptoCurrency = new CryptoCurrency(1L, "test", Code.USD, LocalDate.now(), 1.0);
        when(cryptoCurrencyRepository.existsById(1L)).thenReturn(true);
        when(cryptoCurrencyRepository.findById(1L)).thenReturn(Optional.of(cryptoCurrency));
        //When
        CryptoCurrency result = cryptoCurrencyService.getCryptoCurrency(1L);
        //Then
        assertEquals(cryptoCurrency, result);
    }

    @Test
    void saveCryptoCurrency() throws DuplicateCryptoCurrencyException {
        //Given
        CryptoCurrency cryptoCurrency = new CryptoCurrency(1L, "test", Code.USD, LocalDate.now(), 1.0);
        when(cryptoCurrencyRepository.save(any(CryptoCurrency.class))).thenReturn(cryptoCurrency);
        //When
        CryptoCurrency result = cryptoCurrencyService.saveCryptoCurrency(cryptoCurrency);
        //Then
        assertEquals(cryptoCurrency, result);
    }

    @Test
    void saveCryptoCurrencyList() {
        //Given
        CryptoCurrency cryptoCurrency = new CryptoCurrency(1L, "test", Code.USD, LocalDate.now(), 1.0);
        List<CryptoCurrency> list = List.of(cryptoCurrency);
        when(cryptoCurrencyRepository.save(any(CryptoCurrency.class))).thenReturn(cryptoCurrency);
        //When
        List<CryptoCurrency> result = cryptoCurrencyService.saveCryptoCurrencyList(list);
        //Then
        assertEquals(list, result);
    }
    @Test
    void updateCryptoCurrency() throws CryptoCurrencyNotFoundException {
        //Given
        CryptoCurrency cryptoCurrency = new CryptoCurrency(1L, "test", Code.USD, LocalDate.now(), 1.0);
        when(cryptoCurrencyRepository.save(any(CryptoCurrency.class))).thenReturn(cryptoCurrency);
        when(cryptoCurrencyRepository.existsById(1L)).thenReturn(true);
        //When
        CryptoCurrency result = cryptoCurrencyService.updateCryptoCurrency(cryptoCurrency);
        //Then
        assertEquals(cryptoCurrency, result);
    }

    @Test
    void updateCryptoCurrencyList() throws CryptoCurrencyNotFoundException {
        //Given
        CryptoCurrency cryptoCurrency = new CryptoCurrency(1L, "test", Code.USD, LocalDate.now(), 1.0);
        List<CryptoCurrency> list = List.of(cryptoCurrency);
        when(cryptoCurrencyRepository.save(any(CryptoCurrency.class))).thenReturn(cryptoCurrency);
        //When
        List<CryptoCurrency> result = cryptoCurrencyService.updateCryptoCurrencyList(list);
        //Then
        assertEquals(list, result);
    }

    @Test
    void deleteCryptoCurrency() throws CryptoCurrencyNotFoundException {
        //Given
        when(cryptoCurrencyRepository.existsById(1L)).thenReturn(true);
        //When
        cryptoCurrencyService.deleteCryptoCurrency(1L);
        //Then
        verify(cryptoCurrencyRepository, times(1)).deleteById(1L);
    }

    @Test
    void getAllCurrenciesWithGivenCode() throws CryptoCurrencyNotFoundException {
        //Given
        CryptoCurrency cryptoCurrency = new CryptoCurrency(1L, "test", Code.USD, LocalDate.now(), 1.0);
        List<CryptoCurrency> list = List.of(cryptoCurrency);
        when(cryptoCurrencyRepository.findByCode(Code.USD)).thenReturn(list);
        //When
        List<CryptoCurrency> result = cryptoCurrencyService.getAllCryptoCurrenciesWithGivenCode(Code.USD);
        //Then
        assertEquals(list, result);
    }

    @Test
    void getLatestCryptoCurrencyList() {
        //Given
        CryptoCurrency cryptoCurrency = new CryptoCurrency(1L, "test", Code.USD, LocalDate.now(), 1.0);
        List<CryptoCurrency> list = List.of(cryptoCurrency);
        when(cryptoCurrencyRepository.findByEffectiveDate(LocalDate.now())).thenReturn(list);
        //When
        List<CryptoCurrency> result = cryptoCurrencyService.getLatestCryptoCurrencyList();
        //Then
        assertEquals(list, result);
    }
}