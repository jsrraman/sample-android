package com.rajaraman.sample.ui;

import android.app.Activity;
import android.os.Bundle;

import com.rajaraman.sample.R;

import org.androidannotations.annotations.EActivity;

@EActivity(R.layout.activity_test)
public class TestActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}