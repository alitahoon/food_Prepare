package com.example.food_order.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.food_order.R;
import com.example.food_order.connections.retrofitRequestManager;
import com.example.food_order.databinding.FragmentDetailsBinding;
import com.example.food_order.interfaces.getRecipeWithIDListener;
import com.example.food_order.models.getRecipeResponse;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link details#newInstance} factory method to
 * create an instance of this fragment.
 */
public class details extends Fragment {
    FragmentDetailsBinding binding;
    ArrayList<getRecipeResponse> recipeDataList=new ArrayList<>();

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_RecipeID = "recipeID";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mRecipeID;
    private String mParam2;

    public details() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param recipeID Parameter 1.
     * @return A new instance of fragment details.
     */
    // TODO: Rename and change types and number of parameters
    public static details newInstance(String recipeID) {
        details fragment = new details();
        Bundle args = new Bundle();
        args.putString(ARG_RecipeID, recipeID);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mRecipeID = getArguments().getString(ARG_RecipeID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding=FragmentDetailsBinding.inflate(inflater,container,false);

        //fetch data from the server
        new retrofitRequestManager(getContext()).getRecipeWithID(Integer.parseInt(mRecipeID), false, new getRecipeWithIDListener() {
            @Override
            public void fetchDone(getRecipeResponse getRecipeResponse, String message) {
                binding.itemTitle.setText(getRecipeResponse.title);
                binding.itemInstructions.setText(getRecipeResponse.instructions);
                Picasso.get().load(getRecipeResponse.image).into(binding.itemImage);
            }

            @Override
            public void fetchFaild(String message) {
                Toast.makeText(getContext(), "Fetch recipe data failed "+message, Toast.LENGTH_SHORT).show();
            }
        });

        return binding.getRoot();
    }
}