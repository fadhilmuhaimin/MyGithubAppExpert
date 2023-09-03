package com.fadhil.core.domain.repository

import com.fadhil.core.data.Resource
import com.fadhil.core.data.remote.response.ItemsItem
import kotlinx.coroutines.flow.Flow

interface IUserRepository {
    fun searchUser(user : String) : Flow<Resource<List<ItemsItem>>>
}