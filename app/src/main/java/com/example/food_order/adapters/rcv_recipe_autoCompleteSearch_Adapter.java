package com.example.food_order.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.food_order.R;
import com.example.food_order.models.Product;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class rcv_recipe_autoCompleteSearch_Adapter extends RecyclerView.Adapter<rcv_recipe_autoCompleteSearch_Adapter.rcv_recipe_autoCompleteSearchViewHolder>{
    ArrayList<Product> recipesList=new ArrayList<>();
    Context context;

    public rcv_recipe_autoCompleteSearch_Adapter(ArrayList<Product> recipesList, Context context) {
        this.recipesList = recipesList;
        this.context = context;
    }

    @NonNull
    @Override
    public rcv_recipe_autoCompleteSearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.search_rcv_item_layout,null,false);
        rcv_recipe_autoCompleteSearchViewHolder newSearchedRecipe=new rcv_recipe_autoCompleteSearchViewHolder(v);
        return newSearchedRecipe;
    }

    @Override
    public void onBindViewHolder(@NonNull rcv_recipe_autoCompleteSearchViewHolder holder, int position) {
        Product newRecipeModel=recipesList.get(position);
        holder.name.setText(newRecipeModel.title);
        Picasso.get().load(newRecipeModel.image).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return recipesList.size();
    }

    class rcv_recipe_autoCompleteSearchViewHolder extends RecyclerView.ViewHolder{
        de.hdodenhof.circleimageview.CircleImageView imageView;
        TextView name;
        public rcv_recipe_autoCompleteSearchViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.rcv_search_item_image);
            name=itemView.findViewById(R.id.rcv_search_item_name);
        }
    }
}
