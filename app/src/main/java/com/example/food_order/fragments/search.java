package com.example.food_order.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.food_order.adapters.rcv_recipe_autoCompleteSearch_Adapter;
import com.example.food_order.connections.retrofitRequestManager;
import com.example.food_order.databinding.FragmentSearchBinding;
import com.example.food_order.interfaces.searchRecipeResponseListener;
import com.example.food_order.models.autoCompleteSearchRecipe;
import com.example.food_order.models.Product;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link search#newInstance} factory method to
 * create an instance of this fragment.
 */
public class search extends Fragment {
    FragmentSearchBinding binding;
    ArrayList<Product> newRecipeList =new ArrayList<>();


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_SearchKey = "SearchKey";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String searchKey;
    private String mParam2;

    public search() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param searchKey Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment search_fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static search newInstance(String searchKey, String param2) {
        search fragment = new search();
        Bundle args = new Bundle();
        args.putString(ARG_SearchKey, searchKey);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            searchKey = getArguments().getString(ARG_SearchKey);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding=FragmentSearchBinding.inflate(inflater,container,false);
        new retrofitRequestManager(getContext()).getSearchAutoComplete(searchKey, new searchRecipeResponseListener() {
            @Override
            public void fetchDone(autoCompleteSearchRecipe autoCompleteSearchRecipe, String message) {
                newRecipeList=autoCompleteSearchRecipe.products;
                binding.rcvRecipeAutoComplete.setAdapter(new rcv_recipe_autoCompleteSearch_Adapter(newRecipeList,getContext()));
            }

            @Override
            public void fetchFaild(String message) {
                Toast.makeText(getActivity(), "Fetch faild "+message, Toast.LENGTH_SHORT).show();
            }
        });
        return binding.getRoot();
    }
}