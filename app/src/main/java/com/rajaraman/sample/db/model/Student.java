package com.rajaraman.sample.db.model;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ForeignKey;
import com.raizlabs.android.dbflow.annotation.ForeignKeyReference;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;
import com.rajaraman.sample.utils.AppConstants;

@Table(databaseName = AppConstants.STUDENTS_DB_NAME)
public class Student extends BaseModel {
    @Column
    @PrimaryKey
    public long id;

    @Column
    public String name;

    @Column
    public int age;

    // A student is mapped to only exam (one to one) - I have not figured out
    // yet how to retrieve the foreign key values
    @Column
    @ForeignKey(
        references = {@ForeignKeyReference(columnName = "exam_id",
                    columnType = Long.class,
                    foreignColumnName = "id")},
        saveForeignKeyModel = false)
    public Exam exam;

    @Column
    public double cgpa;

    // Added in VERSION = 2
    @Column
    public String nickName;

    // Added in VERSION = 3
    @Column
    public String fullName;
}