package com.fadhil.core.domain.usecase

import com.fadhil.core.data.Resource
import com.fadhil.core.domain.model.Detail
import com.fadhil.core.domain.model.ItemsSearch
import kotlinx.coroutines.flow.Flow

interface UserUseCase {
    fun searchUser(user : String) : Flow<Resource<List<ItemsSearch>>>
    fun getFavoriteUser() : Flow<List<ItemsSearch>>

    fun getFavoritebyUser(user : String) : Flow<ItemsSearch?>?

    fun getFollowing(user: String) : Flow<Resource<List<ItemsSearch>>>
    fun getFollower(user: String) : Flow<Resource<List<ItemsSearch>>>
    fun getDetailUser(user: String) : Flow<Resource<Detail>>
    fun insertData(favoriteUser: ItemsSearch)
    fun delete(user: String)
}