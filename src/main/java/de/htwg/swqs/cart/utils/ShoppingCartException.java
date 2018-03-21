package de.htwg.swqs.cart.utils;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ShoppingCartException extends RuntimeException {

    public ShoppingCartException(String exception) {
        super(exception);
    }
}