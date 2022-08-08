package com.kodilla.currency.config;

import com.kodilla.currency.exception.CurrencyNotFoundException;
import com.kodilla.currency.exception.DuplicateCurrencyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalHttpErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<Object> handleCurrencyNotFoundException(CurrencyNotFoundException exception){
        return new ResponseEntity<>("Currency with given id does not exist.", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleDuplicateCurrencyException(DuplicateCurrencyException exception){
        return new ResponseEntity<>("Currency with given code and effective date already exist in database.", HttpStatus.BAD_REQUEST);
    }
}
