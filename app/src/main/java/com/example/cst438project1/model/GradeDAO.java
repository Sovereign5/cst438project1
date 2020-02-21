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
public interface GradeDAO {

    @Insert
    void addGrade(Grade grade);

    @Update
    void updateGrade(Grade grade);

    @Delete
    void deleteGrade(Grade grade);

    @Query("select * from " + AssignmentDatabase.GRADE_TABLE)
    List<Grade> getAllGrades();

    @Query("SELECT * FROM " + AssignmentDatabase.GRADE_TABLE + " WHERE courseID LIKE :courseID AND " + " studentID LIKE :studentID ")
    Grade findGrade(int courseID, int studentID);

}
