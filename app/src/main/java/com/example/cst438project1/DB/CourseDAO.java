package com.example.cst438project1.DB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import com.example.cst438project1.DB.CourseLog;

@Dao
public interface CourseDAO {

    @Insert
    void insert(CourseDAO... courseDAOS);

    @Update
    void update(CourseDAO... courseDAOS);

    @Delete
    void delete(CourseDAO courseDAO);

    @Query("SELECT * FROM " + CourseDatabase.COURSELOG_TABLE)
    List<CourseLog> getGymLogs();

    @Query("SELECT * FROM " + CourseDatabase.COURSELOG_TABLE + " WHERE mLogId = :logID")
    CourseLog getQuestionWithId(int logID);
}
