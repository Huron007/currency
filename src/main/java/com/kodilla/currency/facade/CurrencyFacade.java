package com.kodilla.currency.facade;

import com.kodilla.currency.client.NBPClient;
import com.kodilla.currency.dto.CurrencyDto;
import com.kodilla.currency.entity.Code;
import com.kodilla.currency.entity.Currency;
import com.kodilla.currency.exception.CurrencyNotFoundException;
import com.kodilla.currency.exception.DuplicateCurrencyException;
import com.kodilla.currency.mapper.CurrencyMapper;
import com.kodilla.currency.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CurrencyFacade {

    private final NBPClient nbpClient;
    private final CurrencyMapper currencyMapper;
    private final CurrencyService currencyService;

    //Operations on NBP API
    public List<CurrencyDto> fetchCurrencyList() {
        List<Currency> currencyList = currencyMapper.mapToCurrencyList(nbpClient.getTable());
        List<Currency> savedList = currencyService.saveCurrencyList(currencyList);
        return currencyMapper.mapToCurrencyListDto(savedList);
    }

    public CurrencyDto fetchSingleCurrency(Code code) throws DuplicateCurrencyException {
        Currency currency = currencyMapper.mapToCurrency(nbpClient.getSingleCurrency(code));
        Currency savedCurrency = currencyService.saveCurrency(currency);
        return currencyMapper.mapToCurrencyDto(savedCurrency);
    }

    public List<CurrencyDto> fetchCurrencyListFromWholeMonth() {
        List<Currency> listOfTables = currencyMapper.mapToCurrencyList(nbpClient.getTableFromWholeMonth());
        List<Currency> savedList = currencyService.saveCurrencyList(listOfTables);
        return currencyMapper.mapToCurrencyListDto(savedList);
    }

    public List<CurrencyDto> fetchSingleCurrencyFromWholeMonth(Code code) {
        List<Currency> currencyList = currencyMapper.mapToCurrencyList(nbpClient.getSingleCurrencyFromWholeMonth(code));
        List<Currency> savedList = currencyService.saveCurrencyList(currencyList);
        return currencyMapper.mapToCurrencyListDto(savedList);
    }

    //Operations on database
    public List<CurrencyDto> getAllCurrencies(){
        return currencyMapper.mapToCurrencyListDto(currencyService.getAllCurrencies());
    }

    public CurrencyDto getSingleCurrency(final Long currencyId) throws CurrencyNotFoundException {
        return currencyMapper.mapToCurrencyDto(currencyService.getCurrency(currencyId));
    }

    public CurrencyDto saveSingleCurrency(final CurrencyDto currencyDto) throws DuplicateCurrencyException {
        return currencyMapper.mapToCurrencyDto(currencyService.saveCurrency(currencyMapper.mapToCurrency(currencyDto)));
    }

    public List<CurrencyDto> saveCurrencyTable(final List<CurrencyDto> currencyDtoList) {
        return currencyMapper.mapToCurrencyListDto(currencyService.saveCurrencyList(currencyMapper.mapToCurrencyList(currencyDtoList)));
    }

    public CurrencyDto updateSingleCurrency(final CurrencyDto currencyDto) throws CurrencyNotFoundException {
        return currencyMapper.mapToCurrencyDto(currencyService.updateCurrency(currencyMapper.mapToCurrency(currencyDto)));
    }

    public List<CurrencyDto> updateCurrencyTable(final List<CurrencyDto> currencyDtoList) throws CurrencyNotFoundException {
        return currencyMapper.mapToCurrencyListDto(currencyService.updateCurrencyList(currencyMapper.mapToCurrencyList(currencyDtoList)));
    }

    public void deleteCurrency(final Long currencyId) throws CurrencyNotFoundException {
        currencyService.deleteCurrency(currencyId);
    }
}
