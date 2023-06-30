package com.example.finalexamandroidkotlin.database.app

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.finalexamandroidkotlin.model.App

@Dao
interface AppDAO {
    @Insert
    fun insert(app: App)

    @Query("SELECT * FROM APP ORDER BY `ID` DESC LIMIT 1")
    fun getApp(): App?
}