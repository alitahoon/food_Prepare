package com.example.food_order.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.food_order.R;
import com.example.food_order.models.ExtendedIngredient;
import com.example.food_order.models.Product;
import com.example.food_order.models.getRecipeResponse;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class rcv_extendedIngredient_adapter extends RecyclerView.Adapter<rcv_extendedIngredient_adapter.rcv_extendedIngredient_adapterViewHolder>{
    ArrayList<ExtendedIngredient> recipesDataList=new ArrayList<>();
    Context context;

    public rcv_extendedIngredient_adapter(ArrayList<ExtendedIngredient> recipesDataList, Context context) {
        this.recipesDataList = recipesDataList;
        this.context = context;
    }

    @NonNull
    @Override
    public rcv_extendedIngredient_adapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v=LayoutInflater.from(parent.getContext()).inflate(R.layout.details_rcv_item_layout,null,false);
        return new rcv_extendedIngredient_adapterViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull rcv_extendedIngredient_adapterViewHolder holder, int position) {
        ExtendedIngredient newExtendedIngredientData=recipesDataList.get(position);
        Picasso.get().load(newExtendedIngredientData.image).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return recipesDataList.size();
    }


    public class rcv_extendedIngredient_adapterViewHolder extends RecyclerView.ViewHolder{
        ImageView image;
        public rcv_extendedIngredient_adapterViewHolder(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.ExtendedIngredient_image);
        }
    }
}
