package com.example.finalexamandroidkotlin.database.user_current;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.finalexamandroidkotlin.model.UserCurrent;

@Database(entities = {UserCurrent.class}, version = 1, exportSchema = false)
public abstract class UserDatabase extends RoomDatabase {
    private static final String dbName = "UserCurrentManager.db";
    private static UserDatabase instance;

    public static synchronized UserDatabase getInstance(Context context) {
        return (instance == null)
                ? Room.databaseBuilder(context.getApplicationContext(), UserDatabase.class, dbName)
                .allowMainThreadQueries().build()
                : instance;
    }

    public abstract UserDAO userDAO();
}
