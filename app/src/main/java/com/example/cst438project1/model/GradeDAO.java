package com.example.cst438project1.model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

@Dao
public interface GradeDAO {

    @Insert
    void addGrade(Grade grade);

    @Update
    void updateGrade(Grade grade);

    @Delete
    void deleteGrade(Grade grade);

}
