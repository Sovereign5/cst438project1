package com.example.cst438project1.model;


import com.example.cst438project1.AccountLog;
import com.example.cst438project1.DB.AppDatabase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface AssignmentDAO {
    @Query("select * from " + AssignmentDatabase.ASSIGNMENT_TABLE)
    List<Assignment> getAllAssignments();

    @Update
    void updateAssignment(Assignment assignments);

    @Insert
    void addAssignment(Assignment... assignments);

    @Delete
    void deleteAssignment(Assignment assignment);

    @Query("SELECT * FROM " + AssignmentDatabase.ASSIGNMENT_TABLE + " WHERE assignmentID = :assignID ")
    Assignment findAssignment(int assignID);

}
