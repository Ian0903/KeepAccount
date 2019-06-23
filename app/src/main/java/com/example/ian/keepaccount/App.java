package com.example.ian.keepaccount;

import android.app.Application;
import android.content.Context;

import com.example.ian.keepaccount.data.source.local.ObjectBox;

public class App extends Application {

    private static App app;
    private static Context context;
    private static String curBook;

    @Override
    public void onCreate(){
        super.onCreate();
        context = getApplicationContext();
        app = new App();
        ObjectBox.init(this);
    }

    public static Context getContext(){
        return context;
    }

    public static App getApplication(){
        return app;
    }

    public static String getCurBook (){
        return curBook;
    }

    public static void setCurBook(String curBook) {
        App.curBook = curBook;
    }
}
