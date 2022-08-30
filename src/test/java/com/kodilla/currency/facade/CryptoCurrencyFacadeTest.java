package com.kodilla.currency.facade;

import com.kodilla.currency.client.CoinGeckoClient;
import com.kodilla.currency.dto.CryptoCurrencyDto;
import com.kodilla.currency.dto.CurrencyDto;
import com.kodilla.currency.entity.Code;
import com.kodilla.currency.entity.CryptoCurrency;
import com.kodilla.currency.entity.Currency;
import com.kodilla.currency.exception.CryptoCurrencyNotFoundException;
import com.kodilla.currency.exception.CurrencyNotFoundException;
import com.kodilla.currency.exception.DuplicateCryptoCurrencyException;
import com.kodilla.currency.exception.DuplicateCurrencyException;
import com.kodilla.currency.mapper.CryptoCurrencyMapper;
import com.kodilla.currency.mapper.CurrencyMapper;
import com.kodilla.currency.service.CryptoCurrencyService;
import com.kodilla.currency.service.CurrencyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CryptoCurrencyFacadeTest {

    @InjectMocks
    private CryptoCurrencyFacade cryptoCurrencyFacade;

    @Mock
    private CryptoCurrencyService cryptoCurrencyService;

    @Mock
    private CryptoCurrencyMapper cryptoCurrencyMapper;

    @Mock
    private CoinGeckoClient coinGeckoClient;
    
    @Test
    void getAllCurrencies() {
        //Given
        CryptoCurrencyDto cryptoCurrencyDto = new CryptoCurrencyDto(1L, "test", Code.btc, LocalDate.now(), 1.0);
        List<CryptoCurrencyDto> dtoList = List.of(cryptoCurrencyDto);
        CryptoCurrency cryptoCurrency = new CryptoCurrency(1L, "test", Code.btc, LocalDate.now(), 1.0);
        List<CryptoCurrency> list = List.of(cryptoCurrency);
        when(cryptoCurrencyService.getAllCurrencies()).thenReturn(list);
        when(cryptoCurrencyMapper.mapToCryptoCurrencyListDto(anyList())).thenReturn(dtoList);
        //When
        List<CryptoCurrencyDto> result = cryptoCurrencyFacade.getAllCurrencies();
        //Then
        assertThat(result).isNotNull();
        assertEquals(dtoList, result);
    }

    @Test
    void getSingleCryptoCurrency() throws CryptoCurrencyNotFoundException {
        //Given
        CryptoCurrency cryptoCurrency = new CryptoCurrency(1L, "test", Code.btc, LocalDate.now(), 1.0);
        CryptoCurrencyDto cryptoCurrencyDto = new CryptoCurrencyDto(1L, "test", Code.btc, LocalDate.now(), 1.0);
        when(cryptoCurrencyService.getCryptoCurrency(1L)).thenReturn(cryptoCurrency);
        when(cryptoCurrencyMapper.mapToCryptoCurrencyDto(any(CryptoCurrency.class))).thenReturn(cryptoCurrencyDto);
        //When
        CryptoCurrencyDto result = cryptoCurrencyFacade.getSingleCryptoCurrency(1L);
        //Then
        assertEquals(cryptoCurrencyDto, result);
    }

    @Test
    void saveSingleCryptoCurrency() throws DuplicateCryptoCurrencyException {
        //Given
        CryptoCurrency cryptoCurrency = new CryptoCurrency(1L, "test", Code.btc, LocalDate.now(), 1.0);
        CryptoCurrencyDto cryptoCurrencyDto = new CryptoCurrencyDto(1L, "test", Code.btc, LocalDate.now(), 1.0);
        when(cryptoCurrencyService.saveCryptoCurrency(any(CryptoCurrency.class))).thenReturn(cryptoCurrency);
        when(cryptoCurrencyMapper.mapToCryptoCurrency(any(CryptoCurrencyDto.class))).thenReturn(cryptoCurrency);
        when(cryptoCurrencyMapper.mapToCryptoCurrencyDto(any(CryptoCurrency.class))).thenReturn(cryptoCurrencyDto);
        //When
        CryptoCurrencyDto result = cryptoCurrencyFacade.saveSingleCryptoCurrency(cryptoCurrencyDto);
        //Then
        assertEquals(cryptoCurrencyDto, result);
    }

    @Test
    void saveCryptoCurrencyList() {
        //Given
        CryptoCurrencyDto cryptoCurrencyDto = new CryptoCurrencyDto(1L, "test", Code.btc, LocalDate.now(), 1.0);
        List<CryptoCurrencyDto> dtoList = List.of(cryptoCurrencyDto);
        CryptoCurrency cryptoCurrency = new CryptoCurrency(1L, "test", Code.btc, LocalDate.now(), 1.0);
        List<CryptoCurrency> list = List.of(cryptoCurrency);
        when(cryptoCurrencyService.saveCryptoCurrencyList(anyList())).thenReturn(list);
        when(cryptoCurrencyMapper.mapToCryptoCurrencyListDto(anyList())).thenReturn(dtoList);
        when(cryptoCurrencyMapper.mapToCryptoCurrencyList(anyList())).thenReturn(list);
        //When
        List<CryptoCurrencyDto> result = cryptoCurrencyFacade.saveCryptoCurrencyTable(dtoList);
        //Then
        assertThat(result).isNotNull();
        assertEquals(dtoList, result);

    }

    @Test
    void updateSingleCryptoCurrency() throws CryptoCurrencyNotFoundException {
        //Given
        CryptoCurrency cryptoCurrency = new CryptoCurrency(1L, "test", Code.btc, LocalDate.now(), 1.0);
        CryptoCurrencyDto cryptoCurrencyDto = new CryptoCurrencyDto(1L, "test", Code.btc, LocalDate.now(), 1.0);
        when(cryptoCurrencyService.updateCryptoCurrency(any(CryptoCurrency.class))).thenReturn(cryptoCurrency);
        when(cryptoCurrencyMapper.mapToCryptoCurrency(any(CryptoCurrencyDto.class))).thenReturn(cryptoCurrency);
        when(cryptoCurrencyMapper.mapToCryptoCurrencyDto(any(CryptoCurrency.class))).thenReturn(cryptoCurrencyDto);
        //When
        CryptoCurrencyDto result = cryptoCurrencyFacade.updateSingleCryptoCurrency(cryptoCurrencyDto);
        //Then
        assertEquals(cryptoCurrencyDto, result);
    }

    @Test
    void updateCryptoCurrencyList() throws CryptoCurrencyNotFoundException {
        //Given
        CryptoCurrencyDto cryptoCurrencyDto = new CryptoCurrencyDto(1L, "test", Code.btc, LocalDate.now(), 1.0);
        List<CryptoCurrencyDto> dtoList = List.of(cryptoCurrencyDto);
        CryptoCurrency cryptoCurrency = new CryptoCurrency(1L, "test", Code.btc, LocalDate.now(), 1.0);
        List<CryptoCurrency> list = List.of(cryptoCurrency);
        when(cryptoCurrencyService.updateCryptoCurrencyList(anyList())).thenReturn(list);
        when(cryptoCurrencyMapper.mapToCryptoCurrencyListDto(anyList())).thenReturn(dtoList);
        when(cryptoCurrencyMapper.mapToCryptoCurrencyList(anyList())).thenReturn(list);
        //When
        List<CryptoCurrencyDto> result = cryptoCurrencyFacade.updateCryptoCurrencyTable(dtoList);
        //Then
        assertThat(result).isNotNull();
        assertEquals(dtoList, result);
    }

    @Test
    void deleteCryptoCurrency() throws CryptoCurrencyNotFoundException {
        //Given & When
        cryptoCurrencyFacade.deleteCryptoCurrency(1L);
        //Then
        verify(cryptoCurrencyService, times(1)).deleteCryptoCurrency(1L);
    }

    @Test
    void fetchTopTenCryptoList() {
        //Given
        CryptoCurrencyDto cryptoCurrencyDto = new CryptoCurrencyDto(1L, "test", Code.btc, LocalDate.now(), 1.0);
        List<CryptoCurrencyDto> dtoList = List.of(cryptoCurrencyDto);
        CryptoCurrency cryptoCurrency = new CryptoCurrency(1L, "test", Code.btc, LocalDate.now(), 1.0);
        List<CryptoCurrency> list = List.of(cryptoCurrency);
        when(cryptoCurrencyService.saveCryptoCurrencyList(anyList())).thenReturn(list);
        when(cryptoCurrencyMapper.mapToCryptoCurrencyListDto(anyList())).thenReturn(dtoList);
        when(cryptoCurrencyMapper.mapToCryptoCurrencyList(anyList())).thenReturn(list);
        //When
        List<CryptoCurrencyDto> result = cryptoCurrencyFacade.fetchTopTenCryptoList();
        //Then
        assertThat(result).isNotNull();
        assertEquals(dtoList, result);
    }

    @Test
    void fetchSingleCryptoFromWholeMonth() {
        //Given
        CryptoCurrencyDto cryptoCurrencyDto = new CryptoCurrencyDto(1L, "test", Code.btc, LocalDate.now(), 1.0);
        List<CryptoCurrencyDto> dtoList = List.of(cryptoCurrencyDto);
        CryptoCurrency cryptoCurrency = new CryptoCurrency(1L, "test", Code.btc, LocalDate.now(), 1.0);
        List<CryptoCurrency> list = List.of(cryptoCurrency);
        when(cryptoCurrencyService.saveCryptoCurrencyList(anyList())).thenReturn(list);
        when(cryptoCurrencyMapper.mapToCryptoCurrencyListDto(anyList())).thenReturn(dtoList);
        when(cryptoCurrencyMapper.mapToCryptoCurrencyList(anyList())).thenReturn(list);
        //When
        List<CryptoCurrencyDto> result = cryptoCurrencyFacade.fetchSingleCryptoFromWholeMonth(Code.btc);
        //Then
        assertThat(result).isNotNull();
        assertEquals(dtoList, result);
    }

    @Test
    void getAllCryptoCurrenciesWithGivenCode() throws CryptoCurrencyNotFoundException {
        //Given
        CryptoCurrencyDto cryptoCurrencyDto = new CryptoCurrencyDto(1L, "test", Code.btc, LocalDate.now(), 1.0);
        List<CryptoCurrencyDto> dtoList = List.of(cryptoCurrencyDto);
        CryptoCurrency cryptoCurrency = new CryptoCurrency(1L, "test", Code.btc, LocalDate.now(), 1.0);
        List<CryptoCurrency> list = List.of(cryptoCurrency);
        when(cryptoCurrencyService.getAllCryptoCurrenciesWithGivenCode(Code.btc)).thenReturn(list);
        when(cryptoCurrencyMapper.mapToCryptoCurrencyListDto(anyList())).thenReturn(dtoList);
        //When
        List<CryptoCurrencyDto> result = cryptoCurrencyFacade.getAllCryptoCurrenciesWithGivenCode(Code.btc);
        //Then
        assertThat(result).isNotNull();
        assertEquals(dtoList, result);
    }

    @Test
    void getLatestCryptoCurrencyList() {
        //Given
        CryptoCurrencyDto cryptoCurrencyDto = new CryptoCurrencyDto(1L, "test", Code.btc, LocalDate.now(), 1.0);
        List<CryptoCurrencyDto> dtoList = List.of(cryptoCurrencyDto);
        CryptoCurrency cryptoCurrency = new CryptoCurrency(1L, "test", Code.btc, LocalDate.now(), 1.0);
        List<CryptoCurrency> list = List.of(cryptoCurrency);
        when(cryptoCurrencyService.getLatestCryptoCurrencyList()).thenReturn(list);
        when(cryptoCurrencyMapper.mapToCryptoCurrencyListDto(anyList())).thenReturn(dtoList);
        //When
        List<CryptoCurrencyDto> result = cryptoCurrencyFacade.getLatestCryptoCurrencyList();
        //Then
        assertThat(result).isNotNull();
        assertEquals(dtoList, result);
    }
}