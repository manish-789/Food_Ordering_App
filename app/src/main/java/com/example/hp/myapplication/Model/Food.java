package com.example.hp.myapplication.Model;

public class Food {
    private String Name, Image,Description,MenuId, CategoryId;
    private String Price, Discount;

    public Food(){

    }

    public Food(String name, String image, String description, String menuId, String categoryId, String price, String discount) {
        Name = name;
        Image = image;
        Description = description;
        MenuId = menuId;
        CategoryId = categoryId;
        Price = price;
        Discount = discount;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getDiscount() {
        return Discount;
    }

    public void setDiscount(String discount) {
        Discount = discount;
    }

    public String getMenuId() {
        return MenuId;
    }

    public void setMenuId(String menuId) {
        MenuId = menuId;
    }

    public String getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(String categoryId) {
        CategoryId = categoryId;
    }
}
