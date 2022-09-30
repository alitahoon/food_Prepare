package com.example.food_order.adapters;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.food_order.R;
import com.example.food_order.interfaces.randomRcvClickListener;
import com.example.food_order.models.Recipe;
import com.google.android.material.badge.BadgeUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class rcv_recipe_Adapter extends RecyclerView.Adapter<rcv_recipe_Adapter.rcv_recipe_AdapterViewHolder>{
    Context context;
    ArrayList<Recipe> recipesList=new ArrayList<>();
    randomRcvClickListener listener;

    public rcv_recipe_Adapter(Context context, ArrayList<Recipe> recipesList,randomRcvClickListener listener) {
        this.context = context;
        this.recipesList = recipesList;
        this.listener=listener;
    }

    @NonNull
    @Override
    public rcv_recipe_AdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_random_layout,null,false);
        rcv_recipe_AdapterViewHolder newRcipelayout=new rcv_recipe_AdapterViewHolder(v);
        return newRcipelayout;
    }

    @Override
    public void onBindViewHolder(@NonNull rcv_recipe_AdapterViewHolder holder, int position) {
        Recipe newRecipeModel=recipesList.get(position);
        String [] title=newRecipeModel.title.split(" ");
        if (title.length>1)
            holder.recipe_title.setText(title[1]);
        else
            holder.recipe_title.setText(newRecipeModel.title);
        holder.rcipe_instructions.setText(newRecipeModel.instructions);
        holder.rcipe_aggregateLikes.setText(String.valueOf(newRecipeModel.aggregateLikes));
        holder.rcipe_healthScore.setText(String.valueOf(newRecipeModel.healthScore));
        holder.rcipe_preparationMinutes.setText(String.valueOf(newRecipeModel.readyInMinutes));
        Picasso.get().load(newRecipeModel.image).into(holder.imageView);
        holder.readMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.randomRcvClickListener(String.valueOf(newRecipeModel.id));
            }
        });

    }

    @Override
    public int getItemCount() {
        return recipesList.size();
    }

    class rcv_recipe_AdapterViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView recipe_title,rcipe_instructions,rcipe_aggregateLikes,
                rcipe_preparationMinutes,rcipe_healthScore;
        Button readMore;
        public rcv_recipe_AdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.rcipe_image);
            rcipe_healthScore=itemView.findViewById(R.id.rcipe_healthScore);
            rcipe_aggregateLikes=itemView.findViewById(R.id.rcipe_aggregateLikes);
            rcipe_preparationMinutes=itemView.findViewById(R.id.rcipe_preparationMinutes);
            recipe_title=itemView.findViewById(R.id.recipe_title);
            rcipe_instructions=itemView.findViewById(R.id.rcipe_instructions);
            readMore=itemView.findViewById(R.id.btn_continue);

        }
    }
}
