package com.example.androidexpert1.favorite.di

import android.content.Context
import com.example.androidexpert1.di.FavoriteModuleDependencies
import com.example.androidexpert1.favorite.FavoriteFragment
import dagger.BindsInstance
import dagger.Component

@Component(
    dependencies = [FavoriteModuleDependencies::class]
)

interface FavoriteComponentt {

    fun inject(favoriteFragment: FavoriteFragment)

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun appDependencies(favoriteModuleDependencies: FavoriteModuleDependencies): Builder
        fun build(): FavoriteComponentt
    }
}








