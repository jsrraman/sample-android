package com.rajaraman.androidsample.db.model;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ForeignKey;
import com.raizlabs.android.dbflow.annotation.ForeignKeyReference;
import com.raizlabs.android.dbflow.annotation.ModelContainer;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.sql.builder.Condition;
import com.raizlabs.android.dbflow.sql.language.Select;
import com.raizlabs.android.dbflow.structure.BaseModel;
import com.raizlabs.android.dbflow.structure.container.ForeignKeyContainer;
import com.rajaraman.androidsample.utils.AppConstants;

import java.util.List;

@ModelContainer
@Table(databaseName = AppConstants.STUDENTS_DB_NAME)
public class Exam extends BaseModel {
    @Column
    @PrimaryKey
    public long id;

    @Column
    public String type;

    // An exam can have multiple students (one to many) - I have not figured out
    // yet how to retrieve the foreign key values
    @Column
    @ForeignKey(
            references = {@ForeignKeyReference(columnName = "student_id",
                    columnType = Long.class,
                    foreignColumnName = "id")},
            saveForeignKeyModel = false)
    ForeignKeyContainer<Student> student;

    // Not sure how this works - right now it is a placeholder
    public Student getStudent() {
        return student.toModel();
    }

    private List<Student> studentList;

    public List<Student> getStudentList() {
        if (studentList == null) {
            studentList = new Select(Student$Table.EXAM_EXAM_ID)
                            .from(Student.class)
                            .where(Condition.column(Student$Table.EXAM_EXAM_ID).is(id))
                            .queryList();
        }

        return studentList;
    }
}