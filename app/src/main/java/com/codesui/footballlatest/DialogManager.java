package com.codesui.footballlatest;

import android.content.Context;
import android.view.View;

import androidx.appcompat.app.AlertDialog;

import com.codesui.footballlatest.Utility.RunFunction;

public class DialogManager {
    private AlertDialog alertDialog;
    private Context context;
    private String text;
    private RunFunction function;

    public DialogManager(Context context, String text, RunFunction function) {
        this.context = context;
        this.text = text;
        this.function = function;
    }


    public void createDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("An error occurred while trying to fetch " + text + " Do you want to exit?");
        builder.setTitle("Error!");
        builder.setCancelable(false);
        builder.setPositiveButton("Try Again", (dialog, which) -> {
            dialog.cancel();
            function.execute();
        });
        builder.setNegativeButton("Ok", (dialog, which) -> {
            dialog.cancel();
            //textEmpty.setVisibility(View.VISIBLE);
        });
        alertDialog = builder.create();
    }

    public void showDialog(){
        alertDialog.show();
    }
}
