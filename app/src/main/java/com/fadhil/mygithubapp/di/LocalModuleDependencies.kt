package com.fadhil.mygithubapp.di

import com.fadhil.core.domain.usecase.UserUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface LocalModuleDependencies {

    fun tourismUseCase(): UserUseCase
}