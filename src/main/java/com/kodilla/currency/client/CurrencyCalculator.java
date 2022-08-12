package com.kodilla.currency.client;

import com.kodilla.currency.entity.Code;
import com.kodilla.currency.entity.Currency;
import com.kodilla.currency.repository.CurrencyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CurrencyCalculator {

    private final CurrencyRepository currencyRepository;

    public Double calculateCurrency(Code code1, Code code2, Double value){
        Double currencyOneRate = getLatestExchangeRate(code1);
        Double currencyTwoRate = getLatestExchangeRate(code2);
        return (currencyOneRate * value) / currencyTwoRate;
    }

    public Double getLatestExchangeRate(Code code) {
        return currencyRepository.findByCode(code).stream()
                .sorted(Comparator.comparing(Currency::getEffectiveDate))
                .skip(currencyRepository.findByCode(code).size() - 1)
                .collect(Collectors.toList()).get(0).getExchangeRate();
    }
}
