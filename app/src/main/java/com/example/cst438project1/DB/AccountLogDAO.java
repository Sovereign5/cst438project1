package com.example.cst438project1.DB;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

import com.example.cst438project1.AccountLog;

@Dao
public interface AccountLogDAO {
    @Insert
    void insert(AccountLog... accountLogs);

    @Update
    void update(AccountLog... accountLogs);

    @Delete
    void delete(AccountLog... accountLogs);

}
