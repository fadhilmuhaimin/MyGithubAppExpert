package com.fadhil.core.data.local

import com.fadhil.core.data.local.entity.FavoriteUser
import com.fadhil.core.data.local.room.FavoriteDao
import com.fadhil.core.data.remote.response.ItemsItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class LocalDataSource @Inject constructor(private val userDao: FavoriteDao) {

    fun getFavoriteTourism(): Flow<List<FavoriteUser>> = userDao.getFavoriteUser()

    fun getFavoritebyUser(user : String) : Flow<FavoriteUser> = userDao.getFavoriteUserByUsername(user)



}