package com.fadhil.core.data.local

import com.fadhil.core.data.local.room.FavoriteDao
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class LocalDataSource @Inject constructor(private val userDao: FavoriteDao) {


}