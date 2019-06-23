package com.example.ian.keepaccount.utils;

import android.widget.Toast;

import com.example.ian.keepaccount.App;

public class ToastUtils {

    public static void show(String message){
        Toast.makeText(App.getContext(),message,Toast.LENGTH_SHORT).show();
    }
}
