package com.rajaraman.sample.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.location.Address;
import android.location.Geocoder;
import android.net.ConnectivityManager;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.TextView;

import com.noveogroup.android.log.Log;
import com.rajaraman.sample.MyApplication;
import com.rajaraman.sample.R;
import com.rajaraman.sample.ui.WebContentActivity_;

import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AppUtil {

    public static boolean isEmailValid(String email) {

// Email Regex pattern description
//        ^			        #  start of the line
//        [_A-Za-z0-9-\\+]+	#  must start with string in the bracket [ ], must contains one or more (+)
//        (			        #  start of group #1
//        \\.[_A-Za-z0-9-]+	#  follow by a dot "." and string in the bracket [ ], must contains one or more (+)
//        )*			    #  end of group #1, this group is optional (*)
//        @			        #  must contains a "@" symbol
//          [A-Za-z0-9-]+   #  follow by string in the bracket [ ], must contains one or more (+)
//        (			        #  start of group #2 - first level TLD checking
//        \\.[A-Za-z0-9]+   #  follow by a dot "." and string in the bracket [ ], must contains one or more (+)
//        )*		        #  end of group #2, this group is optional (*)
//        (			        #  start of group #3 - second level TLD checking
//        \\.[A-Za-z]{2,}   #  follow by a dot "." and string in the bracket [ ], with minimum length of 2
//        )			        #  end of group #3
//        $			        #end of the line
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }

    public static boolean isPasswordValid(String password) {
        return ((password == null) || (password.isEmpty())) ? false : true;
    }

    public static boolean isMobileNumValid(String mobile) {
        return ((mobile.isEmpty()) ||
                (!StringUtils.isNumeric(mobile)) ||
                ((mobile.length() < 10)) ? false : true);
    }

    public static boolean isUsernameValid(String username) {

        // If the username is mobile, then it should be a valid mobile number
        boolean valid = isMobileNumValid(username);

        // If not, check whether it is a valid email address
        if (!valid) {
            valid = isEmailValid(username);
        }

        return valid;
    }

    public static boolean isNetworkAvailable(Context context) {
        return ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).
                getActiveNetworkInfo() != null;
    }

    public static void deleteDb(String dbName) {
        // Note:
        // As we are using DBFlow library for handling the sqlite, the proper way of deleting
        // the database will be to call FlowManager.getDatabase(database_name).reset() but that will
        // not work for the inactive databases (It will throw the below error)
        // InvalidDBConfiguration("The specified database" + databaseName + " was not found. " + "Did you forget the @Database annotation?");
        // so using the actual android API to delete the database
        boolean value = MyApplication.getContext().deleteDatabase(dbName + ".db");

        if (value) {
            Log.d("Database " + dbName + " deleted successfully");
        } else {
            Log.d("Database " + dbName + " could not be deleted");
        }
    }

    // Code to get \/ in your JSON string
    public static void checkJsonObjectBehaviour() {
        JSONObject reader = null;
        String jsonString = "{\"PAYMENT_DETAILS\":\"abcd\\\\/efgh\"}";
        String value;

        try {
            reader = new JSONObject(jsonString);
        } catch (JSONException e) {
            android.util.Log.d("error", "Exception->" + e.getMessage());
        }

        if (reader != null) {
            try {
                value = reader.getString("PAYMENT_DETAILS");
            } catch (JSONException e) {
                value = "";
            }

            android.util.Log.d("debug", value);
        }
    }

    public static void getAddress(Context appContext) {
        Geocoder geocoder = new Geocoder(appContext, Locale.getDefault());

        try {
            List<Address> addressList = geocoder.getFromLocationName("Kanyakumari", 1);
//            List<Address> addressList = geocoder.getFromLocation(13.052414, 80.250825, 1);

            int i = 0;
            i += 1;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setupTermsAndConditions(final Activity activity, TextView textView) {
        final int TERMS_AND_CONDITIONS_START_SPAN = 11;
        final int TERMS_AND_CONDITIONS_END_SPAN = 31;

        SpannableString termsAndConditions =
                new SpannableString(activity.getString(R.string.terms_and_conditions_desc));

        // Make the spannable text bold
        termsAndConditions.setSpan(new StyleSpan(Typeface.BOLD), TERMS_AND_CONDITIONS_START_SPAN,
                TERMS_AND_CONDITIONS_END_SPAN, Spanned.SPAN_INCLUSIVE_INCLUSIVE);

        // Set the clickable action for the spannable text
        termsAndConditions.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View view) {
                showWebContent(activity, AppConstants.TERMS_AND_CONDITIONS_URL,
                        activity.getString(R.string.terms_and_conditions));
            }
        }, TERMS_AND_CONDITIONS_START_SPAN, TERMS_AND_CONDITIONS_END_SPAN, Spanned.SPAN_INCLUSIVE_INCLUSIVE);

        // Set the colour of spannable text
        // Note: setSpan order is important. Here the link colour should be customized. So calling the
        // ForegroundColorSpan as a last statement to get that effect. Had it been called before
        // ClickableSpan then the default link color would have been set
        termsAndConditions.setSpan(new ForegroundColorSpan(ContextCompat.getColor(activity,
                        R.color.sz_pink_color)), TERMS_AND_CONDITIONS_START_SPAN,
                TERMS_AND_CONDITIONS_END_SPAN, Spanned.SPAN_INCLUSIVE_INCLUSIVE);

        textView.setText(termsAndConditions);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }

    public static void showWebContent(Activity activity, String urlToBeLoaded,
                                      String backButtonString) {
        Intent intent = new Intent(activity, WebContentActivity_.class);

        intent.putExtra(AppConstants.INTENT_URL_TO_BE_LOADED, urlToBeLoaded);
        intent.putExtra(AppConstants.INTENT_TITLE_STRING, backButtonString);

        activity.startActivity(intent);
    }
}