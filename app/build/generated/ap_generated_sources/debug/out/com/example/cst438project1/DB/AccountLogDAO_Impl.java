package com.example.cst438project1.DB;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.cst438project1.AccountLog;
import com.example.cst438project1.model.Assignment;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public final class AccountLogDAO_Impl implements AccountLogDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfAccountLog;

  private final ArrayListTypeConverterAccounts __arrayListTypeConverterAccounts = new ArrayListTypeConverterAccounts();

  private final ArrayListTypeConverterAssignments __arrayListTypeConverterAssignments = new ArrayListTypeConverterAssignments();

  private final EntityDeletionOrUpdateAdapter __deletionAdapterOfAccountLog;

  private final EntityDeletionOrUpdateAdapter __updateAdapterOfAccountLog;

  public AccountLogDAO_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfAccountLog = new EntityInsertionAdapter<AccountLog>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `accountlog`(`mAccountId`,`username`,`password`,`first_name`,`last_name`,`courses`,`userAssignments`) VALUES (nullif(?, 0),?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, AccountLog value) {
        stmt.bindLong(1, value.getAccountId());
        if (value.getUsername() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getUsername());
        }
        if (value.getPassword() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getPassword());
        }
        if (value.getFirstname() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getFirstname());
        }
        if (value.getLastname() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getLastname());
        }
        final String _tmp;
        _tmp = __arrayListTypeConverterAccounts.CourseListToString(value.getUserCourses());
        if (_tmp == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, _tmp);
        }
        final String _tmp_1;
        _tmp_1 = __arrayListTypeConverterAssignments.AssignmentsToString(value.getUserAssignments());
        if (_tmp_1 == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, _tmp_1);
        }
      }
    };
    this.__deletionAdapterOfAccountLog = new EntityDeletionOrUpdateAdapter<AccountLog>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `accountlog` WHERE `mAccountId` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, AccountLog value) {
        stmt.bindLong(1, value.getAccountId());
      }
    };
    this.__updateAdapterOfAccountLog = new EntityDeletionOrUpdateAdapter<AccountLog>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `accountlog` SET `mAccountId` = ?,`username` = ?,`password` = ?,`first_name` = ?,`last_name` = ?,`courses` = ?,`userAssignments` = ? WHERE `mAccountId` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, AccountLog value) {
        stmt.bindLong(1, value.getAccountId());
        if (value.getUsername() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getUsername());
        }
        if (value.getPassword() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getPassword());
        }
        if (value.getFirstname() == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindString(4, value.getFirstname());
        }
        if (value.getLastname() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getLastname());
        }
        final String _tmp;
        _tmp = __arrayListTypeConverterAccounts.CourseListToString(value.getUserCourses());
        if (_tmp == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, _tmp);
        }
        final String _tmp_1;
        _tmp_1 = __arrayListTypeConverterAssignments.AssignmentsToString(value.getUserAssignments());
        if (_tmp_1 == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, _tmp_1);
        }
        stmt.bindLong(8, value.getAccountId());
      }
    };
  }

  @Override
  public void insert(AccountLog... accountLogs) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfAccountLog.insert(accountLogs);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(AccountLog... accountLogs) {
    __db.beginTransaction();
    try {
      __deletionAdapterOfAccountLog.handleMultiple(accountLogs);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(AccountLog... accountLogs) {
    __db.beginTransaction();
    try {
      __updateAdapterOfAccountLog.handleMultiple(accountLogs);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<AccountLog> getAccountLog() {
    final String _sql = "SELECT * FROM accountlog";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfMAccountId = _cursor.getColumnIndexOrThrow("mAccountId");
      final int _cursorIndexOfUsername = _cursor.getColumnIndexOrThrow("username");
      final int _cursorIndexOfPassword = _cursor.getColumnIndexOrThrow("password");
      final int _cursorIndexOfFirstname = _cursor.getColumnIndexOrThrow("first_name");
      final int _cursorIndexOfLastname = _cursor.getColumnIndexOrThrow("last_name");
      final int _cursorIndexOfUserCourses = _cursor.getColumnIndexOrThrow("courses");
      final int _cursorIndexOfUserAssignments = _cursor.getColumnIndexOrThrow("userAssignments");
      final List<AccountLog> _result = new ArrayList<AccountLog>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final AccountLog _item;
        final String _tmpUsername;
        _tmpUsername = _cursor.getString(_cursorIndexOfUsername);
        final String _tmpPassword;
        _tmpPassword = _cursor.getString(_cursorIndexOfPassword);
        final String _tmpFirstname;
        _tmpFirstname = _cursor.getString(_cursorIndexOfFirstname);
        final String _tmpLastname;
        _tmpLastname = _cursor.getString(_cursorIndexOfLastname);
        _item = new AccountLog(_tmpFirstname,_tmpLastname,_tmpUsername,_tmpPassword);
        final int _tmpMAccountId;
        _tmpMAccountId = _cursor.getInt(_cursorIndexOfMAccountId);
        _item.setAccountId(_tmpMAccountId);
        final List<CourseLog> _tmpUserCourses;
        final String _tmp;
        _tmp = _cursor.getString(_cursorIndexOfUserCourses);
        _tmpUserCourses = __arrayListTypeConverterAccounts.stringToCourseList(_tmp);
        _item.setUserCourses(_tmpUserCourses);
        final List<Assignment> _tmpUserAssignments;
        final String _tmp_1;
        _tmp_1 = _cursor.getString(_cursorIndexOfUserAssignments);
        _tmpUserAssignments = __arrayListTypeConverterAssignments.stringToAssignments(_tmp_1);
        _item.setUserAssignments(_tmpUserAssignments);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public AccountLog getAccountWithId(int accountID) {
    final String _sql = "SELECT * FROM accountlog WHERE mAccountID = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, accountID);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfMAccountId = _cursor.getColumnIndexOrThrow("mAccountId");
      final int _cursorIndexOfUsername = _cursor.getColumnIndexOrThrow("username");
      final int _cursorIndexOfPassword = _cursor.getColumnIndexOrThrow("password");
      final int _cursorIndexOfFirstname = _cursor.getColumnIndexOrThrow("first_name");
      final int _cursorIndexOfLastname = _cursor.getColumnIndexOrThrow("last_name");
      final int _cursorIndexOfUserCourses = _cursor.getColumnIndexOrThrow("courses");
      final int _cursorIndexOfUserAssignments = _cursor.getColumnIndexOrThrow("userAssignments");
      final AccountLog _result;
      if(_cursor.moveToFirst()) {
        final String _tmpUsername;
        _tmpUsername = _cursor.getString(_cursorIndexOfUsername);
        final String _tmpPassword;
        _tmpPassword = _cursor.getString(_cursorIndexOfPassword);
        final String _tmpFirstname;
        _tmpFirstname = _cursor.getString(_cursorIndexOfFirstname);
        final String _tmpLastname;
        _tmpLastname = _cursor.getString(_cursorIndexOfLastname);
        _result = new AccountLog(_tmpFirstname,_tmpLastname,_tmpUsername,_tmpPassword);
        final int _tmpMAccountId;
        _tmpMAccountId = _cursor.getInt(_cursorIndexOfMAccountId);
        _result.setAccountId(_tmpMAccountId);
        final List<CourseLog> _tmpUserCourses;
        final String _tmp;
        _tmp = _cursor.getString(_cursorIndexOfUserCourses);
        _tmpUserCourses = __arrayListTypeConverterAccounts.stringToCourseList(_tmp);
        _result.setUserCourses(_tmpUserCourses);
        final List<Assignment> _tmpUserAssignments;
        final String _tmp_1;
        _tmp_1 = _cursor.getString(_cursorIndexOfUserAssignments);
        _tmpUserAssignments = __arrayListTypeConverterAssignments.stringToAssignments(_tmp_1);
        _result.setUserAssignments(_tmpUserAssignments);
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
  public boolean findCredentials(String user, String pass) {
    final String _sql = "SELECT * FROM accountlog WHERE username LIKE ? AND  password LIKE ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (user == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, user);
    }
    _argIndex = 2;
    if (pass == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, pass);
    }
    final Cursor _cursor = __db.query(_statement);
    try {
      final boolean _result;
      if(_cursor.moveToFirst()) {
        final int _tmp;
        _tmp = _cursor.getInt(0);
        _result = _tmp != 0;
      } else {
        _result = false;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public AccountLog findAccount(String user, String pass) {
    final String _sql = "SELECT * FROM accountlog WHERE username = ? AND  password = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (user == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, user);
    }
    _argIndex = 2;
    if (pass == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, pass);
    }
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfMAccountId = _cursor.getColumnIndexOrThrow("mAccountId");
      final int _cursorIndexOfUsername = _cursor.getColumnIndexOrThrow("username");
      final int _cursorIndexOfPassword = _cursor.getColumnIndexOrThrow("password");
      final int _cursorIndexOfFirstname = _cursor.getColumnIndexOrThrow("first_name");
      final int _cursorIndexOfLastname = _cursor.getColumnIndexOrThrow("last_name");
      final int _cursorIndexOfUserCourses = _cursor.getColumnIndexOrThrow("courses");
      final int _cursorIndexOfUserAssignments = _cursor.getColumnIndexOrThrow("userAssignments");
      final AccountLog _result;
      if(_cursor.moveToFirst()) {
        final String _tmpUsername;
        _tmpUsername = _cursor.getString(_cursorIndexOfUsername);
        final String _tmpPassword;
        _tmpPassword = _cursor.getString(_cursorIndexOfPassword);
        final String _tmpFirstname;
        _tmpFirstname = _cursor.getString(_cursorIndexOfFirstname);
        final String _tmpLastname;
        _tmpLastname = _cursor.getString(_cursorIndexOfLastname);
        _result = new AccountLog(_tmpFirstname,_tmpLastname,_tmpUsername,_tmpPassword);
        final int _tmpMAccountId;
        _tmpMAccountId = _cursor.getInt(_cursorIndexOfMAccountId);
        _result.setAccountId(_tmpMAccountId);
        final List<CourseLog> _tmpUserCourses;
        final String _tmp;
        _tmp = _cursor.getString(_cursorIndexOfUserCourses);
        _tmpUserCourses = __arrayListTypeConverterAccounts.stringToCourseList(_tmp);
        _result.setUserCourses(_tmpUserCourses);
        final List<Assignment> _tmpUserAssignments;
        final String _tmp_1;
        _tmp_1 = _cursor.getString(_cursorIndexOfUserAssignments);
        _tmpUserAssignments = __arrayListTypeConverterAssignments.stringToAssignments(_tmp_1);
        _result.setUserAssignments(_tmpUserAssignments);
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
