package com.fadhil.core.domain.usecase

import com.fadhil.core.data.Resource
import com.fadhil.core.domain.model.Detail
import com.fadhil.core.domain.model.ItemsSearch
import com.fadhil.core.domain.repository.IUserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserInteractor @Inject constructor(private val userRepository : IUserRepository) : UserUseCase {
    override fun searchUser(user: String): Flow<Resource<List<ItemsSearch>>> = userRepository.searchUser(user)

    override fun getFavoriteUser(): Flow<List<ItemsSearch>>  = userRepository.getFavoriteUser()

    override fun getFavoritebyUser(user: String): Flow<ItemsSearch?>?  = userRepository.getFavoritebyUser(user)

    override fun getFollowing(user: String): Flow<Resource<List<ItemsSearch>>> = userRepository.getFollowing(user)

    override fun getFollower(user: String): Flow<Resource<List<ItemsSearch>>> = userRepository.getFollower(user)
    override fun getDetailUser(user: String): Flow<Resource<Detail>> = userRepository.getDetailUser(user)

    override fun insertData(favoriteUser: ItemsSearch)  = userRepository.insertData(favoriteUser)

    override fun delete(user: String) = userRepository.delete(user)

}