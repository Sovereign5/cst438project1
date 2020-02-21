package com.example.cst438project1.DB;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public final class CourseDAO_Impl implements CourseDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfCourseLog;

  private final EntityDeletionOrUpdateAdapter __deletionAdapterOfCourseLog;

  private final EntityDeletionOrUpdateAdapter __updateAdapterOfCourseLog;

  public CourseDAO_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfCourseLog = new EntityInsertionAdapter<CourseLog>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `courselog`(`mCourseID`,`mInstructor`,`mTitle`,`mDescription`,`mStartDate`,`mEndDate`) VALUES (nullif(?, 0),?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, CourseLog value) {
        stmt.bindLong(1, value.getCourseID());
        if (value.getInstructor() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getInstructor());
        }
        if (value.getTitle() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getTitle());
        }
        if (value.getDescription() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getDescription());
        }
        if (value.getStartDate() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getStartDate());
        }
        if (value.getEndDate() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getEndDate());
        }
      }
    };
    this.__deletionAdapterOfCourseLog = new EntityDeletionOrUpdateAdapter<CourseLog>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `courselog` WHERE `mCourseID` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, CourseLog value) {
        stmt.bindLong(1, value.getCourseID());
      }
    };
    this.__updateAdapterOfCourseLog = new EntityDeletionOrUpdateAdapter<CourseLog>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `courselog` SET `mCourseID` = ?,`mInstructor` = ?,`mTitle` = ?,`mDescription` = ?,`mStartDate` = ?,`mEndDate` = ? WHERE `mCourseID` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, CourseLog value) {
        stmt.bindLong(1, value.getCourseID());
        if (value.getInstructor() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getInstructor());
        }
        if (value.getTitle() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getTitle());
        }
        if (value.getDescription() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getDescription());
        }
        if (value.getStartDate() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getStartDate());
        }
        if (value.getEndDate() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getEndDate());
        }
        stmt.bindLong(7, value.getCourseID());
      }
    };
  }

  @Override
  public void insert(CourseLog... courseLogs) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfCourseLog.insert(courseLogs);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(CourseLog courseLog) {
    __db.beginTransaction();
    try {
      __deletionAdapterOfCourseLog.handle(courseLog);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(CourseLog... courseLogs) {
    __db.beginTransaction();
    try {
      __updateAdapterOfCourseLog.handleMultiple(courseLogs);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<CourseLog> getCourseLogs() {
    final String _sql = "SELECT * FROM courselog";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfMCourseID = _cursor.getColumnIndexOrThrow("mCourseID");
      final int _cursorIndexOfMInstructor = _cursor.getColumnIndexOrThrow("mInstructor");
      final int _cursorIndexOfMTitle = _cursor.getColumnIndexOrThrow("mTitle");
      final int _cursorIndexOfMDescription = _cursor.getColumnIndexOrThrow("mDescription");
      final int _cursorIndexOfMStartDate = _cursor.getColumnIndexOrThrow("mStartDate");
      final int _cursorIndexOfMEndDate = _cursor.getColumnIndexOrThrow("mEndDate");
      final List<CourseLog> _result = new ArrayList<CourseLog>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final CourseLog _item;
        final String _tmpMInstructor;
        _tmpMInstructor = _cursor.getString(_cursorIndexOfMInstructor);
        final String _tmpMTitle;
        _tmpMTitle = _cursor.getString(_cursorIndexOfMTitle);
        final String _tmpMDescription;
        _tmpMDescription = _cursor.getString(_cursorIndexOfMDescription);
        final String _tmpMStartDate;
        _tmpMStartDate = _cursor.getString(_cursorIndexOfMStartDate);
        final String _tmpMEndDate;
        _tmpMEndDate = _cursor.getString(_cursorIndexOfMEndDate);
        _item = new CourseLog(_tmpMInstructor,_tmpMTitle,_tmpMDescription,_tmpMStartDate,_tmpMEndDate);
        final int _tmpMCourseID;
        _tmpMCourseID = _cursor.getInt(_cursorIndexOfMCourseID);
        _item.setCourseID(_tmpMCourseID);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public CourseLog getCourseWithId(int logID) {
    final String _sql = "SELECT * FROM courselog WHERE mCourseID = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, logID);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfMCourseID = _cursor.getColumnIndexOrThrow("mCourseID");
      final int _cursorIndexOfMInstructor = _cursor.getColumnIndexOrThrow("mInstructor");
      final int _cursorIndexOfMTitle = _cursor.getColumnIndexOrThrow("mTitle");
      final int _cursorIndexOfMDescription = _cursor.getColumnIndexOrThrow("mDescription");
      final int _cursorIndexOfMStartDate = _cursor.getColumnIndexOrThrow("mStartDate");
      final int _cursorIndexOfMEndDate = _cursor.getColumnIndexOrThrow("mEndDate");
      final CourseLog _result;
      if(_cursor.moveToFirst()) {
        final String _tmpMInstructor;
        _tmpMInstructor = _cursor.getString(_cursorIndexOfMInstructor);
        final String _tmpMTitle;
        _tmpMTitle = _cursor.getString(_cursorIndexOfMTitle);
        final String _tmpMDescription;
        _tmpMDescription = _cursor.getString(_cursorIndexOfMDescription);
        final String _tmpMStartDate;
        _tmpMStartDate = _cursor.getString(_cursorIndexOfMStartDate);
        final String _tmpMEndDate;
        _tmpMEndDate = _cursor.getString(_cursorIndexOfMEndDate);
        _result = new CourseLog(_tmpMInstructor,_tmpMTitle,_tmpMDescription,_tmpMStartDate,_tmpMEndDate);
        final int _tmpMCourseID;
        _tmpMCourseID = _cursor.getInt(_cursorIndexOfMCourseID);
        _result.setCourseID(_tmpMCourseID);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public CourseLog getCourseTitle(String title) {
    final String _sql = "SELECT * FROM courselog WHERE mTitle = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (title == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, title);
    }
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfMCourseID = _cursor.getColumnIndexOrThrow("mCourseID");
      final int _cursorIndexOfMInstructor = _cursor.getColumnIndexOrThrow("mInstructor");
      final int _cursorIndexOfMTitle = _cursor.getColumnIndexOrThrow("mTitle");
      final int _cursorIndexOfMDescription = _cursor.getColumnIndexOrThrow("mDescription");
      final int _cursorIndexOfMStartDate = _cursor.getColumnIndexOrThrow("mStartDate");
      final int _cursorIndexOfMEndDate = _cursor.getColumnIndexOrThrow("mEndDate");
      final CourseLog _result;
      if(_cursor.moveToFirst()) {
        final String _tmpMInstructor;
        _tmpMInstructor = _cursor.getString(_cursorIndexOfMInstructor);
        final String _tmpMTitle;
        _tmpMTitle = _cursor.getString(_cursorIndexOfMTitle);
        final String _tmpMDescription;
        _tmpMDescription = _cursor.getString(_cursorIndexOfMDescription);
        final String _tmpMStartDate;
        _tmpMStartDate = _cursor.getString(_cursorIndexOfMStartDate);
        final String _tmpMEndDate;
        _tmpMEndDate = _cursor.getString(_cursorIndexOfMEndDate);
        _result = new CourseLog(_tmpMInstructor,_tmpMTitle,_tmpMDescription,_tmpMStartDate,_tmpMEndDate);
        final int _tmpMCourseID;
        _tmpMCourseID = _cursor.getInt(_cursorIndexOfMCourseID);
        _result.setCourseID(_tmpMCourseID);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
