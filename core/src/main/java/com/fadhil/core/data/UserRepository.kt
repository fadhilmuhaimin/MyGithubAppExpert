package com.fadhil.core.data

import androidx.lifecycle.*
import com.fadhil.core.data.local.entity.FavoriteUser
import com.fadhil.core.data.local.room.FavoriteDao
import com.fadhil.core.data.remote.RemoteDataSource
import com.fadhil.core.data.remote.network.ApiResponse
import com.fadhil.core.data.remote.response.ItemsItem
import com.fadhil.core.data.remote.response.DetailResponse
import com.fadhil.core.data.remote.network.ApiService
import com.fadhil.core.data.remote.response.SearchResponse
import com.fadhil.core.domain.repository.IUserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject


class UserRepository @Inject  constructor(
    private val remoteDataSource: RemoteDataSource,
    private val apiService: ApiService,
    private val favoriteDao: FavoriteDao
) : IUserRepository {






    private val _search = MutableLiveData <List<ItemsItem>>()
    val search: LiveData <List<ItemsItem>> = _search

    private val _search2 = MutableLiveData <List<ItemsItem>>()
    val search2: LiveData <List<ItemsItem>> = _search2

    private val _search1 = MutableLiveData <List<ItemsItem>>()
    val search1: LiveData <List<ItemsItem>> = _search1

    private val _detail = MutableLiveData<DetailResponse>()
    val detail : LiveData<DetailResponse> = _detail

    fun getSearchUser(query: String): LiveData<Result<List<ItemsItem>>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.searchUser(query)
            _search.value = (response.items as List<ItemsItem>?)
        }catch (e : Exception){
            emit(Result.Error(e.message.toString()))
        }
        val data : LiveData<Result<List<ItemsItem>>> = search.map { Result.Success(it) }
        emitSource(data)
    }

    fun getFollower(username: String ): LiveData<Result<List<ItemsItem>>> = liveData {
        emit(Result.Loading)
        try {

            val response = apiService.findFollowers(username)
            _search2.value = response
        }catch (e : Exception){
            emit(Result.Error(e.message.toString()))
        }
        val data : LiveData<Result<List<ItemsItem>>> = search2.map { Result.Success(it) }
        emitSource(data)
    }

    fun getFollowing(username: String): LiveData<Result<List<ItemsItem>>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.findFollowing(username)
            _search1.value = response
        }catch (e : Exception){
            emit(Result.Error(e.message.toString()))
        }
        val data : LiveData<Result<List<ItemsItem>>> = search1.map { Result.Success(it) }
        emitSource(data)
    }

    suspend fun insertData(favoriteUser: FavoriteUser){
        favoriteDao.insertFavorite(favoriteUser)
    }

    fun checkDatabase(username: String) : LiveData<FavoriteUser>{
        return favoriteDao.getFavoriteUserByUsername(username)
    }

    suspend fun delete(username: String) {
        favoriteDao.delete(username)
    }

    fun getFavorite() : LiveData<List<FavoriteUser>>{
        return favoriteDao.getFavoriteUser()
    }


    fun getDetailUser(username : String) : LiveData<Result<DetailResponse>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.findUserDetailByUsername(username)
            _detail.value = response

        }catch (e : java.lang.Exception){
            emit(Result.Error(e.message.toString()))

        }

        val data : LiveData<Result<DetailResponse>> = detail.map { Result.Success(it) }
        emitSource(data)


    }



    override fun searchUser(user: String): Flow<Resource<List<ItemsItem>>> =
        flow<Resource<List<ItemsItem>>> {
            emit(Resource.Loading())
            remoteDataSource.searchUser(user)
                .catch { e ->
                    emit(Resource.Error(e.toString()))
                }
                .collect{ apiResponse ->
                    when(apiResponse){
                        is ApiResponse.Success -> {
                            val userLiset = apiResponse.data
                            emit(Resource.Success(userLiset as List<ItemsItem>))
                        }
                        is ApiResponse.Empty -> {
                            emit(Resource.Empty())
                        }
                        is ApiResponse.Error -> {
                            emit(Resource.Error(apiResponse.errorMessage))
                        }
                    }
                }
        }.flowOn(Dispatchers.Default)


}