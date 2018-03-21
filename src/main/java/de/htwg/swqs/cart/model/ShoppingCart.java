package de.htwg.swqs.cart.model;

import de.htwg.swqs.cart.utils.ShoppingCartException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    private static long idGenerator= 10000L;

    private long id;
    private List<Product> itemsInShoppingCart;
    private BigDecimal cartTotalSum;

    public ShoppingCart() {
        this.itemsInShoppingCart = new ArrayList<Product>();
        // set a new id to the created shopping cart
        this.id = idGenerator++;
    }

    public long getId() {
        return id;
    }

    public List<Product> getItemsInShoppingCart() {
        return itemsInShoppingCart;
    }

    public void setItemsInShoppingCartAsList(List<Product> itemsInShoppingCart) {
        this.itemsInShoppingCart = itemsInShoppingCart;
    }

    public BigDecimal getCartTotalSum() {
        return cartTotalSum;
    }

    public void setCartTotalSum(BigDecimal cartTotalSum) {
        this.cartTotalSum = cartTotalSum;
    }
}
