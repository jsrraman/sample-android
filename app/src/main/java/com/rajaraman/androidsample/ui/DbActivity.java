package com.rajaraman.androidsample.ui;

import android.app.Activity;
import android.database.Cursor;
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
import com.rajaraman.androidsample.db.StudentDb;
import com.rajaraman.androidsample.db.model.Exam;
import com.rajaraman.androidsample.db.model.Student;
import com.rajaraman.androidsample.db.model.Student$Table;
import com.rajaraman.androidsample.db.model.StudentClass;

import org.androidannotations.annotations.EActivity;

import java.util.List;

@EActivity(R.layout.activity_db)
public class DbActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        createDbData();
//
//        getDbVersion();

//        getStudentDbData();
//        getExamDbData();
//        getStudentClassData();

//        updateStudentDbData();
    }

    private void updateStudentDbData() {

        // *************
//        Building a conditional query
//        ConditionQueryBuilder<Student> queryBuilder = new ConditionQueryBuilder<>(Student.class,
//                Condition.column(Student$Table.NICKNAME).is("NickName1"));
//
//        String query = queryBuilder.getQuery();

        // *************

        // *************
        // Get the underlying query string like this
//        String query = new Update(Student.class).set(Condition.column(Student$Table.NICKNAME)
//                .is("Rajaraman"))
//                .where(Condition.column(Student$Table.NICKNAME).is("NickName1")).getQuery();
        // *************

        ConditionQueryBuilder<Student> whereConditionQueryBuilder = new ConditionQueryBuilder<>(Student.class,
                Condition.column(Student$Table.NICKNAME).is("NickName1"))
                .or(Condition.column(Student$Table.NICKNAME).is("NickName2"));

        // If count is 0, then update statement did not succeed
        long count = new Update(Student.class).set(
                Condition.column(Student$Table.NICKNAME).is("Ram"))
                .where(whereConditionQueryBuilder).count();

        getStudentDbData();
    }

    private void clearDbData() {
        Delete.tables(Student.class, Exam.class);
    }

    private void createDbData() {

//        clearDbData();

        for (int i = 0; i < 10; i++) {

            Student student = new Student();

            student.id = i;
            student.age = 19;
            student.name = "Name" + i;
            student.cgpa = 10.00;
//            student.nickName = "NickName" + i;
//            student.fullName = "FullName" + i;

            student.async().save();

            Exam exam = new Exam();

            exam.id = i;
            exam.type = "Mid term" + i;

            exam.async().save();

//            StudentClass studentClass = new StudentClass();
//            studentClass.id = "Class" + i;
//            studentClass.sectionName = "10 std";
//
//            studentClass.save();
        }
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

    public void getStudentDbData() {

        List<Student> studentList = new Select().from(Student.class).where().queryList();

        if (studentList != null) {
        }
    }

    public void getExamDbData() {

        List<Exam> examList = new Select().from(Exam.class).where().queryList();

        if (examList != null) {
            for (Exam exam : examList) {
                exam.getStudentList();

                int i = 0;
                i = 5;
            }
        }
    }

    public void getStudentClassData() {
//        List<StudentClass> studentClassList = new Select().from(StudentClass.class).where().queryList();
//
//        int i = 0;
    }

    public int getDbVersion() {
        int dbVersion = FlowManager.getDatabase(StudentDb.NAME).getDatabaseVersion();
        return dbVersion;
    }
}