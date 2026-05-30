package com.example.bookease;

import androidx.annotation.NonNull;

public class MenuItem_Model {
    private String imageUrl;
    private String name;
    private double price;
    private String description;

    public MenuItem_Model() {
    }

    public MenuItem_Model(String imageUrl, String name, double price, String description) {
        this.imageUrl = imageUrl;
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @NonNull
    @Override
    public String toString() {
        return "MenuItem{" +
                "imageUrl='" + imageUrl + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                '}';
    }
}
