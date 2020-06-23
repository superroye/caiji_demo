package com.supylc.bimo;

import android.app.Application;

import com.supylc.uilibs.talent.BaseApp;

public class MyApp extends Application {

    public static Application app;

    @Override
    public void onCreate() {
        super.onCreate();

        app = this;

        BaseApp.Companion.initApp(app);
    }
}
