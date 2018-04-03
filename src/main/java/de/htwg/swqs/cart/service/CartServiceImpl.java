package de.htwg.swqs.cart.service;

import de.htwg.swqs.catalog.model.Product;
import de.htwg.swqs.cart.utils.ShoppingCartException;
import de.htwg.swqs.cart.utils.ShoppingCartItemWrongQuantityException;
import de.htwg.swqs.catalog.repository.CatalogRepository;
import de.htwg.swqs.cart.model.ShoppingCart;
import de.htwg.swqs.cart.model.ShoppingCartItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class CartServiceImpl implements CartService {

    private Map<Long, ShoppingCart> shoppingCarts;

    private CatalogRepository catalogRepository;

    @Autowired
    public CartServiceImpl(CatalogRepository catalogRepository) {
        this.catalogRepository = catalogRepository;
        this.shoppingCarts = new HashMap<>();
    }

    public ShoppingCart getShoppingCart(long cartId) {

        if (!this.shoppingCarts.containsKey(cartId)) {
            throw new ShoppingCartException("Shopping cart does not exist");
        }

        return this.shoppingCarts.get(cartId);

    }

    public ShoppingCart removeItemFromCart(long cartId, ShoppingCartItem item) {

        if (!this.shoppingCarts.containsKey(cartId)) {
            throw new ShoppingCartException("Shopping cart does not exist");
        }
        // todo: can be done by java validation
        if (item.getQuantity() < 0) {
            throw new ShoppingCartItemWrongQuantityException("Can not remove negative quantity");
        }

        ShoppingCart cart = this.shoppingCarts.get(cartId);
        // try to get the existing shopping cart item with the same product which we want to remove (or reduce the quantity)
        Optional<ShoppingCartItem> existingShoppingCartItem = getExistingItemFromShoppingCart(cart.getItemsInShoppingCart(), item.getProduct());
        if (!existingShoppingCartItem.isPresent()) {
            throw new ShoppingCartException("Shopping cart does not contain the product");
        }

        // quantity of item in shopping cart
        int quantityOfItemBeforeUpdate = existingShoppingCartItem.get().getQuantity();
        // if not enough items in shopping cart throw exception
        if (quantityOfItemBeforeUpdate < item.getQuantity()) {
            throw new ShoppingCartItemWrongQuantityException("Removing " + item.getQuantity() + " items from shopping cart not possible (just " + quantityOfItemBeforeUpdate + " present)");
        }

        // update the total sum of the cart
        BigDecimal priceOfTheRemovedItems = BigDecimal.valueOf(item.getQuantity()).multiply(item.getProduct().getPriceEuro());
        cart.setCartTotalSum(cart.getCartTotalSum().subtract(priceOfTheRemovedItems));
        // update the quantity of the shopping cart item
        int newQuantityOfProduct = existingShoppingCartItem.get().getQuantity() - item.getQuantity();
        if (newQuantityOfProduct == 0) {
            cart.getItemsInShoppingCart().remove(existingShoppingCartItem.get());
        } else {
            existingShoppingCartItem.get().setQuantity(newQuantityOfProduct);
        }

        return cart;
    }

    public ShoppingCart removeItemFromCart(long cartId, long productId, int quantity) {
        Optional<Product> product = this.catalogRepository.findById(productId);
        if (!product.isPresent()) {
            // todo: throw exception
        }

        ShoppingCartItem item = new ShoppingCartItem();
        item.setQuantity(quantity);
        item.setProduct(product.get());

        return removeItemFromCart(cartId, item);
    }

    public ShoppingCart addItemToCart(long cartId, ShoppingCartItem item) {

        if (!this.shoppingCarts.containsKey(cartId)) {
            throw new ShoppingCartException("Shopping cart does not exist");
        }
        // todo: will be done by java validation
        if (item.getQuantity() < 0) {
            throw new ShoppingCartItemWrongQuantityException("Can not add negative quantity");
        }

        ShoppingCart cart = this.shoppingCarts.get(cartId);
        // try to get the existing shopping cart item with the same product which we want to add (or raise the quantity)
        Optional<ShoppingCartItem> existingShoppingCartItem = getExistingItemFromShoppingCart(cart.getItemsInShoppingCart(), item.getProduct());

        if (existingShoppingCartItem.isPresent()) {
            // the product is already in the cart
            int quantityOfItemBeforeUpdate = existingShoppingCartItem.get().getQuantity();
            existingShoppingCartItem.get().setQuantity(quantityOfItemBeforeUpdate + item.getQuantity());
        } else {
            // product not there, add it to cart
            cart.getItemsInShoppingCart().add(item);
        }

        // update the total sum of the cart
        BigDecimal priceOfTheAddedItems = BigDecimal.valueOf(item.getQuantity()).multiply(item.getProduct().getPriceEuro());
        cart.setCartTotalSum(cart.getCartTotalSum().add(priceOfTheAddedItems));

        return cart;
    }

    public ShoppingCart addItemToCart(long cartId, long productId, int quantity) {
        ShoppingCartItem item = new ShoppingCartItem();
        item.setProduct(getProductFromCatalog(productId));
        item.setQuantity(quantity);
        return addItemToCart(cartId, item);
    }

    public ShoppingCart clearShoppingCart(long cartId) {

        if (!this.shoppingCarts.containsKey(cartId)) {
            throw new ShoppingCartException("Shopping cart does not exist");
        }

        ShoppingCart cart = this.shoppingCarts.get(cartId);
        // replace the itemlist with empty arraylist
        cart.setItemsInShoppingCartAsList(new ArrayList<>());
        // reset the cart total sum
        cart.setCartTotalSum(BigDecimal.valueOf(0));
        return cart;
    }

    public ShoppingCart createNewShoppingCart() {

        ShoppingCart cart = new ShoppingCart();
        this.shoppingCarts.put(cart.getId(), cart);
        return cart;
    }

    private Optional<ShoppingCartItem> getExistingItemFromShoppingCart(List<ShoppingCartItem> shoppingCartItems, Product productToRemove) {
        for (ShoppingCartItem tmpItem : shoppingCartItems) {
            if (tmpItem.getProduct().equals(productToRemove)) {
                return Optional.of(tmpItem);
            }
        }
        return Optional.empty();
    }

    private Product getProductFromCatalog(long productId) {
        Optional<Product> p = this.catalogRepository.findById(productId);

        if (!p.isPresent()) {
            throw new ShoppingCartException("Product does not exist");
        }
        return p.get();
    }


}
