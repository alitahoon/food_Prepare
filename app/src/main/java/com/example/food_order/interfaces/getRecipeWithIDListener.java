package com.example.food_order.interfaces;

import com.example.food_order.models.autoCompleteSearchRecipe;
import com.example.food_order.models.getRecipeResponse;

public interface getRecipeWithIDListener {
    void fetchDone(getRecipeResponse getRecipeResponse, String message);
    void fetchFaild(String message);

}
