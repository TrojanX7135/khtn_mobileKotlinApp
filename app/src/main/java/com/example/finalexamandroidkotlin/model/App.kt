package com.example.finalexamandroidkotlin.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "App")
data class App (
    var isFirstInstallApp: Boolean
) {

    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}