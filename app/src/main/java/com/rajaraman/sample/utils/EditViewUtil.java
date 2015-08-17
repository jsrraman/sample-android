package com.rajaraman.sample.utils;

import android.text.InputFilter;
import android.widget.EditText;

public class EditViewUtil {
    public static final int FILTER_TYPE_MOBILE = 1;
    public static final int FILTER_TYPE_EMAIL = 2;

    private static InputFilter[] emailTextInputFilter;
    private static InputFilter[] mobileTextInputFilter;

    public static InputFilter[] createFilter(int filterType) {
        InputFilter[] inputFilters;

        switch (filterType) {
            case FILTER_TYPE_MOBILE: {
                inputFilters = createLengthFilter(ValidationUtil.MAX_EMAIL_LEN);
                break;
            }

            case FILTER_TYPE_EMAIL: {
                inputFilters = createLengthFilter(ValidationUtil.MAX_MOBILE_LEN);
                break;
            }

            default: {
                inputFilters = null;
                break;
            }
        }

        return inputFilters;
    }

    private static InputFilter[] createLengthFilter(int length) {
        return new InputFilter[]{
                new InputFilter.LengthFilter(length)
        };
    }

    public static void initUserNameTextInputFilters() {
        createUserNameTextInputFilters();
    }

    private static void createUserNameTextInputFilters() {
        emailTextInputFilter = new InputFilter[]{
                new InputFilter.LengthFilter(ValidationUtil.MAX_EMAIL_LEN)};

        mobileTextInputFilter = new InputFilter[]{
                new InputFilter.LengthFilter(ValidationUtil.MAX_MOBILE_LEN)};
    }

    public static void setUserNameTextInputFilter(EditText editText, CharSequence s) {
        if (ValidationUtil.isNumeric(s.toString())) {
            editText.setFilters(mobileTextInputFilter);
        } else {
            editText.setFilters(emailTextInputFilter);
        }
    }
}