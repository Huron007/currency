package com.kodilla.currency.config;

import com.kodilla.currency.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalHttpErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<Object> handleCurrencyNotFoundException(CurrencyNotFoundException exception){
        return new ResponseEntity<>("Currency with given id does not exist.", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleDuplicateCurrencyException(DuplicateCurrencyException exception){
        return new ResponseEntity<>("Currency with given code and effective date already exist in database.", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleCryptoCurrencyNotFoundException(CryptoCurrencyNotFoundException exception){
        return new ResponseEntity<>("CryptoCurrency with given id does not exist.", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleDuplicateCryptoCurrencyException(DuplicateCryptoCurrencyException exception){
        return new ResponseEntity<>("CryptoCurrency with given code and effective date already exist in database.", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleAlertNotFoundException(AlertNotFoundException exception){
        return new ResponseEntity<>("Alert with given id does not exist." , HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleFavoriteNotFoundException(FavoriteNotFoundException exception){
        return new ResponseEntity<>("Favorite with given id does not exist.", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleDuplicateFavoriteException(DuplicateFavoriteException exception){
        return new ResponseEntity<>("Favorite with given properties already exist.", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleCodeNotFoundException(CodeNotFoundException exception){
        return new ResponseEntity<>("Given code does not exist.", HttpStatus.NOT_FOUND);
    }
}
