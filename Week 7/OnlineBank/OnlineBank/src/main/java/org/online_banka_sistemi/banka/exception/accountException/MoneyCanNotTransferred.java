package org.kodluyoruz.mybank.exception.accountException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MoneyCanNotTransferred extends RuntimeException {
    public MoneyCanNotTransferred (String messages){
        super(messages);
    }
}
