package com.example.cst438project1.DB;

import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import java.lang.IllegalStateException;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.HashMap;
import java.util.HashSet;

@SuppressWarnings("unchecked")
public final class AppDatabase_Impl extends AppDatabase {
  private volatile AccountLogDAO _accountLogDAO;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `accountlog` (`mAccountId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `username` TEXT, `password` TEXT, `first_name` TEXT, `last_name` TEXT, `courses` TEXT, `userAssignments` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"2648b03f7ad1faeb615c1c9c97193f64\")");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `accountlog`");
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      protected void validateMigration(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsAccountlog = new HashMap<String, TableInfo.Column>(7);
        _columnsAccountlog.put("mAccountId", new TableInfo.Column("mAccountId", "INTEGER", true, 1));
        _columnsAccountlog.put("username", new TableInfo.Column("username", "TEXT", false, 0));
        _columnsAccountlog.put("password", new TableInfo.Column("password", "TEXT", false, 0));
        _columnsAccountlog.put("first_name", new TableInfo.Column("first_name", "TEXT", false, 0));
        _columnsAccountlog.put("last_name", new TableInfo.Column("last_name", "TEXT", false, 0));
        _columnsAccountlog.put("courses", new TableInfo.Column("courses", "TEXT", false, 0));
        _columnsAccountlog.put("userAssignments", new TableInfo.Column("userAssignments", "TEXT", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysAccountlog = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesAccountlog = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoAccountlog = new TableInfo("accountlog", _columnsAccountlog, _foreignKeysAccountlog, _indicesAccountlog);
        final TableInfo _existingAccountlog = TableInfo.read(_db, "accountlog");
        if (! _infoAccountlog.equals(_existingAccountlog)) {
          throw new IllegalStateException("Migration didn't properly handle accountlog(com.example.cst438project1.AccountLog).\n"
                  + " Expected:\n" + _infoAccountlog + "\n"
                  + " Found:\n" + _existingAccountlog);
        }
      }
    }, "2648b03f7ad1faeb615c1c9c97193f64", "51842b07d03f415080867152cc0817c2");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    return new InvalidationTracker(this, "accountlog");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `accountlog`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  public AccountLogDAO getAccountLogDAO() {
    if (_accountLogDAO != null) {
      return _accountLogDAO;
    } else {
      synchronized(this) {
        if(_accountLogDAO == null) {
          _accountLogDAO = new AccountLogDAO_Impl(this);
        }
        return _accountLogDAO;
      }
    }
  }
}
