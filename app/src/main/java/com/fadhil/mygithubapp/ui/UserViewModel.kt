package com.fadhil.mygithubapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.fadhil.core.data.UserRepository
import com.fadhil.core.data.local.entity.FavoriteUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel() {



    fun getSearchResult(query : String) = userRepository.searchUser(query).asLiveData()

    fun  listUser(user : String) = userRepository.searchUser(user)

    fun getDetail(username : String) = userRepository.getDetailUser(username)

    fun getFollower(username: String ) = userRepository.getFollower(username )
    fun getFollowing(username: String) = userRepository.getFollowing(username)

    fun insertFavorite(favoriteUser: FavoriteUser){
        viewModelScope.launch {
            userRepository.insertData(favoriteUser)
        }
    }

    fun delete(username: String){
        viewModelScope.launch {
            userRepository.delete(username)
        }
    }

    fun check(username: String) : LiveData<FavoriteUser>{
       return userRepository.checkDatabase(username)
    }

    fun getfavorite() : LiveData<List<FavoriteUser>>{
        return userRepository.getFavorite()
    }





    

}