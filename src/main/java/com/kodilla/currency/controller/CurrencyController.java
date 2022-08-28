package com.kodilla.currency.controller;

import com.kodilla.currency.dto.CurrencyDto;
import com.kodilla.currency.entity.Code;
import com.kodilla.currency.exception.CodeNotFoundException;
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

    //DATABASE OPERATIONS
    @GetMapping
    public ResponseEntity<List<CurrencyDto>> getCurrencies() {
        return ResponseEntity.ok(currencyFacade.getAllCurrencies());
    }

    @GetMapping("list/{code}")
    public ResponseEntity<List<CurrencyDto>> getAllCurrenciesWithGivenCode(@PathVariable Code code) throws CurrencyNotFoundException {
        return ResponseEntity.ok(currencyFacade.getAllCurrenciesWithGivenCode(code));
    }

    @GetMapping("list/latest")
    public ResponseEntity<List<CurrencyDto>> getLatestCurrencyList(){
        return ResponseEntity.ok(currencyFacade.getLatestCurrencyList());
    }

    @GetMapping(value = "{currencyId}")
    public ResponseEntity<CurrencyDto> getCurrency(@PathVariable Long currencyId) throws CurrencyNotFoundException {
        return ResponseEntity.ok(currencyFacade.getSingleCurrency(currencyId));
    }

    @GetMapping(value = "/rate/{code}")
    public ResponseEntity<Double> getLatestExchangeRate(@PathVariable Code code) throws CodeNotFoundException {
        return ResponseEntity.ok(currencyFacade.getLatestExchangeRate(code));
    }

    @GetMapping(value = "/calculate")
    public ResponseEntity<Double> calculate(@RequestParam Code code1, @RequestParam Code code2, @RequestParam Double value) throws CodeNotFoundException {
        return ResponseEntity.ok(currencyFacade.calculate(code1, code2, value));
    }

    @DeleteMapping(value = "{currencyId}")
    public ResponseEntity<Void> deleteCurrency(@PathVariable Long currencyId) throws CurrencyNotFoundException {
        currencyFacade.deleteCurrency(currencyId);
        return ResponseEntity.ok().build();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CurrencyDto> createCurrency(@RequestBody CurrencyDto currencyDto) throws DuplicateCurrencyException {
        return ResponseEntity.ok(currencyFacade.saveSingleCurrency(currencyDto));
    }

    @PostMapping(value = "Table",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CurrencyDto>> createCurrencyTable(@RequestBody List<CurrencyDto> currencyDtoList) {
        return ResponseEntity.ok(currencyFacade.saveCurrencyTable(currencyDtoList));
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CurrencyDto> updateCurrency(@RequestBody CurrencyDto currencyDto) throws CurrencyNotFoundException {
        return ResponseEntity.ok(currencyFacade.updateSingleCurrency(currencyDto));
    }

    @PutMapping(value = "Table", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CurrencyDto>> updateTable(@RequestBody List<CurrencyDto> currencyDtoList) throws CurrencyNotFoundException {
        return ResponseEntity.ok(currencyFacade.updateCurrencyTable(currencyDtoList));
    }

    //API OPERATIONS
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
