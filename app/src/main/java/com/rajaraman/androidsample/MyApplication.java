package com.rajaraman.androidsample;

import android.app.Application;

import com.facebook.FacebookSdk;
import com.noveogroup.android.log.Log;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("onCreate");

        // Facebook SDK intialization
        FacebookSdk.sdkInitialize(getApplicationContext());
    }
}