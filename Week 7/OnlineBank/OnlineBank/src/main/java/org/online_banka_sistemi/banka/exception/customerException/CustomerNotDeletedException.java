package org.kodluyoruz.mybank.exception.customerException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CustomerNotDeletedException extends RuntimeException {
    public CustomerNotDeletedException(String message) {
        super(message);
    }
}
