package com.supylc.bimo;

import android.app.Application;
import android.util.Log;

import com.supylc.uilibs.talent.BaseApp;

public class MyApp extends Application {

    public static Application app;
    static final String TAG = "MyApp";

    @Override
    public void onCreate() {
        super.onCreate();

        Log.d(TAG, "=================================onCreate");
        app = this;

        BaseApp.Companion.initApp(app);
    }
}
