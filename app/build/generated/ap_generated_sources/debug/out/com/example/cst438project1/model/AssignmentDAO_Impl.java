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
public final class AssignmentDAO_Impl implements AssignmentDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfAssignment;

  private final EntityDeletionOrUpdateAdapter __deletionAdapterOfAssignment;

  private final EntityDeletionOrUpdateAdapter __updateAdapterOfAssignment;

  public AssignmentDAO_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfAssignment = new EntityInsertionAdapter<Assignment>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `Assignment`(`assignmentID`,`details`,`maxScore`,`earnedScore`,`assignedDate`,`dueDate`,`categoryID`,`courseID`) VALUES (nullif(?, 0),?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Assignment value) {
        stmt.bindLong(1, value.getAssignmentID());
        if (value.getDetails() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getDetails());
        }
        stmt.bindDouble(3, value.getMaxScore());
        stmt.bindDouble(4, value.getEarnedScore());
        if (value.getAssignedDate() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getAssignedDate());
        }
        if (value.getDueDate() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getDueDate());
        }
        stmt.bindLong(7, value.getCategoryID());
        stmt.bindLong(8, value.getCourseID());
      }
    };
    this.__deletionAdapterOfAssignment = new EntityDeletionOrUpdateAdapter<Assignment>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `Assignment` WHERE `assignmentID` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Assignment value) {
        stmt.bindLong(1, value.getAssignmentID());
      }
    };
    this.__updateAdapterOfAssignment = new EntityDeletionOrUpdateAdapter<Assignment>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `Assignment` SET `assignmentID` = ?,`details` = ?,`maxScore` = ?,`earnedScore` = ?,`assignedDate` = ?,`dueDate` = ?,`categoryID` = ?,`courseID` = ? WHERE `assignmentID` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Assignment value) {
        stmt.bindLong(1, value.getAssignmentID());
        if (value.getDetails() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getDetails());
        }
        stmt.bindDouble(3, value.getMaxScore());
        stmt.bindDouble(4, value.getEarnedScore());
        if (value.getAssignedDate() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getAssignedDate());
        }
        if (value.getDueDate() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getDueDate());
        }
        stmt.bindLong(7, value.getCategoryID());
        stmt.bindLong(8, value.getCourseID());
        stmt.bindLong(9, value.getAssignmentID());
      }
    };
  }

  @Override
  public void addAssignment(Assignment... assignments) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfAssignment.insert(assignments);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteAssignment(Assignment assignment) {
    __db.beginTransaction();
    try {
      __deletionAdapterOfAssignment.handle(assignment);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateAssignment(Assignment assignments) {
    __db.beginTransaction();
    try {
      __updateAdapterOfAssignment.handle(assignments);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<Assignment> getAllAssignments() {
    final String _sql = "select * from assignment";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfAssignmentID = _cursor.getColumnIndexOrThrow("assignmentID");
      final int _cursorIndexOfDetails = _cursor.getColumnIndexOrThrow("details");
      final int _cursorIndexOfMaxScore = _cursor.getColumnIndexOrThrow("maxScore");
      final int _cursorIndexOfEarnedScore = _cursor.getColumnIndexOrThrow("earnedScore");
      final int _cursorIndexOfAssignedDate = _cursor.getColumnIndexOrThrow("assignedDate");
      final int _cursorIndexOfDueDate = _cursor.getColumnIndexOrThrow("dueDate");
      final int _cursorIndexOfCategoryID = _cursor.getColumnIndexOrThrow("categoryID");
      final int _cursorIndexOfCourseID = _cursor.getColumnIndexOrThrow("courseID");
      final List<Assignment> _result = new ArrayList<Assignment>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Assignment _item;
        _item = new Assignment();
        final int _tmpAssignmentID;
        _tmpAssignmentID = _cursor.getInt(_cursorIndexOfAssignmentID);
        _item.setAssignmentID(_tmpAssignmentID);
        final String _tmpDetails;
        _tmpDetails = _cursor.getString(_cursorIndexOfDetails);
        _item.setDetails(_tmpDetails);
        final float _tmpMaxScore;
        _tmpMaxScore = _cursor.getFloat(_cursorIndexOfMaxScore);
        _item.setMaxScore(_tmpMaxScore);
        final float _tmpEarnedScore;
        _tmpEarnedScore = _cursor.getFloat(_cursorIndexOfEarnedScore);
        _item.setEarnedScore(_tmpEarnedScore);
        final String _tmpAssignedDate;
        _tmpAssignedDate = _cursor.getString(_cursorIndexOfAssignedDate);
        _item.setAssignedDate(_tmpAssignedDate);
        final String _tmpDueDate;
        _tmpDueDate = _cursor.getString(_cursorIndexOfDueDate);
        _item.setDueDate(_tmpDueDate);
        final int _tmpCategoryID;
        _tmpCategoryID = _cursor.getInt(_cursorIndexOfCategoryID);
        _item.setCategoryID(_tmpCategoryID);
        final int _tmpCourseID;
        _tmpCourseID = _cursor.getInt(_cursorIndexOfCourseID);
        _item.setCourseID(_tmpCourseID);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public Assignment findAssignment(int assignID) {
    final String _sql = "SELECT * FROM assignment WHERE assignmentID = ? ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, assignID);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfAssignmentID = _cursor.getColumnIndexOrThrow("assignmentID");
      final int _cursorIndexOfDetails = _cursor.getColumnIndexOrThrow("details");
      final int _cursorIndexOfMaxScore = _cursor.getColumnIndexOrThrow("maxScore");
      final int _cursorIndexOfEarnedScore = _cursor.getColumnIndexOrThrow("earnedScore");
      final int _cursorIndexOfAssignedDate = _cursor.getColumnIndexOrThrow("assignedDate");
      final int _cursorIndexOfDueDate = _cursor.getColumnIndexOrThrow("dueDate");
      final int _cursorIndexOfCategoryID = _cursor.getColumnIndexOrThrow("categoryID");
      final int _cursorIndexOfCourseID = _cursor.getColumnIndexOrThrow("courseID");
      final Assignment _result;
      if(_cursor.moveToFirst()) {
        _result = new Assignment();
        final int _tmpAssignmentID;
        _tmpAssignmentID = _cursor.getInt(_cursorIndexOfAssignmentID);
        _result.setAssignmentID(_tmpAssignmentID);
        final String _tmpDetails;
        _tmpDetails = _cursor.getString(_cursorIndexOfDetails);
        _result.setDetails(_tmpDetails);
        final float _tmpMaxScore;
        _tmpMaxScore = _cursor.getFloat(_cursorIndexOfMaxScore);
        _result.setMaxScore(_tmpMaxScore);
        final float _tmpEarnedScore;
        _tmpEarnedScore = _cursor.getFloat(_cursorIndexOfEarnedScore);
        _result.setEarnedScore(_tmpEarnedScore);
        final String _tmpAssignedDate;
        _tmpAssignedDate = _cursor.getString(_cursorIndexOfAssignedDate);
        _result.setAssignedDate(_tmpAssignedDate);
        final String _tmpDueDate;
        _tmpDueDate = _cursor.getString(_cursorIndexOfDueDate);
        _result.setDueDate(_tmpDueDate);
        final int _tmpCategoryID;
        _tmpCategoryID = _cursor.getInt(_cursorIndexOfCategoryID);
        _result.setCategoryID(_tmpCategoryID);
        final int _tmpCourseID;
        _tmpCourseID = _cursor.getInt(_cursorIndexOfCourseID);
        _result.setCourseID(_tmpCourseID);
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
