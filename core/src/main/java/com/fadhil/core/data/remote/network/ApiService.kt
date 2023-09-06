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