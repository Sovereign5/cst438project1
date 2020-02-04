package com.example.cst438project1.DB;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.cst438project1.DB.CourseDatabase;

import java.util.Objects;

@Entity(tableName = CourseDatabase.COURSELOG_TABLE)
public class CourseLog {
    @PrimaryKey(autoGenerate = true)
    private int mLogId;


}
