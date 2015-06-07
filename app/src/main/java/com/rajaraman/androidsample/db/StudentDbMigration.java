package com.rajaraman.androidsample.db;

import android.database.sqlite.SQLiteDatabase;

import com.raizlabs.android.dbflow.annotation.Migration;
import com.raizlabs.android.dbflow.sql.migration.BaseMigration;
import com.rajaraman.androidsample.utils.AppConstants;

@Migration(version = AppConstants.STUDENTS_DB_VERSION, databaseName = AppConstants.STUDENTS_DB_NAME)
public class StudentDbMigration extends BaseMigration {

    @Override
    public void migrate(SQLiteDatabase database) {
    }
}