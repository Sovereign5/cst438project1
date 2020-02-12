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
    void insert(CourseLog... courseLogs);

    @Update
    void update(CourseLog... courseLogs);

    @Delete
    void delete(CourseLog courseLog);

    @Query("SELECT * FROM " + CourseDatabase.COURSELOG_TABLE)
    List<CourseLog> getCourseLogs();

    @Query("SELECT * FROM " + CourseDatabase.COURSELOG_TABLE + " WHERE mCourseID = :logID")
    CourseLog getCourseWithId(int logID);

//    @Query("SELECT * FROM " + CourseDatabase.COURSELOG_TABLE + " WHERE mInstructor = :logID")
//    CourseLog getInstructorWithId(int logID);
}
