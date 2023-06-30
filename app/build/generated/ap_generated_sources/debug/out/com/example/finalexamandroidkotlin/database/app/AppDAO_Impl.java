package com.example.finalexamandroidkotlin.database.app;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.finalexamandroidkotlin.model.App;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDAO_Impl implements AppDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<App> __insertionAdapterOfApp;

  public AppDAO_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfApp = new EntityInsertionAdapter<App>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `App` (`isFirstInstallApp`,`id`) VALUES (?,nullif(?, 0))";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, App value) {
        final int _tmp = value.isFirstInstallApp() ? 1 : 0;
        stmt.bindLong(1, _tmp);
        stmt.bindLong(2, value.getId());
      }
    };
  }

  @Override
  public void insert(final App app) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfApp.insert(app);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public App getApp() {
    final String _sql = "SELECT * FROM APP ORDER BY `ID` DESC LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfIsFirstInstallApp = CursorUtil.getColumnIndexOrThrow(_cursor, "isFirstInstallApp");
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final App _result;
      if(_cursor.moveToFirst()) {
        final boolean _tmpIsFirstInstallApp;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfIsFirstInstallApp);
        _tmpIsFirstInstallApp = _tmp != 0;
        _result = new App(_tmpIsFirstInstallApp);
        final long _tmpId;
        _tmpId = _cursor.getLong(_cursorIndexOfId);
        _result.setId(_tmpId);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
