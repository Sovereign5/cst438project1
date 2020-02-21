package com.example.cst438project1.model;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.List;

@Database(entities = {Assignment.class, Grade.class}, version = 1)
public abstract class AssignmentDatabase extends RoomDatabase {
    public static final String dbAssignment = "db-assignment";
    public static final String dbGrade = "db-grade";

    public static final String ASSIGNMENT_TABLE = "assignment";
    public static final String GRADE_TABLE = "grade";

    public abstract AssignmentDAO getAssignmentDAO();

    public abstract GradeDAO getGradeDAO();

    private static AssignmentDatabase instance;

    public static AssignmentDatabase getAppDatabase(Context context) {
        if (instance == null) {
            instance =
                    Room.databaseBuilder(context.getApplicationContext(),
                            AssignmentDatabase.class, "AppDB")
                            .allowMainThreadQueries()
                            .build();
        }
        return instance;
    }

    public void loadData(Context context) {
        List<Assignment> assignmentList = AssignmentDatabase.getAppDatabase(context).getAssignmentDAO().getAllAssignments();
        List<Grade> gradeList = AssignmentDatabase.getAppDatabase(context).getGradeDAO().getAllGrades();

        if (assignmentList.size() == 0) {
            loadAssignments(context);
        }

        if (gradeList.size() == 0) {
            loadGrades(context);
        }
    }

    private void loadAssignments(Context context) {
        AssignmentDAO dao = getAppDatabase(context).getAssignmentDAO();

        Assignment hw1 = new Assignment("Homework 1", 100, 80, "2/20/2020",
                "3/20/2020", 1, 1);
        Assignment hw2 = new Assignment("Homework 2", 100, 60, "3/25/2020",
                "4/05/2020", 2,  2);
        Assignment project1 = new Assignment("Homework 3", 100, 70, "4/20/2020",
                "5/08/2020", 3,  3);

        dao.addAssignment(hw1);
        dao.addAssignment(hw2);
        dao.addAssignment(project1);
    }

    private void loadGrades(Context context) {
        GradeDAO dao = getAppDatabase(context).getGradeDAO();

        Grade grade1 = new Grade('A', 1,  1, 1,
        "3/21/2020");
        Grade grade2 = new Grade('B', 2,  2, 2,
                "4/06/2020");
        Grade grade3 = new Grade('C', 3,  3, 3,
                "5/09/2020");

        dao.addGrade(grade1);
        dao.addGrade(grade2);
        dao.addGrade(grade3);
    }



}

