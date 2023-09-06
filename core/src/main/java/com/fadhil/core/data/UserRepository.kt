package com.fadhil.core.data

import androidx.lifecycle.*
import com.fadhil.core.data.local.LocalDataSource
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
    private val localDataSource: LocalDataSource,
    private val apiService: ApiService,
    private val favoriteDao: FavoriteDao
) : IUserRepository {


    private val _detail = MutableLiveData<DetailResponse>()
    val detail : LiveData<DetailResponse> = _detail



    override fun getFollower(username: String ): Flow<Resource<List<ItemsItem>>> = flow {
        emit(Resource.Loading())
        remoteDataSource.getFollower(username)
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

    }
    override fun getFollowing(username: String): Flow<Resource<List<ItemsItem>>> = flow {
        emit(Resource.Loading())
        remoteDataSource.getFollowing(username)
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

    }

    suspend fun insertData(favoriteUser: FavoriteUser){
        favoriteDao.insertFavorite(favoriteUser)
    }



    suspend fun delete(username: String) {
        favoriteDao.delete(username)
    }



    override fun getDetailUser(username : String) : Flow<Resource<DetailResponse>> =
        flow<Resource<DetailResponse>> {
            emit(Resource.Loading())
            remoteDataSource.getDetail(username)
                .catch { e ->
                    emit(Resource.Error(e.toString()))
                }
                .collect{ apiResponse ->
                    when(apiResponse){
                        is ApiResponse.Success -> {
                            val userLiset = apiResponse.data
                            emit(Resource.Success(userLiset))
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

    override fun getFavoriteUser(): Flow<List<FavoriteUser>> {
        return localDataSource.getFavoriteTourism()
    }

    override fun getFavoritebyUser(user: String): Flow<FavoriteUser> {
        return localDataSource.getFavoritebyUser(user)
    }


}