package com.example.food_order.interfaces;

import com.example.food_order.models.autoCompleteSearchRecipe;

public interface searchRecipeResponseListener {
    void fetchDone(autoCompleteSearchRecipe autoCompleteSearchRecipe, String message);
    void fetchFaild(String message);

}
