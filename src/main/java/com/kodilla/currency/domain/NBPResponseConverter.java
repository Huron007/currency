package com.kodilla.currency.domain;

import com.kodilla.currency.dto.CurrencyDto;
import com.kodilla.currency.dto.NBPResponseDto;
import com.kodilla.currency.dto.NBPResponseTableDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class NBPResponseConverter {

    public CurrencyDto convertToSingleCurrency(NBPResponseDto nbpResponseDto){
        return new CurrencyDto(
                0L,
                nbpResponseDto.getCurrency(),
                nbpResponseDto.getCode(),
                nbpResponseDto.getRates()[0].getEffectiveDate(),
                nbpResponseDto.getRates()[0].getExchangeRate()
        );
    }

    public List<CurrencyDto> convertToSingleCurrencyFromWholeMonth(NBPResponseDto nbpResponseDto){
        List<CurrencyDto> list = new ArrayList<>();
        for (int i = 0; i < nbpResponseDto.getRates().length; i++){
            list.add(new CurrencyDto(
                    0L,
                    nbpResponseDto.getCurrency(),
                    nbpResponseDto.getCode(),
                    nbpResponseDto.getRates()[i].getEffectiveDate(),
                    nbpResponseDto.getRates()[i].getExchangeRate()));
        }
        return list;
    }

    public List<CurrencyDto> convertToCurrencyTable(NBPResponseTableDto[] nbpResponseTableDto){
        List<CurrencyDto> list = new ArrayList<>();
        for(int i = 0; i < nbpResponseTableDto[0].getRates().length; i ++){
            list.add(new CurrencyDto(
                    0L,
                    nbpResponseTableDto[0].getRates()[i].getCurrency(),
                    nbpResponseTableDto[0].getRates()[i].getCode(),
                    nbpResponseTableDto[0].getEffectiveDate(),
                    nbpResponseTableDto[0].getRates()[i].getExchangeRate()
                    ));
        }
        return list;
    }

    public List<CurrencyDto> convertToCurrencyTableFromWholeMonth(NBPResponseTableDto[] nbpResponseTableDto){
        List<CurrencyDto> list = new ArrayList<>();
        for(int k = 0; k < nbpResponseTableDto.length; k ++){
            for (int i = 0; i < nbpResponseTableDto[k].getRates().length; i++){
                list.add(new CurrencyDto(
                        0L,
                        nbpResponseTableDto[k].getRates()[i].getCurrency(),
                        nbpResponseTableDto[k].getRates()[i].getCode(),
                        nbpResponseTableDto[k].getEffectiveDate(),
                        nbpResponseTableDto[k].getRates()[i].getExchangeRate()
                ));
            }
        }
        return list;
    }
}
