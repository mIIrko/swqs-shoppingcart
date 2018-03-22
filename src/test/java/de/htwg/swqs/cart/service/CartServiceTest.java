package de.htwg.swqs.cart.service;

import de.htwg.swqs.cart.model.Product;
import de.htwg.swqs.cart.model.ShoppingCart;
import de.htwg.swqs.cart.utils.ShoppingCartException;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Collections;

import static org.junit.Assert.*;

public class CartServiceTest {

    @Test
    public void createNewShoppingCart() {
        // setup
        CartServiceImpl cartService = new CartServiceImpl();

        // execute
        ShoppingCart newCart = cartService.createNewShoppingCart();

        //verify
        assertNotNull(newCart);
        // assert that the id is set correctly
        assertTrue("id of newly created shopping cart", newCart.getId() > 0L);
        // and the newly created cart got no items
        assertTrue(newCart.getItemsInShoppingCart().isEmpty());
    }


    @Test
    public void getShoppingCartWhoExists() {
        // setup
        CartServiceImpl cartService = new CartServiceImpl();
        ShoppingCart createdCart = cartService.createNewShoppingCart();

        // execute
        ShoppingCart retrievedCart = cartService.getShoppingCart(createdCart.getId());

        // verify
        assertEquals(createdCart, retrievedCart);
    }


    @Test(expected = ShoppingCartException.class)
    public void getShoppingCartWhoDoesNotExist() {
        // setup
        CartServiceImpl cartService = new CartServiceImpl();
        ShoppingCart cart = cartService.createNewShoppingCart();

        // execute
        cartService.getShoppingCart(9999999);

        // verification is done by the expected exception
    }

    @Test
    public void clearShoppingCart() {
        // setup
        CartServiceImpl cartService = new CartServiceImpl();
        ShoppingCart cart = cartService.createNewShoppingCart();
        Product prod = new Product(1234, "Sample product", "a description", BigDecimal.valueOf(0.99));
        cartService.addItemToCart(cart.getId(), prod, 1);

        // execute
        cartService.clearShoppingCart(cart.getId());

        // verify
        assertTrue(cart.getItemsInShoppingCart().isEmpty());
        assertEquals(BigDecimal.valueOf(0), cart.getCartTotalSum());
    }

    @Test(expected = ShoppingCartException.class)
    public void clearShoppingCartWhoDoesNotExist() {
        // setup
        CartServiceImpl cartService = new CartServiceImpl();
        ShoppingCart cart = cartService.createNewShoppingCart();
        Product prod = new Product(1234, "Sample product", "a description", BigDecimal.valueOf(0.99));
        cartService.addItemToCart(cart.getId(), prod, 1);

        // execute
        cartService.clearShoppingCart(9999999);

        // verification is done by the expected exception
    }
}
