package com.rajaraman.androidsample.db;

import android.database.sqlite.SQLiteDatabase;

import com.raizlabs.android.dbflow.annotation.Migration;
import com.raizlabs.android.dbflow.sql.migration.BaseMigration;

@Migration(version = StudentDb.VERSION, databaseName = StudentDb.NAME)
public class StudentDbMigration extends BaseMigration {

    @Override
    public void migrate(SQLiteDatabase database) {
    }
}