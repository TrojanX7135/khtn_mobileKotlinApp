package com.example.finalexamandroidkotlin.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "UserCurrent")
data class UserCurrent(
    var email: String,
    var password: String,
    var userName: String,
    var isRemember: Boolean
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}