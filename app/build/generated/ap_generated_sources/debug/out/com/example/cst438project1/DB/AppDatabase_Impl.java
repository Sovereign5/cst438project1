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
  private volatile GradeCategoryDAO _gradeCategoryDAO;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `grade_category_table` (`CategoryId` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `title` TEXT, `weight` REAL NOT NULL, `GradeId` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, \"04c261ad690270b2ac579cf9247de657\")");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `grade_category_table`");
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
        final HashMap<String, TableInfo.Column> _columnsGradeCategoryTable = new HashMap<String, TableInfo.Column>(4);
        _columnsGradeCategoryTable.put("CategoryId", new TableInfo.Column("CategoryId", "INTEGER", true, 1));
        _columnsGradeCategoryTable.put("title", new TableInfo.Column("title", "TEXT", false, 0));
        _columnsGradeCategoryTable.put("weight", new TableInfo.Column("weight", "REAL", true, 0));
        _columnsGradeCategoryTable.put("GradeId", new TableInfo.Column("GradeId", "INTEGER", true, 0));
        final HashSet<TableInfo.ForeignKey> _foreignKeysGradeCategoryTable = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesGradeCategoryTable = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoGradeCategoryTable = new TableInfo("grade_category_table", _columnsGradeCategoryTable, _foreignKeysGradeCategoryTable, _indicesGradeCategoryTable);
        final TableInfo _existingGradeCategoryTable = TableInfo.read(_db, "grade_category_table");
        if (! _infoGradeCategoryTable.equals(_existingGradeCategoryTable)) {
          throw new IllegalStateException("Migration didn't properly handle grade_category_table(com.example.cst438project1.GradeCategory).\n"
                  + " Expected:\n" + _infoGradeCategoryTable + "\n"
                  + " Found:\n" + _existingGradeCategoryTable);
        }
      }
    }, "04c261ad690270b2ac579cf9247de657", "34d3670e37e10fa4801758fa490d20e3");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    return new InvalidationTracker(this, "grade_category_table");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `grade_category_table`");
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
  public GradeCategoryDAO getGradeCategoryDao() {
    if (_gradeCategoryDAO != null) {
      return _gradeCategoryDAO;
    } else {
      synchronized(this) {
        if(_gradeCategoryDAO == null) {
          _gradeCategoryDAO = new GradeCategoryDAO_Impl(this);
        }
        return _gradeCategoryDAO;
      }
    }
  }
}
