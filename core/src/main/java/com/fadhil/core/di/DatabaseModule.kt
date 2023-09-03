package com.fadhil.core.di

import android.content.Context
import androidx.room.Room
import com.fadhil.core.data.local.room.FavoriteDao
import com.fadhil.core.data.local.room.FavoriteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) : FavoriteDatabase = Room.databaseBuilder(
        context,
        FavoriteDatabase::class.java,"Favorito.db"
    ).fallbackToDestructiveMigration()
        .allowMainThreadQueries()
        .build()

    @Provides
    fun provideFavoriteDao(database: FavoriteDatabase) : FavoriteDao = database.favoriteDao()
}