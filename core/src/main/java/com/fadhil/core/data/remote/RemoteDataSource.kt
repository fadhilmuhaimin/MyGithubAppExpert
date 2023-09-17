package com.fadhil.core.data.remote

import android.util.Log
import com.fadhil.core.data.remote.network.ApiResponse
import com.fadhil.core.data.remote.network.ApiService
import com.fadhil.core.data.remote.response.DetailResponse
import com.fadhil.core.data.remote.response.ItemsItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    suspend fun searchUser(user : String): Flow<ApiResponse<List<ItemsItem?>?>> {
        //get data from remote api
        return flow {
            try {
                val response = apiService.searchUser(user)
                val dataArray = response.items
                if (dataArray != null) {
                    if (dataArray.isNotEmpty()){
                        emit(ApiResponse.Success(response.items))
                    } else {
                        emit(ApiResponse.Empty)
                    }
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getFollowing(user: String) : Flow<ApiResponse<List<ItemsItem?>?>>{
        return flow {
            try {
                val response = apiService.findFollowing(user)
                val dataArray = response
                if (dataArray != null) {
                    if (dataArray.isNotEmpty()){
                        emit(ApiResponse.Success(response))
                    } else {
                        emit(ApiResponse.Empty)
                    }
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getFollower(user: String) : Flow<ApiResponse<List<ItemsItem?>?>>{
        return flow {
            try {
                val response = apiService.findFollowers(user)
                val dataArray = response
                if (dataArray != null) {
                    if (dataArray.isNotEmpty()){
                        emit(ApiResponse.Success(response))
                    } else {
                        emit(ApiResponse.Empty)
                    }
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getDetail(user: String) : Flow<ApiResponse<DetailResponse>>{
        return flow {
            try {
                val response = apiService.findUserDetailByUsername(user)
                val dataArray = response
                if (dataArray != null) {
                        emit(ApiResponse.Success(response))
                }else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}

