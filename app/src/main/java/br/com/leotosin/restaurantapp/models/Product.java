package br.com.leotosin.restaurantapp.models;

public class Product {

    private String name;
    private String description;
    private Double price;
    private String picture;
    private ProductType productType;

    public Product(String name, String description, Double price, String picture, ProductType productType) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.picture = picture;
        this.productType = productType;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public ProductType getProductType() {
        return productType;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }
}
