package de.htwg.swqs.cart.service;

import de.htwg.swqs.catalog.model.Product;
import de.htwg.swqs.cart.model.ShoppingCart;
import de.htwg.swqs.cart.model.ShoppingCartItem;
import de.htwg.swqs.cart.utils.ShoppingCartException;
import de.htwg.swqs.catalog.service.CatalogService;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class CartServiceTest {

    @Test
    public void createNewShoppingCart() {
        // setup
        CatalogService catalogServiceMock = mock(CatalogService.class);
        CartService cartService = new CartServiceImpl(catalogServiceMock);
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
        CatalogService catalogServiceMock = mock(CatalogService.class);
        CartService cartService = new CartServiceImpl(catalogServiceMock);
        ShoppingCart createdCart = cartService.createNewShoppingCart();

        // execute
        ShoppingCart retrievedCart = cartService.getShoppingCart(createdCart.getId());

        // verify
        assertEquals(createdCart, retrievedCart);
    }


    @Test(expected = ShoppingCartException.class)
    public void getShoppingCartWhoDoesNotExist() {
        // setup
        CatalogService catalogServiceMock = mock(CatalogService.class);
        CartService cartService = new CartServiceImpl(catalogServiceMock);
        ShoppingCart cart = cartService.createNewShoppingCart();

        // execute
        cartService.getShoppingCart(9999999);

        // verification is done by the expected exception
    }

    @Test
    public void clearShoppingCart() {
        // setup
        CatalogService catalogServiceMock = mock(CatalogService.class);
        CartService cartService = new CartServiceImpl(catalogServiceMock);
        ShoppingCart cart = cartService.createNewShoppingCart();
        Product prod = new Product(1234, "Sample product", "a description", BigDecimal.valueOf(0.99));
        ShoppingCartItem item = new ShoppingCartItem(1, prod);
        cartService.addItemToCart(cart.getId(), item);

        // execute
        cartService.clearShoppingCart(cart.getId());

        // verify
        assertTrue(cart.getItemsInShoppingCart().isEmpty());
        assertEquals(BigDecimal.valueOf(0), cart.getCartTotalSum());
    }

    @Test(expected = ShoppingCartException.class)
    public void clearShoppingCartWhoDoesNotExist() {
        // setup
        CatalogService catalogServiceMock = mock(CatalogService.class);
        CartService cartService = new CartServiceImpl(catalogServiceMock);
        ShoppingCart cart = cartService.createNewShoppingCart();
        Product prod = new Product(1234, "Sample product", "a description", BigDecimal.valueOf(0.99));
        ShoppingCartItem item = new ShoppingCartItem(1, prod);
        cartService.addItemToCart(cart.getId(), item);

        // execute
        cartService.clearShoppingCart(9999999);

        // verification is done by the expected exception
    }
}
