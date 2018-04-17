package de.htwg.swqs.cart.utils;

public class ShoppingCartItemWrongQuantityException extends RuntimeException {

  public ShoppingCartItemWrongQuantityException(String exception) {
    super(exception);
  }
}