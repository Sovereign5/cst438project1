package com.example.cst438project1.model;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Assignment.class, Grade.class}, version =1)
public abstract class AppDatabase extends RoomDatabase {
    public static final String dbAssignment = "db-assignment";
    public static final String dbGrade = "db-grade";

    public static final String ASSIGNMENT_TABLE = "assignment";
    public static final String GRADE_TABLE = "grade";

    public abstract AssignmentDAO getAssignmentDAO();
    public abstract GradeDAO getGradeDAO();
}
