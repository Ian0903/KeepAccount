package com.example.ian.keepaccount.data.source.local;

import android.content.Context;
import android.util.Log;

import com.example.ian.keepaccount.App;
import com.example.ian.keepaccount.data.model.MyObjectBox;

import io.objectbox.BoxStore;
import io.objectbox.android.AndroidObjectBrowser;

public class ObjectBox {

    private static BoxStore boxStore;

    public static void init(Context context) {
        boxStore = MyObjectBox.builder()
                .androidContext(context.getApplicationContext())
                .build();
        if (false) {
            boolean started = new AndroidObjectBrowser(boxStore).start(App.getContext());
            Log.i("ObjectBrowser", "Started: " + started);
        }
    }

    public static BoxStore get() {
        return boxStore;
    }
}
