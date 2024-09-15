package com.example.androidexpert2.core.domain.usecase

import com.example.androidexpert2.core.data.Result
import com.example.androidexpert2.core.domain.model.Recipe
import com.example.androidexpert2.core.domain.repository.IRecipeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RecipeInteractor @Inject constructor(
    private val recipeRepository: IRecipeRepository
) : RecipeUseCase  {
    override fun getRecipes() = recipeRepository.getRecipes()

    override fun getDetailRecipe(id: Int): Flow<Result<Recipe>> =
        recipeRepository.getDetailRecipe(id)

    override fun getRecipeById(id: Int): Flow<Recipe> =
        recipeRepository.getRecipeById(id)

    override fun getFavoriteRecipe() = recipeRepository.getFavoriteRecipe()

    override fun setFavoriteRecipe(recipe: Recipe, state: Boolean) =
        recipeRepository.setFavoriteRecipe(recipe, state)
}