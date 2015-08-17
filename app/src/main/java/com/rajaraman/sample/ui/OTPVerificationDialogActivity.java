package com.rajaraman.sample.ui;

import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import com.noveogroup.android.log.Log;
import com.rajaraman.sample.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_otp_verification_dialog)
public class OTPVerificationDialogActivity extends AppCompatActivity {
//    final EventBus eventBus = EventBus.getDefault();
//    private ProgressBarHelper progressBarHelper;

    String userID;

    @ViewById(R.id.otp_verification_edittext_otp)
    EditText otpEditText;

    @ViewById(R.id.otp_verification_button_verify)
    Button verifyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("onCreate");

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @AfterViews
    protected void initViews() {
//        progressBarHelper = new ProgressBarHelper();
//        setUserMobileVerifiedStatusInDisk(false);
//        callSendVerificationSmsAPI();
    }

    @Override
    protected void onResume() {
        super.onResume();

        // In Kitkat the top portion of the activity that is shown as dialog is cut
        // so following patch is needed (versions prior to KitKat did not have this issue)
        // Refer the issue as follows
        // http://stackoverflow.com/questions/20121711/dialog-on-android-kitkat-seems-to-be-cut
        // Though in the above link the combination of various other flags suggested the following
        // set of flags (referred from https://developer.android.com/training/system-ui/immersive.html)
        // are enough to avoid the top are cut
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            int uiFlag = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN;

            getWindow().getDecorView().setSystemUiVisibility(uiFlag);
        }
    }

    @Override
    protected void onStart() {
        Log.d("onStart");
        super.onStart();
//        eventBus.registerSticky(this);
    }

    @Override
    protected void onStop() {
        Log.d("onStop");
//        eventBus.unregister(this);
        super.onStop();
    }

//    @Click(R.id.otp_verification_button_back)
//    public void onClickBack(View view) {
//        onBackPressed();
//    }

//    @Click(R.id.otp_verification_button_verify)
//    public void onClickVerify(View view) {
//        String otp = otpEditText.getText().toString();
//        callVerifyUserPhoneAPI(otp);
//    }

//    @TextChange(R.id.otp_verification_edittext_otp)
//    public void onTextChangedOTP(CharSequence s, int start, int before, int count) {
//        if (s.length() == AppConstants.MAX_OTP_LENGTH) {
//            setVerifyButtonProperties(true);
//        } else {
//            setVerifyButtonProperties(false);
//        }
//    }

//    private void setVerifyButtonProperties(boolean enable) {
//        if (enable) {
//            verifyButton.setEnabled(true);
//            verifyButton.setTextColor(getResources().getColor(R.color.sz_pink_color));
//        } else {
//            verifyButton.setEnabled(false);
//            verifyButton.setTextColor(getResources().getColor(R.color.sz_pink_color_disabled));
//        }
//    }

//    private void callSendVerificationSmsAPI() {
//        if (!AppUtil.isNetworkAvailable()) {
//            ToastHelper.showToast(R.string.network_error);
//            return;
//        }
//
//        userID = SharedPreferencesHelper.getString(SharedPreferencesHelper.KEY_USER_ID);
//
//        SendSmsAPIRequestData requestData = new SendSmsAPIRequestData();
//
//        requestData.userID = userID;
//        UserVerificationAPIRestClient.getInstance().sendVerificationSms(requestData);
//
////        progressBarHelper = new ProgressBarHelper();
//        progressBarHelper.showIndeterminateProgressDialog(this, getString(R.string.sending_sms));
//    }

//    private void callVerifyUserPhoneAPI(String otp) {
//        if (!AppUtil.isNetworkAvailable()) {
//            ToastHelper.showToast(R.string.network_error);
//            return;
//        }
//
//        VerifyUserPhoneAPIRequestData requestData = new VerifyUserPhoneAPIRequestData();
//
//        requestData.userID = userID;
//        requestData.otp = otp;
//        requestData.mobile = SharedPreferencesHelper.getString(SharedPreferencesHelper.KEY_USER_MOBILE);
//
//        UserVerificationAPIRestClient.getInstance().verifyUserPhone(requestData);
//
////        progressBarHelper = new ProgressBarHelper();
//        progressBarHelper.showIndeterminateProgressDialog(this, getString(R.string.verifying_sms));
//    }
//
//    public void onEventMainThread(SendSmsAPIResponseEvent event) {
////        if (progressBarHelper != null)
//            progressBarHelper.dismissProgressDialog();
//
//        eventBus.removeStickyEvent(event);
//        handleSmsAPIResponseEvent(event);
//    }
//
//    private void handleSmsAPIResponseEvent(SendSmsAPIResponseEvent event) {
//        if (event.status) {
//            // TODO: Listen for the incoming SMS and populate the otp field
//        } else {
//            ToastHelper.showToast(getString(R.string.send_sms_api_failed));
//        }
//    }
//
//    public void onEventMainThread(VerifyUserPhoneAPIResponseEvent event) {
////        if (progressBarHelper != null)
//            progressBarHelper.dismissProgressDialog();
//
//        eventBus.removeStickyEvent(event);
//        handleVerifyPhoneAPIResponseEvent(event);
//    }
//
//    private void handleVerifyPhoneAPIResponseEvent(VerifyUserPhoneAPIResponseEvent event) {
//        if (event.status) {
//            AppUtil.hideKeyboard(this);
//            setUserMobileVerifiedStatusInDisk(true);
//            closeActivity(Activity.RESULT_OK);
//        } else {
//            ToastHelper.showToast(R.string.user_verification_failed);
//        }
//    }
//
//    private void setUserMobileVerifiedStatusInDisk(boolean status) {
//        SharedPreferencesHelper.set(SharedPreferencesHelper.KEY_USER_MOBILE_VERIFIED, status);
//    }
//
//    private void closeActivity(int resultCode) {
//        setResult(resultCode);
//        finish();
//    }
//
//    @Override
//    public void onBackPressed() {
//        closeActivity(Activity.RESULT_CANCELED);
//    }
}