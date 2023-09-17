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
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context) : FavoriteDatabase {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("fadhil".toCharArray())
        val factory = SupportFactory(passphrase)
        return  Room.databaseBuilder(
            context,
            FavoriteDatabase::class.java,"Favorito.db"
        ).fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .openHelperFactory(factory)
            .build()
    }

    @Provides
    fun provideFavoriteDao(database: FavoriteDatabase) : FavoriteDao = database.favoriteDao()
}