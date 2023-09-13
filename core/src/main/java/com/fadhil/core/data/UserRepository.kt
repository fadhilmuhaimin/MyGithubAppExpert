package com.fadhil.core.data

import androidx.lifecycle.*
import com.fadhil.core.Utils.AppExecutors
import com.fadhil.core.Utils.DataMapper
import com.fadhil.core.data.local.LocalDataSource
import com.fadhil.core.data.local.entity.FavoriteUser
import com.fadhil.core.data.local.room.FavoriteDao
import com.fadhil.core.data.remote.RemoteDataSource
import com.fadhil.core.data.remote.network.ApiResponse
import com.fadhil.core.data.remote.response.ItemsItem
import com.fadhil.core.data.remote.response.DetailResponse
import com.fadhil.core.data.remote.network.ApiService
import com.fadhil.core.data.remote.response.SearchResponse
import com.fadhil.core.domain.model.Detail
import com.fadhil.core.domain.model.ItemsSearch
import com.fadhil.core.domain.repository.IUserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class UserRepository @Inject  constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val favoriteDao: FavoriteDao,
    private val appExecutors: AppExecutors
) : IUserRepository {



    override fun getFollower(username: String ): Flow<Resource<List<ItemsSearch>>> = flow {
        emit(Resource.Loading())
        remoteDataSource.getFollower(username)
            .catch { e ->
                emit(Resource.Error(e.toString()))
            }
            .collect{ apiResponse ->
                when(apiResponse){
                    is ApiResponse.Success -> {
                        val userLiset = DataMapper.mapResponsesToDomain(apiResponse.data as List<ItemsItem>)
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

    }
    override fun getFollowing(username: String): Flow<Resource<List<ItemsSearch>>> = flow {
        emit(Resource.Loading())
        remoteDataSource.getFollowing(username)
            .catch { e ->
                emit(Resource.Error(e.toString()))
            }
            .collect{ apiResponse ->
                when(apiResponse){
                    is ApiResponse.Success -> {
                        val userLiset = DataMapper.mapResponsesToDomain(apiResponse.data as List<ItemsItem>)
                        emit(Resource.Success(userLiset as List<ItemsSearch>))
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

    override fun insertData(favoriteUser: ItemsSearch){
        val entity = DataMapper.mapDomainToEntity(favoriteUser)
        appExecutors.diskIO().execute{ entity?.let { localDataSource.setFavorite(it) } }
    }



    override fun delete(user: String) {
        appExecutors.diskIO().execute{ localDataSource.delete(user) }
    }



    override fun getDetailUser(username : String) : Flow<Resource<Detail>> =
        flow<Resource<Detail>> {
            emit(Resource.Loading())
            remoteDataSource.getDetail(username)
                .catch { e ->
                    emit(Resource.Error(e.toString()))
                }
                .collect{ apiResponse ->
                    when(apiResponse){
                        is ApiResponse.Success -> {
                            val userLiset = DataMapper.mapResponseDetailToDOmain(apiResponse.data)
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


    override fun searchUser(user: String): Flow<Resource<List<ItemsSearch>>> =
        flow<Resource<List<ItemsSearch>>> {
            emit(Resource.Loading())
            remoteDataSource.searchUser(user)
                .catch { e ->
                    emit(Resource.Error(e.toString()))
                }
                .collect{ apiResponse ->
                    when(apiResponse){
                        is ApiResponse.Success -> {
                            val userLiset = DataMapper.mapResponsesToDomain(apiResponse.data as List<ItemsItem>)
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

    override fun getFavoriteUser(): Flow<List<ItemsSearch>> {
        return localDataSource.getFavoriteTourism().map {
            DataMapper.mapEntityToDomain(it)
        }
    }

    override fun getFavoritebyUser(user: String) : Flow<ItemsSearch?>? {
        return localDataSource.getFavoritebyUser(user)?.map { result ->
            if (result != null){
                DataMapper?.mapEntityToDomainFavorite(result)
            }else{
                null
            }

        }
    }


}