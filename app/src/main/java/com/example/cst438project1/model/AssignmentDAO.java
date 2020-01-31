package com.example.cst438project1.model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface AssignmentDAO {

    @Query("select * from Assignment where " +
            "assignmentID = :assignmentID and categoryID = :categoryID")
    Assignment getFromCourse(int assignmentID, int categoryID);

    @Update
    void updateAssignment(Assignment assignment);

    @Insert
    void addAssignment(Assignment assignment);

    @Delete
    void deleteAssignment(Assignment assignment);

}
