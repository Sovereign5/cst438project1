package com.example.cst438project1.model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface AssignmentDAO {

    @Query("select * from " + AppDatabase.ASSIGNMENT_TABLE + " where " +
            "assignmentID = :assignmentID and categoryID = :categoryID")
    Assignment getFromCourse(int assignmentID, int categoryID);

    @Query("select * from " + AppDatabase.ASSIGNMENT_TABLE)
    List<Assignment> getAllAssignments();

    @Update
    void updateAssignment(Assignment assignment);

    @Insert
    void addAssignment(Assignment assignment);

    @Delete
    void deleteAssignment(Assignment assignment);

}
