package com.rajaraman.sample.db.model;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;
import com.rajaraman.sample.utils.AppConstants;

@Table(databaseName = AppConstants.STUDENTS_DB_NAME)
public class StudentClass extends BaseModel {
    @Column
    @PrimaryKey
    public int id;

    @Column
    public String sectionName;

    @Column
    public String classType;
}

//// This class is for testing db migration. Uncomment and comment the above definition
//// when you do not want this model to be included in your db.
//public class StudentClass {
//}