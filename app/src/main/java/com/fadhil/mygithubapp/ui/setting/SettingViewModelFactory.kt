package com.fadhil.mygithubapp.ui.setting


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory
import com.fadhil.mygithubapp.Utils.SettingPreferences

class SettingViewModelFactory(private val pref: SettingPreferences) :  NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {


        @Suppress("UNCHECKED_CAST")
        if (modelClass.isAssignableFrom(SettingViewModel::class.java)) {
            return SettingViewModel(pref) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)

    }
}