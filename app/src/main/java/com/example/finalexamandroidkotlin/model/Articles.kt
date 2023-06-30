package com.example.finalexamandroidkotlin.model

import android.annotation.SuppressLint
import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.database.IgnoreExtraProperties
import org.jetbrains.annotations.NotNull

@IgnoreExtraProperties
class Articles (
    var id: String? = null,
    var title: String? = null,
    var description: String? = null,
    var image: String? = null,
    var avatarUser: String? = null,
    var userName: String? = null,
    var date: String? = null,
    var listSave: MutableList<String>? = null,
    var listHeart: MutableList<String>? = null,
    var listTag: MutableList<String>? = null
) {
}