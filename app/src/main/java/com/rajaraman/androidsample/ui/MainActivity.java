package com.rajaraman.androidsample.ui;

import android.app.Activity;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.rajaraman.androidsample.CustomLinkMovementMethod;
import com.rajaraman.androidsample.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

@EActivity(R.layout.activity_main)
public class MainActivity extends Activity {

    private CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // This needs to be called before setContentView
        FacebookSdk.sdkInitialize(getApplicationContext());
    }

    @AfterViews
    protected void initViews() {
        showHtmlLinkInTextView();
        showCustomLinkToActivityInTextView();
    }

    private void showHtmlLinkInTextView() {
        TextView textview = (TextView) findViewById(R.id.main_tv1);
        textview.setMovementMethod(LinkMovementMethod.getInstance());
        String text = "Go to <a href=http://www.google.com>google</a>";
        textview.setText(Html.fromHtml(text));
    }

    private void showCustomLinkToActivityInTextView() {
        TextView textview = (TextView) findViewById(R.id.main_tv2);
        String text = "Go to <a href='szapp://TestActivity'><b>Test Activity</b></a>";
        textview.setText(Html.fromHtml(text));
        textview.setMovementMethod(CustomLinkMovementMethod.getInstance(this));
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();

        //getAddress();

        checkJsonObjectBehaviour();
    }

    // Code to get \/ in your JSON string
    private void checkJsonObjectBehaviour() {
        JSONObject reader = null;
        String jsonString = "{\"PAYMENT_DETAILS\":\"abcd\\\\/efgh\"}";
        String value;

        try {
            reader = new JSONObject(jsonString);
        } catch (JSONException e) {
            Log.d("error", "Exception->" + e.getMessage());
        }

        if (reader != null) {
            try {
                value = reader.getString("PAYMENT_DETAILS");
            } catch (JSONException e) {
                value = "";
            }

            Log.d("debug", value);
        }
    }

    private void getAddress() {
        Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());

        try {
            List<Address> addressList = geocoder.getFromLocationName("Kanyakumari", 1);
//            List<Address> addressList = geocoder.getFromLocation(13.052414, 80.250825, 1);

            int i = 0;
            i += 1;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Click(R.id.main_button_fb)
    public void onClickFBButton(View view) {
        Intent intent = new Intent(this, FBActivity_.class);
        startActivity(intent);
    }

    @Click(R.id.main_button_db)
    public void onClickDbButton(View view) {
        Intent intent = new Intent(this, DbActivity_.class);
        startActivity(intent);
    }
}