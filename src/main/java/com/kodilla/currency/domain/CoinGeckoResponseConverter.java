package com.kodilla.currency.domain;

import com.kodilla.currency.dto.CryptoCurrencyDto;
import com.kodilla.currency.entity.Code;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CoinGeckoResponseConverter {

    private final CurrencyCalculatorHelper currencyCalculatorHelper;

    public List<CryptoCurrencyDto> convertToCryptoCurrencyList(CoinGeckoApiResponseDto[] coinGeckoApiResponseDtos){
        List<CryptoCurrencyDto> list = new ArrayList<>();
        for(int i = 0; i < coinGeckoApiResponseDtos.length; i++){
            if(currencyCalculatorHelper.getCryptoCurrencyNames().contains(coinGeckoApiResponseDtos[i].getName())){
                list.add(new CryptoCurrencyDto(
                        0L,
                        coinGeckoApiResponseDtos[i].getName(),
                        Code.valueOf(coinGeckoApiResponseDtos[i].getCode()),
                        coinGeckoApiResponseDtos[i].getEffectiveDate().toLocalDate(),
                        coinGeckoApiResponseDtos[i].getExchangeRate()
                ));
            }
        }
        return list;
    }

    public List<CryptoCurrencyDto> convertToSingleCurrencyFromWholeMonth(CoinGeckoResponseWithSpecifiedDateDto coinGeckoResponseWithSpecifiedDateDto, Code code){
        List<CryptoCurrencyDto> list = new ArrayList<>();
        int counter = 0;
        double sumOfExchangeRates = 0.0;
        LocalDate date = LocalDate.now();
        for(int i = 0; i < 720; i++){
            counter++;
            sumOfExchangeRates += coinGeckoResponseWithSpecifiedDateDto.getPrices()[i][1];
            if(counter % 24 == 0){
                list.add(new CryptoCurrencyDto(
                        0L,
                        code.fullname,
                        code,
                        date,
                        sumOfExchangeRates/24
                ));
                sumOfExchangeRates = 0;
                date = date.minusDays(1);
            }
        }
        return list;
    }
}
