package com.rajaraman.androidsample.db;

import com.raizlabs.android.dbflow.annotation.Database;

@Database(name = StudentDb.NAME, version = StudentDb.VERSION)
public class StudentDb {
    public static final String NAME = "Students";
    public static final int VERSION = 4;
}