package com.rajaraman.sample.utils;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.net.ConnectivityManager;

import com.noveogroup.android.log.Log;
import com.rajaraman.sample.MyApplication;

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
}