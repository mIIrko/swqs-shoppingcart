package de.htwg.swqs.cart.utils;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ShoppingCartItemWrongQuantityException extends RuntimeException {

    public ShoppingCartItemWrongQuantityException(String exception) {
        super(exception);
    }
}