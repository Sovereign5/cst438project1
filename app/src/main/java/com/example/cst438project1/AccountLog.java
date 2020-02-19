package com.example.cst438project1;

import android.accounts.Account;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.cst438project1.DB.AppDatabase;

import java.util.ArrayList;

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
    private ArrayList usercourses;

    public AccountLog(String firstname, String lastname, String username, String password){

        username = username;
        password = password;
        firstname = firstname;
        lastname = lastname;

    }

}
