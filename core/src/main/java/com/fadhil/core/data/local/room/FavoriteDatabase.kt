package com.fadhil.core.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.fadhil.core.data.local.entity.FavoriteUser


@Database(entities = [FavoriteUser::class], version = 3, exportSchema = false)
abstract class FavoriteDatabase : RoomDatabase() {
    abstract fun favoriteDao() : FavoriteDao

}