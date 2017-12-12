package com.tomtom.canteen_mama_404;

import android.app.Application;
import android.content.Context;

public class LoaderActivity extends Application {

    private static Context context;

    public void onCreate() {
        super.onCreate();
        LoaderActivity.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return LoaderActivity.context;
    }
}
