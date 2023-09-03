package com.fadhil.core.domain.usecase

import com.fadhil.core.data.Resource
import com.fadhil.core.data.remote.response.ItemsItem
import kotlinx.coroutines.flow.Flow

interface UserUseCase {
    fun searchUser(user: String) : Flow<Resource<List<ItemsItem>>>
}