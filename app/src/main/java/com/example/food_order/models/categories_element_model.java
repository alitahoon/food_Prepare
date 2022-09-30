package com.example.food_order.models;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

public class categories_element_model {
    int element_image;
    String name;

    public categories_element_model(int element_image, String name) {
        this.element_image = element_image;
        this.name = name;
    }

    public int getElement_image() {
        return element_image;
    }

    public void setElement_image(int element_image) {
        this.element_image = element_image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
