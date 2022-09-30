package com.example.food_order.models;

public class food_model {
    private int element_image;
    private String name;
    private double price;
    private String details;

    public food_model(int element_image, String name, double price, String details) {
        this.element_image = element_image;
        this.name = name;
        this.price = price;
        this.details = details;
    }

    public int getElement_image() {
        return element_image;
    }

    public void setElement_image(int element_image) {
        this.element_image = element_image;
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

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
