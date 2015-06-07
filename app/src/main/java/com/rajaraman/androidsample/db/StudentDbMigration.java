package com.rajaraman.androidsample.db;

import android.database.sqlite.SQLiteDatabase;

import com.raizlabs.android.dbflow.annotation.Migration;
import com.raizlabs.android.dbflow.sql.migration.BaseMigration;
import com.rajaraman.androidsample.utils.AppConstants;

// This class is needed when you need to migrate after introducing a new table. Let this class
// be there for DB migration, whenever a new table is added and version incremented, this would take
// care of adding the new table into the migrated db
@Migration(version = AppConstants.STUDENTS_DB_VERSION, databaseName = AppConstants.STUDENTS_DB_NAME)
public class StudentDbMigration extends BaseMigration {

    @Override
    public void migrate(SQLiteDatabase database) {
    }
}