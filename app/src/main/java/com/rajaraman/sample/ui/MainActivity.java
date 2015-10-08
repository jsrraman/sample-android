package com.rajaraman.sample.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.StyleSpan;
import android.text.style.UnderlineSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.rajaraman.sample.CustomLinkMovementMethod;
import com.rajaraman.sample.R;
import com.rajaraman.sample.utils.AppUtil;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_main)
public class MainActivity extends Activity {

    private static final String TERMS_AND_CONDITIONS_LINK = "http://stayzilla.com/terms";
    private static final int TERMS_AND_CONDITIONS_START_SPAN = 11;
    private static final int TERMS_AND_CONDITIONS_END_SPAN = 31;

    private CallbackManager callbackManager;

    @ViewById(R.id.main_terms_and_conditions)
    TextView termsAndConditionsTextView;

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
        AppUtil.setupTermsAndConditions(this, termsAndConditionsTextView);
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
        AppUtil.checkJsonObjectBehaviour();
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

    @Click(R.id.button_lengthy_calc)
    public void onClickLengthyCalc(View view) {
        doLengthyCalc((Button) view);
    }

    @UiThread
    public void updateLengthCalcButtonProperty(Button button) {
        button.setText("Lengthy Operation Completed");
    }

    @Background
    public void doLengthyCalc(Button button) {
        try {
            Thread.sleep(15 * 1000);
            updateLengthCalcButtonProperty(button);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Click(R.id.main_button_edittext_test)
    public void onClickTestEditText(View view) {
        Intent intent = new Intent(this, EditTextTestActivity_.class);
        startActivity(intent);
    }

    @Click(R.id.main_button_imageview_test)
    public void onClickTestImageViewText(View view) {
        Intent intent = new Intent(this, ImageViewTestActivity_.class);
        startActivity(intent);
    }

    @Click(R.id.main_button_fb)
    public void onClickFBButton(View view) {
        Intent intent = new Intent(this, FBActivity_.class);
        startActivity(intent);
    }

    @Click(R.id.main_button_dialog_activity)
    public void onClickDialogActivityButton(View view) {
        Intent intent = new Intent(this, OTPVerificationDialogActivity_.class);
        startActivity(intent);
    }
}