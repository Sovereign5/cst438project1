package com.example.cst438project1.DB;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.cst438project1.GradeCategory;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("unchecked")
public final class GradeCategoryDAO_Impl implements GradeCategoryDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter __insertionAdapterOfGradeCategory;

  private final EntityDeletionOrUpdateAdapter __deletionAdapterOfGradeCategory;

  private final EntityDeletionOrUpdateAdapter __updateAdapterOfGradeCategory;

  private final SharedSQLiteStatement __preparedStmtOfDeleteByCatId;

  public GradeCategoryDAO_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfGradeCategory = new EntityInsertionAdapter<GradeCategory>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `grade_category_table`(`CategoryId`,`title`,`weight`,`GradeId`) VALUES (nullif(?, 0),?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, GradeCategory value) {
        stmt.bindLong(1, value.getCategoryId());
        if (value.getTitle() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getTitle());
        }
        stmt.bindDouble(3, value.getWeight());
        stmt.bindLong(4, value.getGradeId());
      }
    };
    this.__deletionAdapterOfGradeCategory = new EntityDeletionOrUpdateAdapter<GradeCategory>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `grade_category_table` WHERE `CategoryId` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, GradeCategory value) {
        stmt.bindLong(1, value.getCategoryId());
      }
    };
    this.__updateAdapterOfGradeCategory = new EntityDeletionOrUpdateAdapter<GradeCategory>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `grade_category_table` SET `CategoryId` = ?,`title` = ?,`weight` = ?,`GradeId` = ? WHERE `CategoryId` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, GradeCategory value) {
        stmt.bindLong(1, value.getCategoryId());
        if (value.getTitle() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getTitle());
        }
        stmt.bindDouble(3, value.getWeight());
        stmt.bindLong(4, value.getGradeId());
        stmt.bindLong(5, value.getCategoryId());
      }
    };
    this.__preparedStmtOfDeleteByCatId = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM grade_category_table WHERE CategoryId = ?";
        return _query;
      }
    };
  }

  @Override
  public void insert(GradeCategory... gradeCategories) {
    __db.beginTransaction();
    try {
      __insertionAdapterOfGradeCategory.insert(gradeCategories);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(GradeCategory gradeCategory) {
    __db.beginTransaction();
    try {
      __deletionAdapterOfGradeCategory.handle(gradeCategory);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(GradeCategory... gradeCategories) {
    __db.beginTransaction();
    try {
      __updateAdapterOfGradeCategory.handleMultiple(gradeCategories);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteByCatId(long categoryID) {
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteByCatId.acquire();
    __db.beginTransaction();
    try {
      int _argIndex = 1;
      _stmt.bindLong(_argIndex, categoryID);
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteByCatId.release(_stmt);
    }
  }

  @Override
  public List<GradeCategory> getGradeCategories() {
    final String _sql = "SELECT * FROM grade_category_table";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final Cursor _cursor = __db.query(_statement);
    try {
      final int _cursorIndexOfCategoryId = _cursor.getColumnIndexOrThrow("CategoryId");
      final int _cursorIndexOfTitle = _cursor.getColumnIndexOrThrow("title");
      final int _cursorIndexOfWeight = _cursor.getColumnIndexOrThrow("weight");
      final int _cursorIndexOfGradeId = _cursor.getColumnIndexOrThrow("GradeId");
      final List<GradeCategory> _result = new ArrayList<GradeCategory>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final GradeCategory _item;
        _item = new GradeCategory();
        final int _tmpCategoryId;
        _tmpCategoryId = _cursor.getInt(_cursorIndexOfCategoryId);
        _item.setCategoryId(_tmpCategoryId);
        final String _tmpTitle;
        _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
        _item.setTitle(_tmpTitle);
        final double _tmpWeight;
        _tmpWeight = _cursor.getDouble(_cursorIndexOfWeight);
        _item.setWeight(_tmpWeight);
        final int _tmpGradeId;
        _tmpGradeId = _cursor.getInt(_cursorIndexOfGradeId);
        _item.setGradeId(_tmpGradeId);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
