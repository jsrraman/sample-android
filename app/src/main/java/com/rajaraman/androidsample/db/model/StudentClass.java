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
import com.rajaraman.androidsample.db.StudentDb;

import java.util.List;

@Table(databaseName = StudentDb.NAME)
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