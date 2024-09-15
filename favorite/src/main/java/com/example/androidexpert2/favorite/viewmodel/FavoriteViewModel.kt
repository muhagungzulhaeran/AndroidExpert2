package com.example.androidexpert2.favorite.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.androidexpert2.core.domain.usecase.RecipeUseCase

class FavoriteViewModel(private val recipeUseCases: RecipeUseCase) : ViewModel() {

    fun getFavoriteRecipes() = recipeUseCases.getFavoriteRecipe().asLiveData()
}