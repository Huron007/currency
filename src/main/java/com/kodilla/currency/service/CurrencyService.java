package com.kodilla.currency.service;

import com.kodilla.currency.entity.Code;
import com.kodilla.currency.entity.Currency;
import com.kodilla.currency.exception.CurrencyNotFoundException;
import com.kodilla.currency.exception.DuplicateCurrencyException;
import com.kodilla.currency.repository.CurrencyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CurrencyService {

    private final CurrencyRepository currencyRepository;

    public List<Currency> getAllCurrencies() {
        return currencyRepository.findAll();
    }

    public List<Currency> getAllCurrenciesWithGivenCode(Code code) throws CurrencyNotFoundException{
        if(!currencyRepository.findByCode(code).isEmpty()){
            return currencyRepository.findByCode(code);
        } else throw new CurrencyNotFoundException();
    }

    public List<Currency> getLatestCurrencyList(){
        LocalDate date = LocalDate.now();
        List<Currency> list = currencyRepository.findByEffectiveDate(date);
        while (list.isEmpty()){
            date = date.minusDays(1);
            list = currencyRepository.findByEffectiveDate(date);
        }
        return list;
    }

    public Currency getCurrency(final Long currencyId) throws CurrencyNotFoundException {
        if(currencyRepository.existsById(currencyId)){
            if(currencyRepository.findById(currencyId).isPresent()){
                return currencyRepository.findById(currencyId).get();
            } else throw new CurrencyNotFoundException();
        } else throw new CurrencyNotFoundException();
    }

    public Currency saveCurrency(final Currency currency) throws DuplicateCurrencyException {
        if(currencyRepository.checkDuplicates(currency.getCode(), currency.getEffectiveDate()).isEmpty()){
            return currencyRepository.save(currency);
        } else throw new DuplicateCurrencyException();
    }

    public List<Currency> saveCurrencyList(final List<Currency> currencyList){
            return currencyList.stream()
                    .filter(Predicate.not(e -> currencyRepository.checkDuplicates(e.getCode(), e.getEffectiveDate()).stream().anyMatch(a -> a.getCode().equals(e.getCode()) && a.getEffectiveDate().equals(e.getEffectiveDate()))))
                    .map(currencyRepository::save)
                    .collect(Collectors.toList());
    }

    public Currency updateCurrency(final Currency currency) throws CurrencyNotFoundException{
        if(currencyRepository.existsById(currency.getId())){
            return currencyRepository.save(currency);
        } else throw new CurrencyNotFoundException();
    }

    public List<Currency> updateCurrencyList(final List<Currency> currencyList) throws CurrencyNotFoundException{
        if(currencyList.stream().map(e -> currencyRepository.findById(e.getId()).isPresent()).count() == currencyList.size()){
            return currencyList.stream()
                    .map(currencyRepository::save)
                    .collect(Collectors.toList());
        } else throw new CurrencyNotFoundException();
    }

    public void deleteCurrency(final Long currencyId) throws CurrencyNotFoundException {
        if(currencyRepository.existsById(currencyId)){
            currencyRepository.deleteById(currencyId);
        } else throw new CurrencyNotFoundException();
    }
}
