package de.htwg.swqs.cart.service;

import de.htwg.swqs.cart.model.Product;
import de.htwg.swqs.cart.model.ShoppingCart;
import de.htwg.swqs.cart.utils.ShoppingCartException;
import de.htwg.swqs.cart.utils.ShoppingCartItemWrongQuantityException;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Collections;

import static org.junit.Assert.*;


public class CartServiceTestAddItem {

    private CartService cartService;
    private ShoppingCart cart;
    private Product prod;

    @Before
    public void setupTestFixture() {
        cartService = new CartServiceImpl();
        cart = cartService.createNewShoppingCart();
        prod = new Product(1234, "Sample product", "a description", BigDecimal.valueOf(0.99));
    }

    @Test
    public void addItemToCart() {
        // setup
        int quantityToAdd = 3;

        // execute
        cartService.addItemToCart(cart.getId(), prod, quantityToAdd);

        // verify
        int quantityOfProductsInCart = Collections.frequency(cartService.getShoppingCart(cart.getId()).getItemsInShoppingCart(), prod);
        assertEquals(quantityOfProductsInCart, quantityToAdd);
    }

    @Test
    public void addItemToCartWithQuantityZero() {
        // setup
        int quantityToAdd = 0;

        // execute
        cartService.addItemToCart(cart.getId(), prod, quantityToAdd);

        // verify
        int quantityOfProductsInCart = Collections.frequency(cartService.getShoppingCart(cart.getId()).getItemsInShoppingCart(), prod);
        assertEquals(quantityOfProductsInCart, quantityToAdd);
    }

    @Test(expected = ShoppingCartItemWrongQuantityException.class)
    public void addItemToCartWithNegativeQuantity() {

        // todo: write function to avoid this

        // setup
        int quantityToAdd = -5;

        // execute
        cartService.addItemToCart(cart.getId(), prod, quantityToAdd);

        // verify
        int quantityOfProductsInCart = Collections.frequency(cartService.getShoppingCart(cart.getId()).getItemsInShoppingCart(), prod);
        assertEquals(quantityOfProductsInCart, quantityToAdd);
    }

    @Test(expected = ShoppingCartException.class)
    public void addItemToCartWhoDoesNotExist() {
        // setup
        int quantityToAdd = 3;

        // execute
        cartService.addItemToCart(9999999, prod, quantityToAdd);

        // verification is done by the expected exception
    }
}
