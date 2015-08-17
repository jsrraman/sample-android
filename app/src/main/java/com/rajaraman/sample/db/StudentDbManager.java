package com.rajaraman.sample.db;

import com.raizlabs.android.dbflow.annotation.Database;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.builder.Condition;
import com.raizlabs.android.dbflow.sql.builder.ConditionQueryBuilder;
import com.raizlabs.android.dbflow.sql.language.Delete;
import com.raizlabs.android.dbflow.sql.language.Select;
import com.raizlabs.android.dbflow.sql.language.Update;
import com.rajaraman.sample.db.model.Exam;
import com.rajaraman.sample.db.model.Student;
import com.rajaraman.sample.db.model.Student$Table;
import com.rajaraman.sample.utils.AppConstants;

import java.util.List;

@Database(name = AppConstants.STUDENTS_DB_NAME, version = AppConstants.STUDENTS_DB_VERSION)
public class StudentDbManager {
    // This is early initialization as this does not have lot of resources to load
    // For lazy initialization, prefer private static class to load the instance as mentioned in
    // http://stackoverflow.com/questions/70689/what-is-an-efficient-way-to-implement-a-singleton-pattern-in-java
    private static final StudentDbManager dbManager = new StudentDbManager();

    private StudentDbManager() {
    }

    public static StudentDbManager getInstance() {
        return dbManager;
    }

    public void createDbData() {

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

    public void clearDbData() {
        Delete.tables(Student.class, Exam.class);
    }

    public void updateStudentDbData() {

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
        int dbVersion = FlowManager.getDatabase(AppConstants.STUDENTS_DB_NAME).getDatabaseVersion();
        return dbVersion;
    }
}