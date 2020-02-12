package com.example.cst438project1.DB;

import androidx.room.RoomDatabase;
import androidx.room.Database;

import com.example.cst438project1.DB.CourseLog;

@Database(entities = {CourseLog.class}, version = 1)
public abstract class CourseDatabase extends RoomDatabase {
    public static final String dbName = "db-courselog"; // Defines dbName
    public static final String COURSELOG_TABLE = "courselog"; // Defines COURSE_TABLE

    public abstract CourseDAO getCourseLogDAO();
}
