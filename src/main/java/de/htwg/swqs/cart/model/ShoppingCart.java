package de.htwg.swqs.cart.model;

import de.htwg.swqs.cart.utils.ShoppingCartException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    /**
     * Counter for the shopping cart {@code id}, used when creating new shopping carts
     */
    private static long idGenerator= 0L;

    private long id;
    private List<Product> itemsInShoppingCart;
    private BigDecimal cartTotalSum;

    public ShoppingCart() {
        this.itemsInShoppingCart = new ArrayList<Product>();
        this.cartTotalSum = new BigDecimal(0);
        // set a new id to the created shopping cart
        this.id = ++idGenerator;
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

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "id=" + id +
                ", itemsInShoppingCart=" + itemsInShoppingCart +
                ", cartTotalSum=" + cartTotalSum +
                '}';
    }
}
