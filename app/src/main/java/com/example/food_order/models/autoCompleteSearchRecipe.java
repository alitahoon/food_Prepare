package com.example.food_order.models;

import java.util.ArrayList;

public class autoCompleteSearchRecipe {
    public String type;
    public ArrayList<Product> products;
    public int offset;
    public int number;
    public int totalProducts;
    public int processingTimeMs;
    public long expires;
    public boolean isStale;
}
