package com.fadhil.core.data.remote.network

import com.fadhil.core.data.remote.response.ItemsItem
import com.fadhil.core.data.remote.response.SearchResponse
import com.fadhil.core.data.remote.response.DetailResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("search/users")
    @Headers("Authorization: token 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")
    suspend fun searchUser(
        @Query("q") username: String
    ) : SearchResponse

    @GET("users/{username}")
    @Headers("Authorization: token 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")
    suspend fun findUserDetailByUsername(
        @Path("username") username: String
    ) : DetailResponse

    @GET("users/{username}/followers")
    @Headers("Authorization: token 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")
    suspend fun findFollowers(
        @Path("username") username: String
    ) : List<ItemsItem>

    @GET("users/{username}/following")
    @Headers("Authorization: token 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")
    suspend fun findFollowing(
        @Path("username") username: String
    ) :List<ItemsItem>




}