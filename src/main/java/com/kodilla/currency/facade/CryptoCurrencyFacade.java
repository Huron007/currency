package com.kodilla.currency.facade;

import com.kodilla.currency.dto.CryptoCurrencyDto;
import com.kodilla.currency.exception.CryptoCurrencyNotFoundException;
import com.kodilla.currency.exception.DuplicateCryptoCurrencyException;
import com.kodilla.currency.mapper.CryptoCurrencyMapper;
import com.kodilla.currency.service.CryptoCurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CryptoCurrencyFacade {

    private final CryptoCurrencyMapper cryptoCurrencyMapper;
    private final CryptoCurrencyService cryptoCurrencyService;

    //Operations on database
    public List<CryptoCurrencyDto> getAllCurrencies(){
        return cryptoCurrencyMapper.mapToCryptoCurrencyListDto(cryptoCurrencyService.getAllCurrencies());
    }

    public CryptoCurrencyDto getSingleCryptoCurrency(final Long cryptoCurrencyId) throws CryptoCurrencyNotFoundException {
        return cryptoCurrencyMapper.mapToCryptoCurrencyDto(cryptoCurrencyService.getCryptoCurrency(cryptoCurrencyId));
    }

    public CryptoCurrencyDto saveSingleCryptoCurrency(final CryptoCurrencyDto cryptoCurrencyDto) throws DuplicateCryptoCurrencyException {
        return cryptoCurrencyMapper.mapToCryptoCurrencyDto(cryptoCurrencyService.saveCryptoCurrency(cryptoCurrencyMapper.mapToCryptoCurrency(cryptoCurrencyDto)));
    }

    public List<CryptoCurrencyDto> saveCryptoCurrencyTable(final List<CryptoCurrencyDto> cryptoCurrencyDtoList) {
        return cryptoCurrencyMapper.mapToCryptoCurrencyListDto(cryptoCurrencyService.saveCryptoCurrencyList(cryptoCurrencyMapper.mapToCryptoCurrencyList(cryptoCurrencyDtoList)));
    }

    public CryptoCurrencyDto updateSingleCryptoCurrency(final CryptoCurrencyDto cryptoCurrencyDto) throws CryptoCurrencyNotFoundException {
        return cryptoCurrencyMapper.mapToCryptoCurrencyDto(cryptoCurrencyService.updateCryptoCurrency(cryptoCurrencyMapper.mapToCryptoCurrency(cryptoCurrencyDto)));
    }

    public List<CryptoCurrencyDto> updateCryptoCurrencyTable(final List<CryptoCurrencyDto> cryptoCurrencyDtoList) throws CryptoCurrencyNotFoundException {
        return cryptoCurrencyMapper.mapToCryptoCurrencyListDto(cryptoCurrencyService.updateCryptoCurrencyList(cryptoCurrencyMapper.mapToCryptoCurrencyList(cryptoCurrencyDtoList)));
    }

    public void deleteCryptoCurrency(final Long cryptoCurrencyId) throws CryptoCurrencyNotFoundException {
        cryptoCurrencyService.deleteCryptoCurrency(cryptoCurrencyId);
    }
}
