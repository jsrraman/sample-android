package com.rajaraman.androidsample.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.builder.Condition;
import com.raizlabs.android.dbflow.sql.builder.ConditionQueryBuilder;
import com.raizlabs.android.dbflow.sql.language.Delete;
import com.raizlabs.android.dbflow.sql.language.Select;
import com.raizlabs.android.dbflow.sql.language.Update;
import com.rajaraman.androidsample.R;
import com.rajaraman.androidsample.db.StudentDbManager;
import com.rajaraman.androidsample.db.model.Exam;
import com.rajaraman.androidsample.db.model.Student;
import com.rajaraman.androidsample.db.model.Student$Table;
import com.rajaraman.androidsample.utils.AppConstants;
import com.rajaraman.androidsample.utils.AppUtil;

import org.androidannotations.annotations.EActivity;

import java.util.List;

@EActivity(R.layout.activity_db)
public class DbActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AppUtil.deleteDb(AppConstants.STUDENTS_DB_NAME);
//        StudentDbManager.getInstance().createDbData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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
}