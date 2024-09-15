package com.example.androidexpert2.di

import com.example.androidexpert2.core.domain.usecase.RecipeInteractor
import com.example.androidexpert2.core.domain.usecase.RecipeUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModul {

    @Binds
    @Singleton
    abstract fun provideRecipeUseCases(recipeInteractor: RecipeInteractor): RecipeUseCase
}