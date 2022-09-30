package com.example.food_order.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.food_order.R;
import com.example.food_order.fragments.home_page;
import com.example.food_order.interfaces.rcv_ctegories_listener;
import com.example.food_order.models.categories_element_model;

import java.util.ArrayList;

public class rcv_categories_adapter extends RecyclerView.Adapter<rcv_categories_adapter.categorie> {

    ArrayList<categories_element_model> itemList=new ArrayList<>() ;
    rcv_ctegories_listener Rcv_ctegories_listener;
    Context context;
    private int selectedPosition = -1;

    public rcv_categories_adapter(ArrayList<categories_element_model> itemList,Context context,rcv_ctegories_listener rcv_ctegories_listener) {
        this.itemList = itemList;
        this.context=context;
        Rcv_ctegories_listener = rcv_ctegories_listener;
    }


    @NonNull
    @Override
    public categorie onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_rcv_categories_layout,null,false);
       categorie c=new categorie(view);
        return c;
    }

    @Override
    public void onBindViewHolder(@NonNull categorie holder, int position) {
        categories_element_model model=itemList.get(position);
        String name=model.getName();
        String newname=model.getName();
        holder.imageView.setImageDrawable(context.getDrawable(model.getElement_image()));
        holder.categorie_name.setText(model.getName());
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Rcv_ctegories_listener.categorieClciked(model.getName());
//            }
//        });
        if (selectedPosition == position) {
            holder.categorie_card.setBackgroundColor(context.getColor(R.color.firstColor));
            holder.categorie_name.setTextColor(context.getColor(R.color.white));
        } else {
            holder.categorie_card.setBackgroundColor(context.getColor(R.color.white));
            holder.categorie_name.setTextColor(context.getColor(R.color.black));
        }
        holder.itemView.setOnClickListener(v -> {
            if (selectedPosition >= 0)
                notifyItemChanged(selectedPosition);
            selectedPosition = holder.getAdapterPosition();
            notifyItemChanged(selectedPosition);
            Rcv_ctegories_listener.categorieClciked(model.getName());

        });

    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    class categorie extends RecyclerView.ViewHolder {
        de.hdodenhof.circleimageview.CircleImageView imageView;
        LinearLayout categorie_card;
        TextView categorie_name;
        public categorie(@NonNull View itemView) {
            super(itemView);
            imageView =itemView.findViewById(R.id.rcv_item_image);
            categorie_card=itemView.findViewById(R.id.item_categorie_card);
            categorie_name=itemView.findViewById(R.id.categorie_name);

        }
    }
}
