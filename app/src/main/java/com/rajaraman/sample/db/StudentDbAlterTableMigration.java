package com.rajaraman.sample.db;

import com.noveogroup.android.log.Log;
import com.raizlabs.android.dbflow.annotation.Migration;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.migration.AlterTableMigration;
import com.rajaraman.sample.db.model.Student;
import com.rajaraman.sample.utils.AppConstants;

// Use this migration for altering the existing table (only add column is allowed)
// https://www.sqlite.org/omitted.html
@Migration(version = AppConstants.STUDENTS_DB_VERSION, databaseName = AppConstants.STUDENTS_DB_NAME)
public class StudentDbAlterTableMigration extends AlterTableMigration<Student> {

    // You need to have
    public StudentDbAlterTableMigration() {
        super(Student.class);
    }

    @Override
    public void onPreMigrate() {
        super.onPreMigrate();

        int dbVersion = FlowManager.getDatabase(AppConstants.STUDENTS_DB_NAME).getDatabaseVersion();

        switch (dbVersion) {

            case 2: {
                addColumn(String.class, "nickName");
                break;
            }

            case 3: {
                addColumn(String.class, "fullName");
                break;
            }

            default: {
                Log.d("Nothing to do !!!");
                break;
            }
        }
    }
}

//// This class is for testing db migration. Uncomment and comment the above definition
//// when you do not migration to happen while testing.
//public class StudentDbAlterTableMigration {
//}