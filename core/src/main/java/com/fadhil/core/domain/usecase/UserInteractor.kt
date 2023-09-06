package com.fadhil.core.domain.usecase

import com.fadhil.core.data.Resource
import com.fadhil.core.data.local.entity.FavoriteUser
import com.fadhil.core.data.remote.response.ItemsItem
import com.fadhil.core.domain.repository.IUserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserInteractor @Inject constructor(private val userRepository : IUserRepository) : UserUseCase {

//    override fun searchUser(user: String): Flow<Resource<List<ItemsItem>>> =
//        userRepository.searchUser(user)
//
//    override fun getFavoriteUSer(): Flow<List<FavoriteUser>> = userRepository.getFavoriteUser()
//    override fun getFavoritebyUser(user: String): Flow<FavoriteUser> = userRepository.getFavoritebyUser(user)
//    override fun getFollowing(user: String): Flow<Resource<List<ItemsItem>>> = userRepository.getFollowing(user)
//    override fun getFollower(user: String): Flow<Resource<List<ItemsItem>>> {
//        TODO("Not yet implemented")
//    }


}