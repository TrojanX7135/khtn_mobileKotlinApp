package com.example.finalexamandroidkotlin.database.user_current

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.finalexamandroidkotlin.model.UserCurrent

@Dao
interface UserDAO {
    @Insert
    fun rememberUserCurrent(userCurrent: UserCurrent)

    @Update
    fun updateUserCurrentAccount(userCurrent: UserCurrent)

    @Query("SELECT * FROM USERCURRENT ORDER BY `ID` DESC LIMIT 1")
    fun getUserCurrentAccount(): UserCurrent?
}