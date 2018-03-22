package de.htwg.swqs.cart.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

/**
 * Class will be used to convert the payload of requests to add new products
 * to a java object (with Jackson Json-processor https://github.com/FasterXML/jackson)
 */
public class ShoppingCartItem {

    @Positive
    private int quantity;
    @NotNull
    private Product product;

    public ShoppingCartItem() {
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
