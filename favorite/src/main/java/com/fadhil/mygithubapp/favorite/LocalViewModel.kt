package com.fadhil.mygithubapp.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.fadhil.core.data.UserRepository
import com.fadhil.core.data.local.entity.FavoriteUser
import com.fadhil.core.domain.model.ItemsSearch
import com.fadhil.core.domain.usecase.UserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

class LocalViewModel @Inject constructor(private val userRepository: UserUseCase) : ViewModel()  {

    fun getfavorite() : LiveData<List<ItemsSearch>>? {
        return userRepository.getFavoriteUser()?.asLiveData()
    }

}