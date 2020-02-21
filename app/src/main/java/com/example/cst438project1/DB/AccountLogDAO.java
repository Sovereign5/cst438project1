package com.example.cst438project1.DB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.cst438project1.AccountLog;

import java.util.List;

@Dao
public interface AccountLogDAO {
    @Insert
    void insert(AccountLog... accountLogs);

    @Update
    void update(AccountLog... accountLogs);

    @Delete
    void delete(AccountLog... accountLogs);

    @Query("SELECT * FROM " + AppDatabase.ACCOUNTLOG_TABLE)
    List<AccountLog> getAccountLog();

    @Query("SELECT * FROM " + AppDatabase.ACCOUNTLOG_TABLE + " WHERE mAccountID = :accountID")
    AccountLog getAccountWithId(int accountID);

    @Query("SELECT * FROM " + AppDatabase.ACCOUNTLOG_TABLE + " WHERE username LIKE :user AND " + " password LIKE :pass LIMIT 1")
    boolean findCredentials(String user, String pass);

    @Query("SELECT * FROM " + AppDatabase.ACCOUNTLOG_TABLE + " WHERE username = :user AND " + " password = :pass LIMIT 1")
    AccountLog findAccount(String user, String pass);


}
