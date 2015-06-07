package com.rajaraman.androidsample.utils;

import android.content.Context;
import android.net.ConnectivityManager;

import com.noveogroup.android.log.Log;
import com.raizlabs.android.dbflow.config.BaseDatabaseDefinition;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.rajaraman.androidsample.MyApplication;

import org.parceler.apache.commons.lang.StringUtils;

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
}