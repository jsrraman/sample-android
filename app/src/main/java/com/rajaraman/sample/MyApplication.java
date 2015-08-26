package com.rajaraman.sample;

import android.app.Application;
import android.content.Context;

import com.facebook.FacebookSdk;
import com.facebook.stetho.Stetho;
import com.noveogroup.android.log.Log;

public class MyApplication extends Application {

    private static Context context;

    public static Context getContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("onCreate");

        context = getApplicationContext();

        // Facebook SDK intialization
        FacebookSdk.sdkInitialize(getApplicationContext());

        // Facebook Stetho library initialization
        Stetho.initialize(Stetho.newInitializerBuilder(this).enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                .build());
    }
}