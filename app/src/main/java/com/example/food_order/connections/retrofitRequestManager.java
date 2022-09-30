package com.example.food_order.connections;

import android.content.Context;
import android.util.Log;

import com.example.food_order.R;
import com.example.food_order.interfaces.RandomRecipResponseListener;
import com.example.food_order.interfaces.getRecipeWithIDListener;
import com.example.food_order.interfaces.searchRecipeResponseListener;
import com.example.food_order.models.RandomRecipeApiResponse;
import com.example.food_order.models.autoCompleteSearchRecipe;
import com.example.food_order.models.getRecipeResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class retrofitRequestManager {
    Context context;
    Retrofit retrofit=new Retrofit.Builder()
            .baseUrl("https://api.spoonacular.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();


    public retrofitRequestManager(Context context) {
        this.context = context;
    }

    public void getRandomRecipe(String tags,RandomRecipResponseListener listener){
        callRandomRecipes callRandomRecipes=retrofit.create(retrofitRequestManager.callRandomRecipes.class);
        Call<RandomRecipeApiResponse> call= callRandomRecipes.CallRandomRecipes(context.getString(R.string.apiKey),
                "20",tags);
        call.enqueue(new Callback<RandomRecipeApiResponse>() {
            @Override
            public void onResponse(Call<RandomRecipeApiResponse> call, Response<RandomRecipeApiResponse> response) {
                if (!response.isSuccessful()){
                    listener.fetchFaild(response.message());
                    Log.i("errrr",response.message());
                }
                listener.fetchDone(response.body(),response.message());

            }

            @Override
            public void onFailure(Call<RandomRecipeApiResponse> call, Throwable t) {
                listener.fetchFaild(t.getMessage());
                Log.i("errrr",t.getMessage());
            }
        });
    }
    public void getSearchAutoComplete( String searchKey,searchRecipeResponseListener listener){
        callSearchAutoComplete callSearchAutoComplete=retrofit.create(retrofitRequestManager.callSearchAutoComplete.class);
        Call<autoCompleteSearchRecipe> call=callSearchAutoComplete.CallSearchAutoComplete(context.getString(R.string.apiKey),
                        "5",searchKey);
                call.enqueue(new Callback<autoCompleteSearchRecipe>() {
                    @Override
                    public void onResponse(Call<autoCompleteSearchRecipe> call, Response<autoCompleteSearchRecipe> response) {
                        if (!response.isSuccessful()){
                            listener.fetchFaild(response.message());

                        }
                        else{
                            listener.fetchDone(response.body(),response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<autoCompleteSearchRecipe> call, Throwable t) {
                            listener.fetchFaild(t.getMessage());
                    }
                });

    }

    public void getRecipeWithID(int RecipeID, Boolean includeNutrition, getRecipeWithIDListener listener){
        callRecipeWithID callRecipe=retrofit.create(retrofitRequestManager.callRecipeWithID.class);
        Call<getRecipeResponse> call=callRecipe.CallRecupeWithID(context.getString(R.string.apiKey),RecipeID,includeNutrition);
        call.enqueue(new Callback<getRecipeResponse>() {
            @Override
            public void onResponse(Call<getRecipeResponse> call, Response<getRecipeResponse> response) {
                if (!response.isSuccessful()){
                    listener.fetchFaild(response.message());
                }else {
                    listener.fetchDone(response.body(),response.message());
                }
            }

            @Override
            public void onFailure(Call<getRecipeResponse> call, Throwable t) {
                listener.fetchFaild(t.getMessage());
            }
        });

    }
    private interface callSearchAutoComplete{
        @GET("food/products/search")
        Call<autoCompleteSearchRecipe> CallSearchAutoComplete(
                @Query("apiKey") String apiKey,
                @Query("number") String number,
                @Query("query") String searchKey
        );
    }

    private interface callRandomRecipes{
        @GET("recipes/random")
        Call<RandomRecipeApiResponse>  CallRandomRecipes(
                @Query("apiKey") String apiKey,
                @Query("number") String number,
                @Query("tags") String tags
        );
    }

    private  interface  callRecipeWithID{
        @GET("recipes/{id}/information")
        Call<getRecipeResponse> CallRecupeWithID(
                @Query("apiKey") String apiKey,
                @Query("id") int id,
                @Query("includeNutrition") Boolean includeNutrition
        );
    }

}
