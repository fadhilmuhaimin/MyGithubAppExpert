package com.fadhil.core.data.local.room

import androidx.room.*
import com.fadhil.core.data.local.entity.FavoriteUser
import kotlinx.coroutines.flow.Flow


@Dao
interface FavoriteDao {

    @Query("SELECT * FROM favorite WHERE username = :username")
    fun getFavoriteUserByUsername(username: String): Flow<FavoriteUser>

    @Query("SELECT * FROM favorite")
    fun getFavoriteUser() : Flow<List<FavoriteUser>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertFavorite(favoriteUser: FavoriteUser)


    @Query("DELETE FROM favorite WHERE username =:username")
    fun delete(username: String)




}