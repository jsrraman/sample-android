package com.rajaraman.sample.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.rajaraman.sample.R;
import com.rajaraman.sample.utils.AppConstants;
import com.rajaraman.sample.utils.ProgressBarHelper;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_web_content)
public class WebContentActivity extends AppCompatActivity {
    @ViewById(R.id.web_content_toolbar)
    Toolbar toolbar;

    @ViewById(R.id.web_content_toolbar_title)
    TextView toolbarTitleTextView;

    @ViewById(R.id.webview)
    WebView webView;

    private ProgressBarHelper progressBarHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @AfterViews
    protected void initViews() {
        progressBarHelper = new ProgressBarHelper();
        progressBarHelper.showIndeterminateProgressDialog(this, getString(R.string.loading));

        Intent intent = getIntent();

        String title = intent.getStringExtra(AppConstants.INTENT_TITLE_STRING);
        toolbarTitleTextView.setText(title);

        String url = intent.getStringExtra(AppConstants.INTENT_URL_TO_BE_LOADED);

//        webView.setInitialScale(1);

        WebSettings webSettings = webView.getSettings();

        // Other web view settings to facilitate easy content consumption
//        webSettings.setBuiltInZoomControls(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setSupportZoom(true);
        webSettings.setUseWideViewPort(true);
//        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);

        // Enable Javascript in web view
        webSettings.setJavaScriptEnabled(true);

        webView.setWebViewClient(new WebViewClient());

        webView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                if (progress == 100) {
                    progressBarHelper.dismissProgressDialog();
                }
            }
        });

        webView.loadUrl(url);
    }

    private void closeActivity() {
        finish();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        closeActivity();
    }
}