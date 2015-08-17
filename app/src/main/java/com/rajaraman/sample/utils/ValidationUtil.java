package com.rajaraman.sample.utils;

import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUtil {
    public static final int MAX_MOBILE_LEN = 10;
    public static final int MAX_EMAIL_LEN = 254;

    public enum ValidationRules {
        EMAIL, MOBILE
    }

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
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches();
    }

    public static boolean isNullInteger(int number) {
        return (number != 0);
    }

    public static boolean isNullOrEmptyString(String password) {
        return (!((password == null) || (password.isEmpty())));
    }

    public static boolean isNumeric(String str) {
        if (str == null) {
            return false;
        }

        int sz = str.length();

        for (int i = 0; i < sz; i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    public static boolean isOnlyContainsAlphabets(String str) {
        if (str == null) {
            return false;
        }

        int sz = str.length();

        for (int i = 0; i < sz; i++) {
            if (!Character.isLetter(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isMobileNumberValid(String mobile) {
        return (!((mobile.isEmpty()) ||
                (!isNumeric(mobile)) ||
                (!(mobile.length() == MAX_MOBILE_LEN))));
    }

    public static boolean validate(EditText editText, ValidationRules validationRule,
                                   String errorMessage, boolean isMandatory) {

        final String value = editText.getText().toString();
        final boolean validationResult = validate(value, validationRule, isMandatory);
        if (!validationResult) {
            if (errorMessage == null) {
                errorMessage = "Please provide valid input";
            }
            editText.setError(errorMessage);
        } else {
            editText.setError(null); //Clearing the error message.
        }

        return validationResult;
    }

    public static boolean validateSpinner(Spinner spinner, String errorMessage) {
        if (spinner.getSelectedItemPosition() == 0) {
            TextView view = (TextView) spinner.getSelectedView();
            view.setError(errorMessage);
            return false;
        }
        return true;
    }

    public static boolean validate(String data, ValidationRules validationRule, boolean isMandatory) {
        //Early exit for null data.
        if (data == null) {
            return false;
        }

        final boolean isEmpty = data.trim().isEmpty();

        if (!isMandatory && isEmpty) {
            return true; //Non mandatory empty value. skip validation.
        }

        if (isMandatory && isEmpty) {
            return false; //Value should not be empty.
        }

        if (validationRule == null) {
            return true; //No special validation needed.
        }

        if (validationRule == ValidationRules.EMAIL && !isEmailValid(data)) {
            return false; //Invalid email.

        } else if (validationRule == ValidationRules.MOBILE && !isMobileNumberValid(data)) {
            return false; //Invalid Mobile no.
        }

        return true;
    }
}