package com.fadhil.core.domain.repository

import com.fadhil.core.data.Resource
import com.fadhil.core.data.local.entity.FavoriteUser
import com.fadhil.core.data.remote.response.DetailResponse
import com.fadhil.core.data.remote.response.ItemsItem
import com.fadhil.core.domain.model.Detail
import com.fadhil.core.domain.model.ItemsSearch
import kotlinx.coroutines.flow.Flow

interface IUserRepository {
    fun searchUser(user : String) : Flow<Resource<List<ItemsSearch>>>
    fun getFavoriteUser() : Flow<List<ItemsSearch>>

    fun getFavoritebyUser(user : String) : Flow<ItemsSearch?>?

    fun getFollowing(user: String) : Flow<Resource<List<ItemsSearch>>>
    fun getFollower(user: String) : Flow<Resource<List<ItemsSearch>>>
    fun getDetailUser(user: String) : Flow<Resource<Detail>>

    fun insertData(favoriteUser: ItemsSearch)

    fun delete(user: String)



}