package com.example.cst438project1.DB;


import com.example.cst438project1.GradeCategory;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {GradeCategory.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public static final String dbname = "db-gradeCat";
    public static final String GRADE_CATEGORY_TABLE = "grade_category_table";

    public abstract GradeCategoryDAO getGradeCategoryDao();
}
