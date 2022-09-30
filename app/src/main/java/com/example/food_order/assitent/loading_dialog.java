package com.example.food_order.assitent;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.widget.TextView;

import com.example.food_order.R;

public class loading_dialog extends ProgressDialog {
    Context context;
    Dialog dialog;

    public loading_dialog(Context context) {
        super(context);
        this.context = context;
    }
    public void showDialog(String title){
        dialog=new Dialog(context, com.google.android.material.R.style.ThemeOverlay_Material3_MaterialCalendar_Fullscreen);
        dialog.setContentView(R.layout.dialog_custom_layout);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        TextView di_txt=dialog.findViewById(R.id.title_dialog_loding_fragment);
        di_txt.setText(title);
        dialog.create();
        dialog.show();
    }
    public  void hideDialog(){ dialog.dismiss();}

    @Override
    public void setCanceledOnTouchOutside(boolean cancel) {
        super.setCanceledOnTouchOutside(cancel);
    }
}
