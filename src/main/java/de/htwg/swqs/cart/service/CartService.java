package de.htwg.swqs.cart.service;

import de.htwg.swqs.cart.model.Product;
import de.htwg.swqs.cart.model.ShoppingCart;
import de.htwg.swqs.cart.model.ShoppingCartItem;
import org.springframework.stereotype.Service;


public interface CartService {

    public ShoppingCart createNewShoppingCart();

    public ShoppingCart clearShoppingCart(long cartId);

    public ShoppingCart getShoppingCart(long cartId);

    public ShoppingCart removeItemFromCart(long cartId, ShoppingCartItem item);

    public ShoppingCart addItemToCart(long cartId, ShoppingCartItem item);

}
