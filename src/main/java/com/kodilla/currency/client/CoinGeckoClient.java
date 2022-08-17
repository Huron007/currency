package com.kodilla.currency.client;

import com.kodilla.currency.config.CoinGeckoConfig;
import com.kodilla.currency.domain.CoinGeckoApiResponseDto;
import com.kodilla.currency.domain.CoinGeckoResponseConverter;
import com.kodilla.currency.domain.CoinGeckoResponseWithSpecifiedDateDto;
import com.kodilla.currency.dto.CryptoCurrencyDto;
import com.kodilla.currency.entity.Code;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.*;

@Component
@RequiredArgsConstructor
public class CoinGeckoClient {

    private final CoinGeckoConfig coinGeckoConfig;
    private final CoinGeckoResponseConverter coinGeckoResponseConverter;
    private final RestTemplate restTemplate;

    public URI getUrl(UrlCode urlCode) {
        return getUrl(urlCode, null);
    }

    public URI getUrl(UrlCode urlCode, Code code){
        switch (urlCode){
            case TOP_TEN_CRYPTO:
                return UriComponentsBuilder.fromHttpUrl(coinGeckoConfig.getCoinGeckoApiEndpoint() + "/coins/markets?vs_currency=pln")
                        .build()
                        .encode()
                        .toUri();
            case SINGLE_CRYPTO_FROM_WHOLE_MONTH:
                return UriComponentsBuilder.fromHttpUrl(coinGeckoConfig.getCoinGeckoApiEndpoint() + "/coins/" + code.name + "/market_chart?vs_currency=pln&days=30")
                        .build()
                        .encode()
                        .toUri();
            default:
                return null;
        }
    }

    public List<CryptoCurrencyDto> getTopTenCrypto(){
        try{
            List<CryptoCurrencyDto> apiResponse = coinGeckoResponseConverter.convertToCryptoCurrencyList(Objects.requireNonNull(restTemplate.getForObject(getUrl(UrlCode.TOP_TEN_CRYPTO), CoinGeckoApiResponseDto[].class)));
            if(apiResponse == null) throw new NoSuchElementException("No value present");
            return apiResponse;
        } catch (RestClientException e){
            return Collections.emptyList();
        }
    }

    public List<CryptoCurrencyDto> getSingleCryptoFromWholeMonth(Code code){
        try{
            List<CryptoCurrencyDto> apiResponse = coinGeckoResponseConverter.convertToSingleCurrencyFromWholeMonth(Objects.requireNonNull(restTemplate.getForObject(getUrl(UrlCode.SINGLE_CRYPTO_FROM_WHOLE_MONTH, code), CoinGeckoResponseWithSpecifiedDateDto.class)), code);
            if(apiResponse == null) throw new NoSuchElementException("No value present");
            return apiResponse;
        } catch (RestClientException e){
            return Collections.emptyList();
        }
    }
}
