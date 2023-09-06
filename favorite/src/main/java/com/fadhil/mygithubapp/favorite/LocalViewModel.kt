package com.fadhil.mygithubapp.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.fadhil.core.data.UserRepository
import com.fadhil.core.data.local.entity.FavoriteUser
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

class LocalViewModel @Inject constructor(private val userRepository: UserRepository) : ViewModel()  {

    fun getfavorite() : LiveData<List<FavoriteUser>> {
        return userRepository.getFavoriteUser().asLiveData()
    }

}