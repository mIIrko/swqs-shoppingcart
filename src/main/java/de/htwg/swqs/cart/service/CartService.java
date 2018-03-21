package de.htwg.swqs.cart.service;

import de.htwg.swqs.cart.model.Product;
import de.htwg.swqs.cart.model.ShoppingCart;

public interface CartService {

    public ShoppingCart getShoppingCart(long cartId);

    public ShoppingCart removeItemFromCart(long cartId, Product product, int quantityToRemove);

    public ShoppingCart addItemToCart(long cartId, Product product, int quantityToAdd);

    public ShoppingCart clearShoppingCart(long cartId);

}
