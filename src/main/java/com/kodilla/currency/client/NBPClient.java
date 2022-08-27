package com.kodilla.currency.client;

import com.kodilla.currency.config.NBPConfig;
import com.kodilla.currency.domain.NBPResponseConverter;
import com.kodilla.currency.dto.NBPResponseDto;
import com.kodilla.currency.dto.NBPResponseTableDto;
import com.kodilla.currency.dto.CurrencyDto;
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
public class NBPClient {

    private final RestTemplate restTemplate;
    private final NBPConfig nbpConfig;
    private final NBPResponseConverter nbpResponseConverter;

    public URI getUrl(UrlCode urlCode) {
        return getUrl(urlCode, null);
    }

    public URI getUrl(UrlCode urlCode, Code code) {
        switch (urlCode){
            case SINGLE_CURRENCY:
                return UriComponentsBuilder.fromHttpUrl(nbpConfig.getNbpApiEndpoint() + "/rates/a/" + code)
                        .build()
                        .encode()
                        .toUri();
            case TABLE:
                return UriComponentsBuilder.fromHttpUrl(nbpConfig.getNbpApiEndpoint() + "/tables/a/")
                        .build()
                        .encode()
                        .toUri();
            case SINGLE_CURRENCY_WHOLE_MONTH:
                return UriComponentsBuilder.fromHttpUrl(nbpConfig.getNbpApiEndpoint() + "/rates/a/" + code + "/last/30")
                        .build()
                        .encode()
                        .toUri();
            case TABLE_WHOLE_MONTH:
                return UriComponentsBuilder.fromHttpUrl(nbpConfig.getNbpApiEndpoint() + "/tables/a/last/30")
                        .build()
                        .encode()
                        .toUri();
            default:
                return null;
        }
    }

    public CurrencyDto getSingleCurrency(Code code){
        try {
            CurrencyDto apiResponse = nbpResponseConverter.convertToSingleCurrency(Objects.requireNonNull(restTemplate.getForObject(getUrl(UrlCode.SINGLE_CURRENCY, code), NBPResponseDto.class)));
            if (apiResponse == null) throw new NoSuchElementException("No value present");
            return apiResponse;
        } catch (RestClientException e){
            return new CurrencyDto();
        }
    }

    public List<CurrencyDto> getTable(){
        try{
            List<CurrencyDto> apiResponse = nbpResponseConverter.convertToCurrencyTable(Objects.requireNonNull(restTemplate.getForObject(getUrl(UrlCode.TABLE), NBPResponseTableDto[].class)));
            if (apiResponse == null) throw new NoSuchElementException("No value present");
            return apiResponse;
        } catch (RestClientException e){
            return Collections.emptyList();
        }
    }

    public List<CurrencyDto> getSingleCurrencyFromWholeMonth(Code code){
        try {
            List<CurrencyDto> apiResponse = nbpResponseConverter.convertToSingleCurrencyFromWholeMonth(Objects.requireNonNull(restTemplate.getForObject(getUrl(UrlCode.SINGLE_CURRENCY_WHOLE_MONTH, code), NBPResponseDto.class)));
            if (apiResponse == null) throw new NoSuchElementException("No value present");
            return apiResponse;
        } catch (RestClientException e){
            return Collections.emptyList();
        }
    }

    public List<CurrencyDto> getTableFromWholeMonth(){
        try {
            List<CurrencyDto> apiResponse = nbpResponseConverter.convertToCurrencyTableFromWholeMonth(Objects.requireNonNull(restTemplate.getForObject(getUrl(UrlCode.TABLE_WHOLE_MONTH), NBPResponseTableDto[].class)));
            if (apiResponse == null) throw new NoSuchElementException("No value present");
            return apiResponse;
        } catch (RestClientException e){
            return Collections.emptyList();
        }
    }
}
