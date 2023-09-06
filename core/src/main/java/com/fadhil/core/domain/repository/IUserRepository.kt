package com.fadhil.core.domain.repository

import com.fadhil.core.data.Resource
import com.fadhil.core.data.local.entity.FavoriteUser
import com.fadhil.core.data.remote.response.DetailResponse
import com.fadhil.core.data.remote.response.ItemsItem
import kotlinx.coroutines.flow.Flow

interface IUserRepository {
    fun searchUser(user : String) : Flow<Resource<List<ItemsItem>>>
    fun getFavoriteUser() : Flow<List<FavoriteUser>>

    fun getFavoritebyUser(user : String) : Flow<FavoriteUser>

    fun getFollowing(user: String) : Flow<Resource<List<ItemsItem>>>
    fun getFollower(user: String) : Flow<Resource<List<ItemsItem>>>
    fun getDetailUser(user: String) : Flow<Resource<DetailResponse>>


}