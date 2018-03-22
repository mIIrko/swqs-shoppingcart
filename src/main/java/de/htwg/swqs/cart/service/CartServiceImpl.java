package de.htwg.swqs.cart.service;

import de.htwg.swqs.cart.model.Product;
import de.htwg.swqs.cart.model.ShoppingCart;
import de.htwg.swqs.cart.utils.ShoppingCartException;
import de.htwg.swqs.cart.utils.ShoppingCartItemWrongQuantityException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class CartServiceImpl implements CartService{

    private Map<Long, ShoppingCart> shoppingCarts;

    public CartServiceImpl() {
        this.shoppingCarts = new HashMap<Long, ShoppingCart>();
    }

    public ShoppingCart getShoppingCart(long cartId) {

        if (!this.shoppingCarts.containsKey(cartId)) {
            throw new ShoppingCartException("Shopping cart does not exist");
        }

        return this.shoppingCarts.get(cartId);

    }

    public ShoppingCart removeItemFromCart(long cartId, Product product, int quantityToRemove) {

        if (!this.shoppingCarts.containsKey(cartId)) {
            throw new ShoppingCartException("Shopping cart does not exist");
        }
        if (quantityToRemove < 0) {
            throw new ShoppingCartItemWrongQuantityException("Can not remove negative quantity");
        }

        ShoppingCart cart = this.shoppingCarts.get(cartId);

        // quantity of item in shopping cart
        int quantityOfItemBeforeUpdate = Collections.frequency(cart.getItemsInShoppingCart(), product);
        // if the product is not present in the shopping cart throw exception
        if (quantityOfItemBeforeUpdate <= 0) {
            throw new ShoppingCartException("Shopping cart does not contain the product");
        }
        // if not enough items in shopping cart throw also exception
        if (quantityOfItemBeforeUpdate < quantityToRemove) {
            throw new ShoppingCartItemWrongQuantityException("Removing " + quantityToRemove + " items from shopping cart not possible (just " + quantityOfItemBeforeUpdate + " present)");
        }

        List<Product> newItemsInShoppingCart = cart.getItemsInShoppingCart();
        BigDecimal newCartTotalSum = cart.getCartTotalSum();

        for (int i = 0; i < quantityToRemove; i++) {
            newCartTotalSum = newCartTotalSum.subtract(product.getPriceEuro());
            newItemsInShoppingCart.remove(product);
        }

        cart.setCartTotalSum(newCartTotalSum);
        cart.setItemsInShoppingCartAsList(newItemsInShoppingCart);

        return cart;
    }

    public ShoppingCart addItemToCart(long cartId, Product product, int quantityToAdd) {

        if (!this.shoppingCarts.containsKey(cartId)) {
            throw new ShoppingCartException("Shopping cart does not exist");
        }
        if (quantityToAdd < 0) {
            throw new ShoppingCartItemWrongQuantityException("Can not add negative quantity");
        }

        ShoppingCart cart = this.shoppingCarts.get(cartId);

        List<Product> newItemsInShoppingCart = cart.getItemsInShoppingCart();
        BigDecimal newCartTotalSum = cart.getCartTotalSum();

        for (int i = 0; i < quantityToAdd; i++) {
            newCartTotalSum = newCartTotalSum.add(product.getPriceEuro());
            newItemsInShoppingCart.add(product);
        }

        cart.setCartTotalSum(newCartTotalSum);
        cart.setItemsInShoppingCartAsList(newItemsInShoppingCart);

        return cart;
    }

    public ShoppingCart clearShoppingCart(long cartId) {

        if (!this.shoppingCarts.containsKey(cartId)) {
            throw new ShoppingCartException("Shopping cart does not exist");
        }

        ShoppingCart cart = this.shoppingCarts.get(cartId);
        // replace the itemlist with empty arraylist
        cart.setItemsInShoppingCartAsList(new ArrayList<Product>());
        // reset the cart total sum
        cart.setCartTotalSum(BigDecimal.valueOf(0));
        return cart;
    }

    public ShoppingCart createNewShoppingCart() {

        ShoppingCart cart = new ShoppingCart();
        this.shoppingCarts.put(cart.getId(), cart);
        return cart;
    }

}
