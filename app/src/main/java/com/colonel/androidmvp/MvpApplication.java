package com.colonel.androidmvp;

import android.app.Application;
import android.content.Context;

/**
 * Created by mi on 17-11-8.
 */

public class MvpApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }

    public static Context getContext() {
        return context;
    }
}