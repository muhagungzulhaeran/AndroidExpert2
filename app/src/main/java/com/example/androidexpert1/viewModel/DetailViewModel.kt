package com.example.androidexpert1.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.androidexpert1.core.domain.model.Recipe
import com.example.androidexpert1.core.domain.usecase.RecipeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val recipeUseCases: RecipeUseCase) : ViewModel() {
    fun getDetailRecipes(id: Int) = recipeUseCases.getDetailRecipe(id).asLiveData()

    fun getRecipesById(id: Int) = recipeUseCases.getRecipeById(id).asLiveData()

    fun setFavoriteRecipes(recipe: Recipe, state: Boolean) =
        recipeUseCases.setFavoriteRecipe(recipe, state)
}