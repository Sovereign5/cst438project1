package com.example.cst438project1;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.cst438project1.DB.AppDatabase;
import com.example.cst438project1.DB.ArrayListTypeConverterAccounts;
import com.example.cst438project1.DB.ArrayListTypeConverterAssignments;
import com.example.cst438project1.DB.CourseLog;
import com.example.cst438project1.model.Assignment;

import java.util.ArrayList;
import java.util.List;

@Entity(tableName = AppDatabase.ACCOUNTLOG_TABLE)
public class AccountLog {

    @PrimaryKey(autoGenerate = true)
    private int mAccountId;

    //User objects

    @ColumnInfo(name = "username")
    private String username;

    @ColumnInfo(name = "password")
    private String password;

    @ColumnInfo(name = "first_name")
    private String firstname;

    @ColumnInfo(name = "last_name")
    private String lastname;

    @ColumnInfo(name = "courses")
    @TypeConverters(ArrayListTypeConverterAccounts.class)
    private List<CourseLog> userCourses;

    @TypeConverters(ArrayListTypeConverterAssignments.class)
    private List<Assignment> userAssignments;


    public AccountLog(String firstname, String lastname, String username, String password){


        this.username = username;
        this.password = password;
        this.firstname = firstname;
        this.lastname = lastname;

        userCourses = new ArrayList<>();
        userAssignments = new ArrayList<>();

    }


    public int getAccountId() {
        return mAccountId;
    }



    public void setAccountId(int mAccountId) {
        this.mAccountId = mAccountId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public List<CourseLog> getUserCourses() {
        return userCourses;
    }

    public void setUserCourses(List<CourseLog> userCourses) {
        this.userCourses = userCourses;
    }

    public void insertCourse(CourseLog newCourse) {
        userCourses.add(newCourse);
    }

    public List<Assignment> getUserAssignments() {
        return userAssignments;
    }

    public void setUserAssignments(List<Assignment> userAssignments) {
        this.userAssignments = userAssignments;
    }

    public void insertAssignment(Assignment newAssignment) {
        userAssignments.add(newAssignment);
    }
}
