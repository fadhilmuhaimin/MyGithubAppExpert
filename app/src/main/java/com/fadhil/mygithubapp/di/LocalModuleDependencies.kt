package com.fadhil.mygithubapp.di

import com.fadhil.core.data.UserRepository
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface LocalModuleDependencies {

    fun tourismUseCase(): UserRepository
}