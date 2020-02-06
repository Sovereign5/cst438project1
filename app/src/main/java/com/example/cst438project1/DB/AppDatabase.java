package com.example.cst438project1.DB;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.cst438project1.AccountLog;

@Database(entities = {AccountLog.class}, version=1)
public abstract class AppDatabase extends RoomDatabase {

    public static final String dbName ="db-accountlog";

    public static final String ACCOUNTLOG_TABLE = "accountlog";

    public abstract AccountLogDAO getAccountLogDAO();

}
