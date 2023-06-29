package com.example.finalexamandroidkotlin.database.app;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.finalexamandroidkotlin.model.App;

@Database(entities = {App.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static final String dbName = "AppManager.db";
    private static AppDatabase instance;

    public static synchronized AppDatabase getInstance(Context context) {
        return (instance == null)
                ? Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, dbName)
                .allowMainThreadQueries().build()
                : instance;
    }

    public abstract AppDAO appDAO();
}
