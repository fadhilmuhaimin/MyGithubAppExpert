package com.fadhil.mygithubapp.di


import com.fadhil.core.domain.usecase.UserInteractor
import com.fadhil.core.domain.usecase.UserUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {

    @Binds
    @Singleton
    abstract fun provideUSerUseCase(userInteractor: UserInteractor): UserUseCase
}
