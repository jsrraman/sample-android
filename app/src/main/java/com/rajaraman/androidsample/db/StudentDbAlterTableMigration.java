package com.rajaraman.androidsample.db;

import com.noveogroup.android.log.Log;
import com.raizlabs.android.dbflow.annotation.Migration;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.builder.Condition;
import com.raizlabs.android.dbflow.sql.language.Update;
import com.raizlabs.android.dbflow.sql.migration.AlterTableMigration;
import com.rajaraman.androidsample.db.model.Student;
import com.rajaraman.androidsample.db.model.Student$Table;

// Use this migration for altering the existing table (only add column is allowed)
// https://www.sqlite.org/omitted.html
@Migration(version = StudentDb.VERSION, databaseName = StudentDb.NAME)
public class StudentDbAlterTableMigration extends AlterTableMigration<Student> {

    // You need to have
    public StudentDbAlterTableMigration() {
        super(Student.class);
    }

    @Override
    public void onPreMigrate() {
        super.onPreMigrate();

        int dbVersion = FlowManager.getDatabase(StudentDb.NAME).getDatabaseVersion();

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

//public class StudentDbAlterTableMigration {
//}