package org.kodluyoruz.mybank.exception;

import org.kodluyoruz.mybank.exception.accountException.MoneyCanNotTransferred;
import org.kodluyoruz.mybank.exception.customerException.CustomerNotDeletedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler  {

    @ExceptionHandler(value = {CustomerNotDeletedException.class})
    public ResponseEntity<Object> handleRequestException(CustomerNotDeletedException e){
        ApiException myException = new ApiException(
                e.getMessage(),
                HttpStatus.BAD_REQUEST,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(myException, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {MoneyCanNotTransferred.class})
    public ResponseEntity<Object> handleRequestException(MoneyCanNotTransferred e){
        ApiException myException = new ApiException(
                e.getMessage(),
                HttpStatus.BAD_REQUEST,
                ZonedDateTime.now(ZoneId.of("Z"))
        );
        return new ResponseEntity<>(myException, HttpStatus.BAD_REQUEST);
    }

}
