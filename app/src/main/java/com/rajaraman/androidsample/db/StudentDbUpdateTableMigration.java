package com.rajaraman.androidsample.db;

import com.noveogroup.android.log.Log;
import com.raizlabs.android.dbflow.annotation.Migration;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.builder.Condition;
import com.raizlabs.android.dbflow.sql.builder.ConditionQueryBuilder;
import com.raizlabs.android.dbflow.sql.language.Update;
import com.raizlabs.android.dbflow.sql.migration.UpdateTableMigration;
import com.rajaraman.androidsample.db.model.Student;
import com.rajaraman.androidsample.db.model.Student$Table;

// Use this migration for updating existing table
@Migration(version = StudentDb.VERSION, databaseName = StudentDb.NAME)
public class StudentDbUpdateTableMigration extends UpdateTableMigration<Student> {

    // You need to have
    public StudentDbUpdateTableMigration() {
        super(Student.class);
    }

    @Override
    public void onPreMigrate() {
        super.onPreMigrate();

        int dbVersion = FlowManager.getDatabase(StudentDb.NAME).getDatabaseVersion();

        switch (dbVersion) {

            case 4: {
                updateStudentTable();
                break;
            }

            default: {
                Log.d("Nothing to do !!!");
                break;
            }
        }
    }

    private void updateStudentTable() {

        // Warning:
        // DO NOT create an instance of Update class inside UpdateTableMigration, as this will try to call
        // getDatabase recursively and we will end up in illegal state exception similar to the one
        // explained here - http://stackoverflow.com/questions/15955799/getdatabase-called-recursively
        // Creating an instance of Update class outside this class is absolutely fine. For reference
        // check updateStudentDbData() of DbActivity
//
//        ConditionQueryBuilder<Student> whereConditionQueryBuilder = new ConditionQueryBuilder<>(Student.class,
//                Condition.column(Student$Table.NICKNAME).is("NickName1"))
//                .or(Condition.column(Student$Table.NICKNAME).is("NickName2"));
//
//        // UPDATE Student SET nickName = "RAM" WHERE nickName = "NickName1" OR nickName = "NickName2"
//        new Update(Student.class).set(
//                Condition.column(Student$Table.NICKNAME).is("Ram"))
//                .where(whereConditionQueryBuilder);

        // Just call set method of this class which does the same thing
        set(Condition.column(Student$Table.NICKNAME).is("Ram")).where(
                    Condition.column(Student$Table.NICKNAME).is("NickName1"),
                    Condition.column(Student$Table.NICKNAME).is("NickName2"));

        int i = 0;
        i = 6;
    }
}

//public class StudentDbUpdateTableMigration {
//}