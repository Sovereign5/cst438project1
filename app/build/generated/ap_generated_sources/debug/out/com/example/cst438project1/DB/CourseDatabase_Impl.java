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
public final class CourseDatabase_Impl extends CourseDatabase {
  private volatile CourseDAO _courseDAO;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `courselog` (`mCourseID` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `mInstructor` TEXT, `mTitle` TEXT, `mDescription` TEXT, `mStartDate` TEXT, `mEndDate` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"390d35edc2e07a22b5bbc76a5888dbd6\")");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `courselog`");
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
        final HashMap<String, TableInfo.Column> _columnsCourselog = new HashMap<String, TableInfo.Column>(6);
        _columnsCourselog.put("mCourseID", new TableInfo.Column("mCourseID", "INTEGER", true, 1));
        _columnsCourselog.put("mInstructor", new TableInfo.Column("mInstructor", "TEXT", false, 0));
        _columnsCourselog.put("mTitle", new TableInfo.Column("mTitle", "TEXT", false, 0));
        _columnsCourselog.put("mDescription", new TableInfo.Column("mDescription", "TEXT", false, 0));
        _columnsCourselog.put("mStartDate", new TableInfo.Column("mStartDate", "TEXT", false, 0));
        _columnsCourselog.put("mEndDate", new TableInfo.Column("mEndDate", "TEXT", false, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysCourselog = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesCourselog = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoCourselog = new TableInfo("courselog", _columnsCourselog, _foreignKeysCourselog, _indicesCourselog);
        final TableInfo _existingCourselog = TableInfo.read(_db, "courselog");
        if (! _infoCourselog.equals(_existingCourselog)) {
          throw new IllegalStateException("Migration didn't properly handle courselog(com.example.cst438project1.DB.CourseLog).\n"
                  + " Expected:\n" + _infoCourselog + "\n"
                  + " Found:\n" + _existingCourselog);
        }
      }
    }, "390d35edc2e07a22b5bbc76a5888dbd6", "b1f1db8b5012db4428c1e846908caf8d");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    return new InvalidationTracker(this, "courselog");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `courselog`");
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
  public CourseDAO getCourseLogDAO() {
    if (_courseDAO != null) {
      return _courseDAO;
    } else {
      synchronized(this) {
        if(_courseDAO == null) {
          _courseDAO = new CourseDAO_Impl(this);
        }
        return _courseDAO;
      }
    }
  }
}
