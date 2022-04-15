package com.example.hp.myapplication.Model;

public class FoodOrder {
   String productName, productId, discount, price, quantity;

    public FoodOrder() {
    }

    public FoodOrder(String productName, String productId, String discount, String price, String quantity) {
        this.productName = productName;
        this.productId = productId;
        this.discount = discount;
        this.price = price;
        this.quantity = quantity;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
