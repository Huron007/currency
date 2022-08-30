package com.kodilla.currency.client;

import com.kodilla.currency.config.CoinGeckoConfig;
import com.kodilla.currency.domain.CoinGeckoResponseConverter;
import com.kodilla.currency.dto.CoinGeckoApiResponseDto;
import com.kodilla.currency.dto.CoinGeckoResponseWithSpecifiedDateDto;
import com.kodilla.currency.dto.CryptoCurrencyDto;
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
class CoinGeckoClientTest {

    @InjectMocks
    private CoinGeckoClient coinGeckoClient;

    @Mock
    private CoinGeckoConfig config;

    @Mock
    private CoinGeckoResponseConverter converter;

    @Mock
    private RestTemplate template;

    @Test
    void getTopTenCrypto() throws URISyntaxException {
        //Given
        CryptoCurrencyDto cryptoCurrencyDto = new CryptoCurrencyDto(1L, "test", Code.btc, LocalDate.now(), 1.0);
        List<CryptoCurrencyDto> list = List.of(cryptoCurrencyDto);
        CoinGeckoApiResponseDto[] responseDtos = new CoinGeckoApiResponseDto[0];
        URI url = new URI("http://test.com/coins/markets?vs_currency=pln");
        when(config.getCoinGeckoApiEndpoint()).thenReturn("http://test.com");
        when(template.getForObject(url, CoinGeckoApiResponseDto[].class)).thenReturn(responseDtos);
        when(converter.convertToCryptoCurrencyList(any(CoinGeckoApiResponseDto[].class))).thenReturn(list);
        //When
        List<CryptoCurrencyDto> result = coinGeckoClient.getTopTenCrypto();
        //Then
        assertEquals(list, result);
    }

    @Test
    void getSingleCryptoFromWholeMonth() throws URISyntaxException {
        //Given
        CryptoCurrencyDto cryptoCurrencyDto = new CryptoCurrencyDto(1L, "test", Code.btc, LocalDate.now(), 1.0);
        List<CryptoCurrencyDto> list = List.of(cryptoCurrencyDto);
        CoinGeckoResponseWithSpecifiedDateDto response = new CoinGeckoResponseWithSpecifiedDateDto();
        URI url = new URI("http://test.com/coins/bitcoin/market_chart?vs_currency=pln&days=30");
        when(config.getCoinGeckoApiEndpoint()).thenReturn("http://test.com");
        when(template.getForObject(url, CoinGeckoResponseWithSpecifiedDateDto.class)).thenReturn(response);
        when(converter.convertToSingleCurrencyFromWholeMonth(any(CoinGeckoResponseWithSpecifiedDateDto.class), any(Code.class))).thenReturn(list);
        //When
        List<CryptoCurrencyDto> result = coinGeckoClient.getSingleCryptoFromWholeMonth(Code.btc);
        //Then
        assertEquals(list, result);
    }
}