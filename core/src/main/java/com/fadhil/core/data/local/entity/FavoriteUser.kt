package com.fadhil.core.data.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite")
data class FavoriteUser(

    @PrimaryKey(autoGenerate = false)
    @NonNull
    @field:ColumnInfo(name = "username")
    var username: String = "",

    @field:ColumnInfo(name = "avatarUrl")
    var avatarUrl: String? = null,

)