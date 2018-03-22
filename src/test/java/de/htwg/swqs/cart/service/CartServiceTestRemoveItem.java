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


public class CartServiceTestRemoveItem {

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
    public void removeItemSuccessfulFromCart() {
        // setup
        int quantityToRemove = 3;
        // first add all the items to the cart
        cartService.addItemToCart(cart.getId(), prod, quantityToRemove);

        // execute
        cartService.removeItemFromCart(cart.getId(), prod, quantityToRemove);

        // verify
        assertEquals(0, Collections.frequency(cart.getItemsInShoppingCart(), prod));
        assertTrue(cart.getItemsInShoppingCart().isEmpty());
    }

    @Test
    public void removeSomeItemsSuccessfulFromCart() {
        // setup
        int quantityToAdd = 3;
        int quantityToRemove = 2;
        // first add all the items to the cart
        cartService.addItemToCart(cart.getId(), prod, quantityToAdd);

        // execute
        cartService.removeItemFromCart(cart.getId(), prod, quantityToRemove);

        // verify
        assertEquals((quantityToAdd - quantityToRemove), Collections.frequency(cart.getItemsInShoppingCart(), prod));
    }
    @Test
    public void removeItemFromCartWithQuantityZero() {
        // setup
        int quantityToAdd = 3;
        int quantityToRemove = 0;
        // first add all the items to the cart
        cartService.addItemToCart(cart.getId(), prod, quantityToAdd);

        // execute
        cartService.removeItemFromCart(cart.getId(), prod, quantityToRemove);

        // verify
        assertEquals(quantityToAdd - quantityToRemove, Collections.frequency(cart.getItemsInShoppingCart(), prod));
    }

    @Test(expected = ShoppingCartItemWrongQuantityException.class)
    public void removeItemFromCartWithNegativeQuantity() {

        // setup
        int quantityToAdd = 3;
        int quantityToRemove = -5;
        // first add all the items to the cart
        cartService.addItemToCart(cart.getId(), prod, quantityToAdd);

        // execute
        cartService.removeItemFromCart(cart.getId(), prod, quantityToRemove);

        // verification is done with the exception

    }

    @Test(expected = ShoppingCartException.class)
    public void removeItemFromCartWhoDoesNotExist() {
        // setup
        int quantityToRemove = 3;
        // first add all the items to the cart
        cartService.addItemToCart(cart.getId(), prod, quantityToRemove);

        // execute
        cartService.removeItemFromCart(9999999, prod, quantityToRemove);

    }

    @Test(expected = ShoppingCartException.class)
    public void removeItemWhoDoesNotExistFromCart() {
        // setup
        int quantityToRemove = 3;
        // first add all the items to the cart
        cartService.addItemToCart(cart.getId(), prod, quantityToRemove);
        // create another product
        Product anotherProd = new Product(2, "Another sample product", "the description", BigDecimal.valueOf(42));

        // execute
        cartService.removeItemFromCart(cart.getId(), anotherProd, quantityToRemove);
    }

    @Test(expected = ShoppingCartItemWrongQuantityException.class)
    public void removeItemFromCartInWrongQuantity() {
        // setup
        int quantityToAdd = 2;
        int quantityToRemove = 3;
        // first add all the items to the cart
        cartService.addItemToCart(cart.getId(), prod, quantityToAdd);

        // execute
        cartService.removeItemFromCart(cart.getId(), prod, quantityToRemove);

        // verify
        assertEquals((quantityToAdd - quantityToRemove), Collections.frequency(cart.getItemsInShoppingCart(), prod));
        assertTrue(cart.getItemsInShoppingCart().isEmpty());
    }
}
