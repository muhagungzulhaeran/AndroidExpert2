package com.example.androidexpert1.core.domain.repository

import com.example.androidexpert1.core.data.Result
import com.example.androidexpert1.core.domain.model.Recipe
import kotlinx.coroutines.flow.Flow

interface IRecipeRepository {

    fun getRecipes(): Flow<Result<List<Recipe>>>

    fun getDetailRecipe(id: Int): Flow<Result<Recipe>>

    fun getRecipeById(id: Int): Flow<Recipe>

    fun getFavoriteRecipe(): Flow<List<Recipe>>

    fun setFavoriteRecipe(recipe: Recipe, state: Boolean)
}