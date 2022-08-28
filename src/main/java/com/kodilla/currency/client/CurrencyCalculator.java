package com.kodilla.currency.client;

import com.kodilla.currency.domain.CurrencyCalculatorHelper;
import com.kodilla.currency.entity.Code;
import com.kodilla.currency.entity.CryptoCurrency;
import com.kodilla.currency.entity.Currency;
import com.kodilla.currency.exception.CodeNotFoundException;
import com.kodilla.currency.repository.CryptoCurrencyRepository;
import com.kodilla.currency.repository.CurrencyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CurrencyCalculator {

    private final CurrencyRepository currencyRepository;
    private final CryptoCurrencyRepository cryptoCurrencyRepository;
    private final CurrencyCalculatorHelper currencyCalculatorHelper;

    public Double calculateCurrency(Code code1, Code code2, Double value) throws CodeNotFoundException {
        Double currencyOneRate = getLatestExchangeRate(code1);
        Double currencyTwoRate = getLatestExchangeRate(code2);
        return (currencyOneRate * value) / currencyTwoRate;
    }

    public Double getLatestExchangeRate(Code code) throws CodeNotFoundException {
        if(!(currencyRepository.findByCode(code).isEmpty() && cryptoCurrencyRepository.findByCode(code).isEmpty())){
            if(currencyCalculatorHelper.getCurrencyList().contains(code)){
                return currencyRepository.findByCode(code).stream()
                        .sorted(Comparator.comparing(Currency::getEffectiveDate))
                        .skip(currencyRepository.findByCode(code).size() - 1)
                        .collect(Collectors.toList()).get(0).getExchangeRate();
            } else {
                return cryptoCurrencyRepository.findByCode(code).stream()
                        .sorted(Comparator.comparing(CryptoCurrency::getEffectiveDate))
                        .skip(cryptoCurrencyRepository.findByCode(code).size() - 1)
                        .collect(Collectors.toList()).get(0).getExchangeRate();
            }
        } else throw new CodeNotFoundException();
    }
}
