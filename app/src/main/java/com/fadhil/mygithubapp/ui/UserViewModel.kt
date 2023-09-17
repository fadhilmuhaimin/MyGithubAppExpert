package com.fadhil.mygithubapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.fadhil.core.domain.model.ItemsSearch
import com.fadhil.core.domain.usecase.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val userRepository: UserUseCase) : ViewModel() {



    fun getSearchResult(query : String) = userRepository.searchUser(query).asLiveData()

    fun  listUser(user : String) = userRepository.searchUser(user)

    fun getDetail(username : String) = userRepository.getDetailUser(username).asLiveData()

    fun getFollower(username: String ) = userRepository.getFollower(username ).asLiveData()
    fun getFollowing(username: String) = userRepository.getFollowing(username).asLiveData()

    fun insertFavorite(favoriteUser: ItemsSearch) = userRepository.insertData(favoriteUser)



    fun delete(username: String){
            userRepository.delete(username)

    }

    fun check(username: String) : LiveData<ItemsSearch?>?{
       return userRepository.getFavoritebyUser(username)?.asLiveData()
    }

//    fun getfavorite() : LiveData<List<FavoriteUser>>{
//        return userRepository.getFavorite()
//    }
//




    

}