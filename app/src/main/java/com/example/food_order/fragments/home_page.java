package com.example.food_order.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.food_order.R;
import com.example.food_order.adapters.rcv_categories_adapter;
import com.example.food_order.adapters.rcv_recipe_Adapter;
import com.example.food_order.assitent.loading_dialog;
import com.example.food_order.connections.retrofitRequestManager;
import com.example.food_order.databinding.FragmentHomePageBinding;
import com.example.food_order.interfaces.RandomRecipResponseListener;
import com.example.food_order.interfaces.randomRcvClickListener;
import com.example.food_order.interfaces.rcv_ctegories_listener;
import com.example.food_order.models.RandomRecipeApiResponse;
import com.example.food_order.models.categories_element_model;
import com.example.food_order.models.food_model;
import com.google.android.material.progressindicator.CircularProgressIndicator;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link home_page#newInstance} factory method to
 * create an instance of this fragment.
 */
public class home_page extends Fragment {
    loading_dialog dialog;
    rcv_recipe_Adapter rcv_recipe_adapter;
    ArrayList<categories_element_model> itemList=new ArrayList<>() ;
    ArrayList<food_model> itemFoodList=new ArrayList<>() ;
    FragmentHomePageBinding binding;
    getClickedRandomRecipeListener listener;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_Tags = "Tags";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mTags;
    private String mParam2;

    public home_page() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param tags Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment home_page.
     */
    // TODO: Rename and change types and number of parameters
    public static home_page newInstance(String tags, String param2) {
        home_page fragment = new home_page();
        Bundle args = new Bundle();
        args.putString(ARG_Tags, tags);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof getClickedRandomRecipeListener)
            listener=(getClickedRandomRecipeListener) context;
        else
            throw new RuntimeException("please Implement getClickedRandomRecipeListener");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener=null;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mTags = getArguments().getString(ARG_Tags);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding=FragmentHomePageBinding.inflate(inflater,container,false);
        Spannable wordspan=new SpannableString("Select your Favourite Meals");
        wordspan.setSpan(new ForegroundColorSpan(getActivity().getColor(R.color.span_text_color)),
                22,27,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        binding.homeCardViewText.setText(wordspan);
        dialog=new loading_dialog(getContext());
        dialog.showDialog("Loading...");
       new retrofitRequestManager(getContext()).getRandomRecipe("main course", new RandomRecipResponseListener() {
           @Override
           public void fetchDone(RandomRecipeApiResponse randomRecipeApiResponse, String message) {
               rcv_recipe_adapter =new rcv_recipe_Adapter(getContext(), randomRecipeApiResponse.recipes, new randomRcvClickListener() {
                   @Override
                   public void randomRcvClickListener(String recipeID) {

                   }
               });
               binding.homeRCVTopOfWeek.setAdapter(rcv_recipe_adapter);
               dialog.hideDialog();
           }

           @Override
           public void fetchFaild(String message) {
               Toast.makeText(getContext(),"failed",Toast.LENGTH_SHORT).show();
           }
       });

        itemList.add(new categories_element_model(R.drawable.main_course,"main course"));
        itemList.add(new categories_element_model(R.drawable.bread,"bread"));
        itemList.add(new categories_element_model(R.drawable.marinade,"marinade"));
        itemList.add(new categories_element_model(R.drawable.side_dish,"side dish"));
        itemList.add(new categories_element_model(R.drawable.breakfast,"breakfast"));
        itemList.add(new categories_element_model(R.drawable.finger_food,"fingerfood"));
        itemList.add(new categories_element_model(R.drawable.dessert,"dessert"));
        itemList.add(new categories_element_model(R.drawable.soup,"soup"));
        itemList.add(new categories_element_model(R.drawable.snack,"snack"));
        itemList.add(new categories_element_model(R.drawable.appetizer,"appetizer"));
        itemList.add(new categories_element_model(R.drawable.beverage,"beverage"));
        itemList.add(new categories_element_model(R.drawable.drink,"drink"));
        itemList.add(new categories_element_model(R.drawable.salad,"salad"));
        itemList.add(new categories_element_model(R.drawable.sauce,"sauce"));
        binding.homeRCVCategories.setAdapter(new rcv_categories_adapter(itemList,
                getActivity().getBaseContext(), new rcv_ctegories_listener() {
            @Override
            public void categorieClciked(String click) {
                new retrofitRequestManager(getContext())
                        .getRandomRecipe(click,new RandomRecipResponseListener() {
                            @Override
                            public void fetchDone(RandomRecipeApiResponse randomRecipeApiResponse, String message) {
                                rcv_recipe_adapter =new rcv_recipe_Adapter(getContext(), randomRecipeApiResponse.recipes, new randomRcvClickListener() {
                                    @Override
                                    public void randomRcvClickListener(String recipeID) {

                                    }
                                });
                                binding.homeRCVTopOfWeek.setAdapter(rcv_recipe_adapter);
                            }

                            @Override
                            public void fetchFaild(String message) {
                                Toast.makeText(getActivity(),message,Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        }));

        return binding.getRoot();
    }

    public interface getClickedRandomRecipeListener{
        void  getClickedRandomRecipeId(String ClickecRecipeId);
    }

}
