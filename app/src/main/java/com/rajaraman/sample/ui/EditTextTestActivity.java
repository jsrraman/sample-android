package com.rajaraman.sample.ui;

import android.app.Activity;
import android.os.Bundle;
import android.text.InputFilter;
import android.widget.EditText;
import android.widget.TextView;

import com.noveogroup.android.log.Log;
import com.rajaraman.sample.R;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.BeforeTextChange;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.TextChange;
import org.androidannotations.annotations.ViewById;
import org.parceler.apache.commons.lang.StringUtils;

@EActivity(R.layout.activity_edittext_test)
public class EditTextTestActivity extends Activity {
    @ViewById(R.id.edittext)
    EditText editText;

    InputFilter[] emailInputFilter;
    InputFilter[] mobileInputFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @AfterViews
    protected void initViews() {
        createInputFilters();
    }

    private void createInputFilters() {
        emailInputFilter = new InputFilter[]{
                new InputFilter.LengthFilter(254)};

        mobileInputFilter = new InputFilter[]{
                new InputFilter.LengthFilter(10)};
    }

    @BeforeTextChange(R.id.edittext)
    protected void onBeforeTextChangeEditText(TextView textview, CharSequence s, int start, int count, int after) {
        Log.d("Start->" + start + " Count->" + count + " After->" + after);
    }

    @TextChange(R.id.edittext)
    protected void onTextChangeEditText(CharSequence s, int start, int before, int count) {
        if (StringUtils.isNumeric(s.toString())) {
            editText.setFilters(mobileInputFilter);
        } else {
            editText.setFilters(emailInputFilter);
        }
    }
}