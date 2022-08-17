package com.kodilla.currency.controller;

import com.kodilla.currency.dto.CryptoCurrencyDto;
import com.kodilla.currency.entity.Code;
import com.kodilla.currency.exception.CryptoCurrencyNotFoundException;
import com.kodilla.currency.exception.DuplicateCryptoCurrencyException;
import com.kodilla.currency.facade.CryptoCurrencyFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/v1/cryptoCurrency")
public class CryptoCurrencyController {

    private final CryptoCurrencyFacade cryptoCurrencyFacade;

    //DATABASE OPERATIONS
    @GetMapping
    public ResponseEntity<List<CryptoCurrencyDto>> getCurrencies() {
        return ResponseEntity.ok(cryptoCurrencyFacade.getAllCurrencies());
    }

    @GetMapping(value = "{cryptoCryptoCurrencyId}")
    public ResponseEntity<CryptoCurrencyDto> getCryptoCurrency(@PathVariable Long cryptoCryptoCurrencyId) throws CryptoCurrencyNotFoundException {
        return ResponseEntity.ok(cryptoCurrencyFacade.getSingleCryptoCurrency(cryptoCryptoCurrencyId));
    }

    @DeleteMapping(value = "{cryptoCryptoCurrencyId}")
    public ResponseEntity<Void> deleteCryptoCurrency(@PathVariable Long cryptoCryptoCurrencyId) throws CryptoCurrencyNotFoundException {
        cryptoCurrencyFacade.deleteCryptoCurrency(cryptoCryptoCurrencyId);
        return ResponseEntity.ok().build();
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CryptoCurrencyDto> createCryptoCurrency(@RequestBody CryptoCurrencyDto cryptoCryptoCurrencyDto) throws DuplicateCryptoCurrencyException {
        return ResponseEntity.ok(cryptoCurrencyFacade.saveSingleCryptoCurrency(cryptoCryptoCurrencyDto));
    }

    @PutMapping(value = "Table",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CryptoCurrencyDto>> createCryptoCurrencyTable(@RequestBody List<CryptoCurrencyDto> cryptoCryptoCurrencyDtoList) {
        return ResponseEntity.ok(cryptoCurrencyFacade.saveCryptoCurrencyTable(cryptoCryptoCurrencyDtoList));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CryptoCurrencyDto> updateCryptoCurrency(@RequestBody CryptoCurrencyDto cryptoCryptoCurrencyDto) throws CryptoCurrencyNotFoundException {
        return ResponseEntity.ok(cryptoCurrencyFacade.updateSingleCryptoCurrency(cryptoCryptoCurrencyDto));
    }

    @PostMapping(value = "Table", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CryptoCurrencyDto>> updateTable(@RequestBody List<CryptoCurrencyDto> cryptoCryptoCurrencyDtoList) throws CryptoCurrencyNotFoundException {
        return ResponseEntity.ok(cryptoCurrencyFacade.updateCryptoCurrencyTable(cryptoCryptoCurrencyDtoList));
    }

    //API OPERATIONS
    @GetMapping(value = "fetch/topTen")
    public ResponseEntity<List<CryptoCurrencyDto>> fetchTopTenCrypto(){
        return ResponseEntity.ok(cryptoCurrencyFacade.fetchTopTenCryptoList());
    }

    @GetMapping(value = "fetch/{code}")
    public ResponseEntity<List<CryptoCurrencyDto>> fetchCryptoFromWholeMonth(@PathVariable Code code){
        return ResponseEntity.ok(cryptoCurrencyFacade.fetchSingleCryptoFromWholeMonth(code));
    }
}
