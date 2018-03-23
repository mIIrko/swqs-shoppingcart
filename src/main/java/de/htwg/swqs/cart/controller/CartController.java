package de.htwg.swqs.cart.controller;

import de.htwg.swqs.cart.model.ShoppingCart;
import de.htwg.swqs.cart.model.ShoppingCartItem;
import de.htwg.swqs.cart.service.CartService;
import de.htwg.swqs.cart.service.CartServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carts")
public class CartController {

    private static final Logger LOGGER = LoggerFactory.getLogger(CartController.class);

    private CartService cartService;

    @Autowired
    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping(value = "/create", produces = MediaType.APPLICATION_JSON_VALUE)
    public ShoppingCart createNewCart() {
        return this.cartService.createNewShoppingCart();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ShoppingCart getCartById(@PathVariable long id) {
        return this.cartService.getShoppingCart(id);
    }

    @GetMapping(value = "/{id}/clear", produces = MediaType.APPLICATION_JSON_VALUE)
    public ShoppingCart removeCartById(@PathVariable long id) {
        return this.cartService.clearShoppingCart(id);
    }

    @PostMapping(value = "/{id}/add-item", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ShoppingCart addItemToCart(@PathVariable long id, @RequestBody ShoppingCartItem item) {
        return this.cartService.addItemToCart(id, item);
    }

    @PostMapping(value = "/{id}/remove-item", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ShoppingCart removeItemFromCart(@PathVariable long id, @RequestBody ShoppingCartItem item) {
        return this.cartService.removeItemFromCart(id, item);
    }


}

