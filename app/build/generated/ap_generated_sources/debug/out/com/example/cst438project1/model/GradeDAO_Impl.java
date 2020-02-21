package com.example.cst438project1.model;

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
public final class GradeDAO_Impl implements GradeDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfGrade;

  private final EntityDeletionOrUpdateAdapter __deletionAdapterOfGrade;

  private final EntityDeletionOrUpdateAdapter __updateAdapterOfGrade;

  public GradeDAO_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfGrade = new EntityInsertionAdapter<Grade>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `Grade`(`gradeID`,`score`,`assignmentID`,`studentID`,`courseID`,`dateEarned`) VALUES (nullif(?, 0),?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Grade value) {
        stmt.bindLong(1, value.getGradeID());
        stmt.bindLong(2, value.getScore());
        stmt.bindLong(3, value.getAssignmentID());
        stmt.bindLong(4, value.getStudentID());
        stmt.bindLong(5, value.getCourseID());
        if (value.getDateEarned() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getDateEarned());
        }
      }
    };
    this.__deletionAdapterOfGrade = new EntityDeletionOrUpdateAdapter<Grade>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `Grade` WHERE `gradeID` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Grade value) {
        stmt.bindLong(1, value.getGradeID());
      }
    };
    this.__updateAdapterOfGrade = new EntityDeletionOrUpdateAdapter<Grade>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `Grade` SET `gradeID` = ?,`score` = ?,`assignmentID` = ?,`studentID` = ?,`courseID` = ?,`dateEarned` = ? WHERE `gradeID` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Grade value) {
        stmt.bindLong(1, value.getGradeID());
        stmt.bindLong(2, value.getScore());
        stmt.bindLong(3, value.getAssignmentID());
        stmt.bindLong(4, value.getStudentID());
        stmt.bindLong(5, value.getCourseID());
        if (value.getDateEarned() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getDateEarned());
        }
        stmt.bindLong(7, value.getGradeID());
      }
    };
  }

  @Override
  public void addGrade(Grade grade) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfGrade.insert(grade);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteGrade(Grade grade) {
    __db.beginTransaction();
    try {
      __deletionAdapterOfGrade.handle(grade);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateGrade(Grade grade) {
    __db.beginTransaction();
    try {
      __updateAdapterOfGrade.handle(grade);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<Grade> getAllGrades() {
    final String _sql = "select * from grade";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfGradeID = _cursor.getColumnIndexOrThrow("gradeID");
      final int _cursorIndexOfScore = _cursor.getColumnIndexOrThrow("score");
      final int _cursorIndexOfAssignmentID = _cursor.getColumnIndexOrThrow("assignmentID");
      final int _cursorIndexOfStudentID = _cursor.getColumnIndexOrThrow("studentID");
      final int _cursorIndexOfCourseID = _cursor.getColumnIndexOrThrow("courseID");
      final int _cursorIndexOfDateEarned = _cursor.getColumnIndexOrThrow("dateEarned");
      final List<Grade> _result = new ArrayList<Grade>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Grade _item;
        _item = new Grade();
        final int _tmpGradeID;
        _tmpGradeID = _cursor.getInt(_cursorIndexOfGradeID);
        _item.setGradeID(_tmpGradeID);
        final char _tmpScore;
        _tmpScore = (char) _cursor.getInt(_cursorIndexOfScore);
        _item.setScore(_tmpScore);
        final int _tmpAssignmentID;
        _tmpAssignmentID = _cursor.getInt(_cursorIndexOfAssignmentID);
        _item.setAssignmentID(_tmpAssignmentID);
        final int _tmpStudentID;
        _tmpStudentID = _cursor.getInt(_cursorIndexOfStudentID);
        _item.setStudentID(_tmpStudentID);
        final int _tmpCourseID;
        _tmpCourseID = _cursor.getInt(_cursorIndexOfCourseID);
        _item.setCourseID(_tmpCourseID);
        final String _tmpDateEarned;
        _tmpDateEarned = _cursor.getString(_cursorIndexOfDateEarned);
        _item.setDateEarned(_tmpDateEarned);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Grade findGrade(int courseID, int studentID) {
    final String _sql = "SELECT * FROM grade WHERE courseID LIKE ? AND  studentID LIKE ? ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, courseID);
    _argIndex = 2;
    _statement.bindLong(_argIndex, studentID);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfGradeID = _cursor.getColumnIndexOrThrow("gradeID");
      final int _cursorIndexOfScore = _cursor.getColumnIndexOrThrow("score");
      final int _cursorIndexOfAssignmentID = _cursor.getColumnIndexOrThrow("assignmentID");
      final int _cursorIndexOfStudentID = _cursor.getColumnIndexOrThrow("studentID");
      final int _cursorIndexOfCourseID = _cursor.getColumnIndexOrThrow("courseID");
      final int _cursorIndexOfDateEarned = _cursor.getColumnIndexOrThrow("dateEarned");
      final Grade _result;
      if(_cursor.moveToFirst()) {
        _result = new Grade();
        final int _tmpGradeID;
        _tmpGradeID = _cursor.getInt(_cursorIndexOfGradeID);
        _result.setGradeID(_tmpGradeID);
        final char _tmpScore;
        _tmpScore = (char) _cursor.getInt(_cursorIndexOfScore);
        _result.setScore(_tmpScore);
        final int _tmpAssignmentID;
        _tmpAssignmentID = _cursor.getInt(_cursorIndexOfAssignmentID);
        _result.setAssignmentID(_tmpAssignmentID);
        final int _tmpStudentID;
        _tmpStudentID = _cursor.getInt(_cursorIndexOfStudentID);
        _result.setStudentID(_tmpStudentID);
        final int _tmpCourseID;
        _tmpCourseID = _cursor.getInt(_cursorIndexOfCourseID);
        _result.setCourseID(_tmpCourseID);
        final String _tmpDateEarned;
        _tmpDateEarned = _cursor.getString(_cursorIndexOfDateEarned);
        _result.setDateEarned(_tmpDateEarned);
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
