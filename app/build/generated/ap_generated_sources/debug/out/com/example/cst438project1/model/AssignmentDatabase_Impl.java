package com.example.cst438project1.model;

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
public final class AssignmentDatabase_Impl extends AssignmentDatabase {
  private volatile AssignmentDAO _assignmentDAO;

  private volatile GradeDAO _gradeDAO;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Assignment` (`assignmentID` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `details` TEXT NOT NULL, `maxScore` REAL NOT NULL, `earnedScore` REAL NOT NULL, `assignedDate` TEXT NOT NULL, `dueDate` TEXT NOT NULL, `categoryID` INTEGER NOT NULL, `courseID` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Grade` (`gradeID` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `score` INTEGER NOT NULL, `assignmentID` INTEGER NOT NULL, `studentID` INTEGER NOT NULL, `courseID` INTEGER NOT NULL, `dateEarned` TEXT NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"b3f47b1d38e75b4803636ee4c88aabb8\")");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `Assignment`");
        _db.execSQL("DROP TABLE IF EXISTS `Grade`");
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
        final HashMap<String, TableInfo.Column> _columnsAssignment = new HashMap<String, TableInfo.Column>(8);
        _columnsAssignment.put("assignmentID", new TableInfo.Column("assignmentID", "INTEGER", true, 1));
        _columnsAssignment.put("details", new TableInfo.Column("details", "TEXT", true, 0));
        _columnsAssignment.put("maxScore", new TableInfo.Column("maxScore", "REAL", true, 0));
        _columnsAssignment.put("earnedScore", new TableInfo.Column("earnedScore", "REAL", true, 0));
        _columnsAssignment.put("assignedDate", new TableInfo.Column("assignedDate", "TEXT", true, 0));
        _columnsAssignment.put("dueDate", new TableInfo.Column("dueDate", "TEXT", true, 0));
        _columnsAssignment.put("categoryID", new TableInfo.Column("categoryID", "INTEGER", true, 0));
        _columnsAssignment.put("courseID", new TableInfo.Column("courseID", "INTEGER", true, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysAssignment = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesAssignment = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoAssignment = new TableInfo("Assignment", _columnsAssignment, _foreignKeysAssignment, _indicesAssignment);
        final TableInfo _existingAssignment = TableInfo.read(_db, "Assignment");
        if (! _infoAssignment.equals(_existingAssignment)) {
          throw new IllegalStateException("Migration didn't properly handle Assignment(com.example.cst438project1.model.Assignment).\n"
                  + " Expected:\n" + _infoAssignment + "\n"
                  + " Found:\n" + _existingAssignment);
        }
        final HashMap<String, TableInfo.Column> _columnsGrade = new HashMap<String, TableInfo.Column>(6);
        _columnsGrade.put("gradeID", new TableInfo.Column("gradeID", "INTEGER", true, 1));
        _columnsGrade.put("score", new TableInfo.Column("score", "INTEGER", true, 0));
        _columnsGrade.put("assignmentID", new TableInfo.Column("assignmentID", "INTEGER", true, 0));
        _columnsGrade.put("studentID", new TableInfo.Column("studentID", "INTEGER", true, 0));
        _columnsGrade.put("courseID", new TableInfo.Column("courseID", "INTEGER", true, 0));
        _columnsGrade.put("dateEarned", new TableInfo.Column("dateEarned", "TEXT", true, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysGrade = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesGrade = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoGrade = new TableInfo("Grade", _columnsGrade, _foreignKeysGrade, _indicesGrade);
        final TableInfo _existingGrade = TableInfo.read(_db, "Grade");
        if (! _infoGrade.equals(_existingGrade)) {
          throw new IllegalStateException("Migration didn't properly handle Grade(com.example.cst438project1.model.Grade).\n"
                  + " Expected:\n" + _infoGrade + "\n"
                  + " Found:\n" + _existingGrade);
        }
      }
    }, "b3f47b1d38e75b4803636ee4c88aabb8", "c411ceed6a6172cadb86b0f52193a7a4");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    return new InvalidationTracker(this, "Assignment","Grade");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `Assignment`");
      _db.execSQL("DELETE FROM `Grade`");
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
  public AssignmentDAO getAssignmentDAO() {
    if (_assignmentDAO != null) {
      return _assignmentDAO;
    } else {
      synchronized(this) {
        if(_assignmentDAO == null) {
          _assignmentDAO = new AssignmentDAO_Impl(this);
        }
        return _assignmentDAO;
      }
    }
  }

  @Override
  public GradeDAO getGradeDAO() {
    if (_gradeDAO != null) {
      return _gradeDAO;
    } else {
      synchronized(this) {
        if(_gradeDAO == null) {
          _gradeDAO = new GradeDAO_Impl(this);
        }
        return _gradeDAO;
      }
    }
  }
}
