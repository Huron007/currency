package com.kodilla.currency.controller;

import com.kodilla.currency.dto.CurrencyDto;
import com.kodilla.currency.entity.Code;
import com.kodilla.currency.exception.CurrencyNotFoundException;
import com.kodilla.currency.exception.DuplicateCurrencyException;
import com.kodilla.currency.facade.CurrencyFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/v1/currency")
public class CurrencyController {

    private final CurrencyFacade currencyFacade;

    @GetMapping
    public ResponseEntity<List<CurrencyDto>> getCurrencies() {
        return ResponseEntity.ok(currencyFacade.getAllCurrencies());
    }

    @GetMapping(value = "{currencyId}")
    public ResponseEntity<CurrencyDto> getCurrency(@PathVariable Long currencyId) throws CurrencyNotFoundException {
        return ResponseEntity.ok(currencyFacade.getSingleCurrency(currencyId));
    }

    @DeleteMapping(value = "{currencyId}")
    public ResponseEntity<Void> deleteCurrency(@PathVariable Long currencyId) throws CurrencyNotFoundException {
        currencyFacade.deleteCurrency(currencyId);
        return ResponseEntity.ok().build();
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CurrencyDto> createCurrency(@RequestBody CurrencyDto currencyDto) throws DuplicateCurrencyException {
        return ResponseEntity.ok(currencyFacade.saveSingleCurrency(currencyDto));
    }

    @PutMapping(value = "createTable",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CurrencyDto>> createCurrencyTable(@RequestBody List<CurrencyDto> currencyDtoList) {
        return ResponseEntity.ok(currencyFacade.saveCurrencyTable(currencyDtoList));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CurrencyDto> updateCurrency(@RequestBody CurrencyDto currencyDto) throws CurrencyNotFoundException {
        return ResponseEntity.ok(currencyFacade.updateSingleCurrency(currencyDto));
    }

    @PostMapping(value = "updateTable", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CurrencyDto>> updateTable(@RequestBody List<CurrencyDto> currencyDtoList) throws CurrencyNotFoundException {
        return ResponseEntity.ok(currencyFacade.updateCurrencyTable(currencyDtoList));
    }

    //WILL BE MOVED TO SCHEDULER
    @GetMapping(value = "fetch/{code}")
    public ResponseEntity<CurrencyDto> fetchSingleCurrency(@PathVariable Code code) throws DuplicateCurrencyException {
        return ResponseEntity.ok(currencyFacade.fetchSingleCurrency(code));
    }

    @GetMapping(value = "fetch/lastMonth/{code}")
    public ResponseEntity<List<CurrencyDto>> fetchSingleCurrencyFromWholeMonth(@PathVariable Code code) {
        return ResponseEntity.ok(currencyFacade.fetchSingleCurrencyFromWholeMonth(code));
    }

    @GetMapping("fetch/table")
    public ResponseEntity<List<CurrencyDto>> fetchTable() {
        return ResponseEntity.ok(currencyFacade.fetchCurrencyList());
    }

    @GetMapping("fetch/lastMonth/table")
    public ResponseEntity<List<CurrencyDto>> fetchTableFromWholeMonth() {
        return ResponseEntity.ok(currencyFacade.fetchCurrencyListFromWholeMonth());
    }
}
