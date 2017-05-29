package com.lihaizhou.greendao3;

/**
 * Created by lihaizhou on 2017/5/29.
 */

import android.app.Application;
import android.content.Context;

public class MyApplication extends Application {
    private static MyApplication sContext;

    public static Context getApplication() {
        return sContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = this;
    }
}