package de.htwg.swqs.cart.service;

import de.htwg.swqs.catalog.model.Product;
import de.htwg.swqs.cart.model.ShoppingCart;
import de.htwg.swqs.cart.model.ShoppingCartItem;
import de.htwg.swqs.cart.utils.ShoppingCartException;
import de.htwg.swqs.cart.utils.ShoppingCartItemWrongQuantityException;
import de.htwg.swqs.catalog.service.CatalogService;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Collections;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;


public class CartServiceTestAddItem {

    private CartService cartService;
    private ShoppingCart cart;
    private Product prod;

    @Before
    public void setupTestFixture() {
        CatalogService catalogServiceMock = mock(CatalogService.class);
        this.cartService = new CartServiceImpl(catalogServiceMock);
        this.cart = cartService.createNewShoppingCart();
        this.prod = new Product(1234, "Sample product", "a description", BigDecimal.valueOf(0.99));
    }

    @Test
    public void addItemToCart() {
        // setup
        int quantityToAdd = 3;
        ShoppingCartItem item = new ShoppingCartItem(quantityToAdd, prod);

        // execute
        cartService.addItemToCart(cart.getId(), item);

        // verify
        assertTrue(cartService.getShoppingCart(cart.getId()).getItemsInShoppingCart().contains(item));
    }

    @Test
    public void addItemToCartWithQuantityZero() {
        // setup
        int quantityToAdd = 0;
        ShoppingCartItem item = new ShoppingCartItem(quantityToAdd, prod);

        // execute
        cartService.addItemToCart(cart.getId(), item);

        // verify
        int quantityOfProductsInCart = Collections.frequency(cartService.getShoppingCart(cart.getId()).getItemsInShoppingCart(), prod);
        assertEquals(quantityOfProductsInCart, quantityToAdd);
    }

    @Test(expected = ShoppingCartItemWrongQuantityException.class)
    public void addItemToCartWithNegativeQuantity() {

        // todo: write function to avoid this

        // setup
        int quantityToAdd = -5;
        ShoppingCartItem item = new ShoppingCartItem(quantityToAdd, prod);

        // execute
        cartService.addItemToCart(cart.getId(), item);


    }

    @Test(expected = ShoppingCartException.class)
    public void addItemToCartWhoDoesNotExist() {
        // setup
        int quantityToAdd = 3;
        ShoppingCartItem item = new ShoppingCartItem(quantityToAdd, prod);

        // execute
        cartService.addItemToCart(9999999, item);

        // verification is done by the expected exception
    }
}
