package com.fadhil.mygithubapp.favorite

import android.content.Context
import com.fadhil.mygithubapp.di.LocalModuleDependencies
import dagger.BindsInstance
import dagger.Component

@Component(dependencies = [LocalModuleDependencies::class])
interface LocalComponent {

    fun inject(activity: LocalActivity)

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun appDependencies(mapsModuleDependencies: LocalModuleDependencies): Builder
        fun build(): LocalComponent
    }

}