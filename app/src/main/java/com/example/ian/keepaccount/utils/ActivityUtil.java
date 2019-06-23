package com.example.ian.keepaccount.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.ian.keepaccount.data.model.Account;

public class ActivityUtil {

    public static void startActivity(Context context,Class nextActivity){
        Intent intent = new Intent(context,nextActivity);
        context.startActivity(intent);
    }

    public static void startActivityWithAccount(Context context, Class nextActivity, Account account){
        Intent intent = new Intent(context,nextActivity);
        Bundle bundle = new Bundle();
        bundle.putSerializable("key_account",account);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
}
