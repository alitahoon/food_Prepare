package com.example.food_order.interfaces;

import com.example.food_order.models.RandomRecipeApiResponse;

public interface RandomRecipResponseListener {
    void fetchDone(RandomRecipeApiResponse randomRecipeApiResponse,String message);
    void fetchFaild(String message);

}
