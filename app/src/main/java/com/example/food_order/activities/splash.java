package com.example.food_order.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.food_order.R;
import com.example.food_order.databinding.ActivitySplashBinding;

public class splash extends AppCompatActivity  {
    ActivitySplashBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //set theme
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getColor(R.color.firstColor));
        }


        binding.btnContinue.setOnClickListener(new View.OnClickListener() {
            int c=0;
            @Override
            public void onClick(View view) {
                c++;
                if (c == 1) {
                    binding.index01.setImageDrawable(getDrawable(R.drawable.slide_index_not_selected));
                    binding.index02.setImageDrawable(getDrawable(R.drawable.slide_index));
                    binding.index03.setImageDrawable(getDrawable(R.drawable.slide_index_not_selected));
                    binding.slideImage.setImageDrawable(getDrawable(R.drawable.img02));
                    binding.slideText.setText(getString(R.string.slide_text02));
                } else if (c == 2) {
                    binding.index01.setImageDrawable(getDrawable(R.drawable.slide_index_not_selected));
                    binding.index02.setImageDrawable(getDrawable(R.drawable.slide_index_not_selected));
                    binding.index03.setImageDrawable(getDrawable(R.drawable.slide_index));
                    binding.slideImage.setImageDrawable(getDrawable(R.drawable.img03));
                    binding.slideText.setText(getString(R.string.slide_text03));
                    binding.btnContinue.setText("Get Started");
                }else{
                    Intent i=new Intent(getApplicationContext(), home.class);
                    startActivity(i);
                }
            }
        });


    }


}