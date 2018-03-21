package de.htwg.swqs.cart.model;

import java.math.BigDecimal;


public class Product {


    private long id;
    private String name;
    private String description;
    private BigDecimal priceEuro;

    public Product() {}

    public Product(long id, String name, String description, BigDecimal priceEuro) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.priceEuro = priceEuro;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPriceEuro() {
        return priceEuro;
    }

    public void setPriceEuro(BigDecimal priceEuro) {
        this.priceEuro = priceEuro;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", priceEuro=" + priceEuro +
                '}';
    }
}
