package com.kodilla.currency.service;

import com.kodilla.currency.entity.CryptoCurrency;
import com.kodilla.currency.exception.CryptoCurrencyNotFoundException;
import com.kodilla.currency.exception.DuplicateCryptoCurrencyException;
import com.kodilla.currency.repository.CryptoCurrencyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CryptoCurrencyService {

    private final CryptoCurrencyRepository cryptoCryptoCurrencyRepository;

    public List<CryptoCurrency> getAllCurrencies() {
        return cryptoCryptoCurrencyRepository.findAll();
    }

    public CryptoCurrency getCryptoCurrency(final Long cryptoCryptoCurrencyId) throws CryptoCurrencyNotFoundException {
        if(cryptoCryptoCurrencyRepository.existsById(cryptoCryptoCurrencyId)){
            if(cryptoCryptoCurrencyRepository.findById(cryptoCryptoCurrencyId).isPresent()){
                return cryptoCryptoCurrencyRepository.findById(cryptoCryptoCurrencyId).get();
            } else throw new CryptoCurrencyNotFoundException();
        } else throw new CryptoCurrencyNotFoundException();
    }

    public CryptoCurrency saveCryptoCurrency(final CryptoCurrency cryptoCryptoCurrency) throws DuplicateCryptoCurrencyException {
        if(cryptoCryptoCurrencyRepository.checkDuplicates(cryptoCryptoCurrency.getName(), cryptoCryptoCurrency.getEffectiveDate()).isEmpty()){
            return cryptoCryptoCurrencyRepository.save(cryptoCryptoCurrency);
        } else throw new DuplicateCryptoCurrencyException();
    }

    public List<CryptoCurrency> saveCryptoCurrencyList(final List<CryptoCurrency> cryptoCryptoCurrencyList){
        return cryptoCryptoCurrencyList.stream()
                .filter(Predicate.not(e -> cryptoCryptoCurrencyRepository.checkDuplicates(e.getName(), e.getEffectiveDate()).stream().anyMatch(a -> a.getName().equals(e.getName()) && a.getEffectiveDate().equals(e.getEffectiveDate()))))
                .map(cryptoCryptoCurrencyRepository::save)
                .collect(Collectors.toList());
    }

    public CryptoCurrency updateCryptoCurrency(final CryptoCurrency cryptoCryptoCurrency) throws CryptoCurrencyNotFoundException{
        if(cryptoCryptoCurrencyRepository.existsById(cryptoCryptoCurrency.getId())){
            return cryptoCryptoCurrencyRepository.save(cryptoCryptoCurrency);
        } else throw new CryptoCurrencyNotFoundException();
    }

    public List<CryptoCurrency> updateCryptoCurrencyList(final List<CryptoCurrency> cryptoCryptoCurrencyList) throws CryptoCurrencyNotFoundException{
        if(cryptoCryptoCurrencyList.stream().map(e -> cryptoCryptoCurrencyRepository.findById(e.getId()).isPresent()).count() == cryptoCryptoCurrencyList.size()){
            return cryptoCryptoCurrencyList.stream()
                    .map(cryptoCryptoCurrencyRepository::save)
                    .collect(Collectors.toList());
        } else throw new CryptoCurrencyNotFoundException();
    }

    public void deleteCryptoCurrency(final Long cryptoCryptoCurrencyId) throws CryptoCurrencyNotFoundException {
        if(cryptoCryptoCurrencyRepository.existsById(cryptoCryptoCurrencyId)){
            cryptoCryptoCurrencyRepository.deleteById(cryptoCryptoCurrencyId);
        } else throw new CryptoCurrencyNotFoundException();
    }
}
