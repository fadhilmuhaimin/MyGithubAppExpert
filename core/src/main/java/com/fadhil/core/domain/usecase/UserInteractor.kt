package com.fadhil.core.domain.usecase

import com.fadhil.core.data.Resource
import com.fadhil.core.data.remote.response.ItemsItem
import com.fadhil.core.domain.repository.IUserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserInteractor @Inject constructor(private val userRepository : IUserRepository) : UserUseCase {
    override fun searchUser(user: String): Flow<Resource<List<ItemsItem>>> = userRepository.searchUser(user)
}