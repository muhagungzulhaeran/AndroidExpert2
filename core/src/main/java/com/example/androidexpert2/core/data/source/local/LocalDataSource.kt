package com.example.androidexpert2.core.data.source.local

import com.example.androidexpert2.core.data.source.local.entity.RecipeEntity
import com.example.androidexpert2.core.data.source.local.room.RecipeDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val recipesDao: RecipeDao) {

    fun getRecipes(): Flow<List<RecipeEntity>> = recipesDao.getRecipes()

    fun getRecipeById(id: Int): Flow<RecipeEntity> = recipesDao.getRecipeById(id)

    suspend fun insertRecipe(recipeList: List<RecipeEntity>) = recipesDao.insertRecipe(recipeList)

    fun getFavoriteRecipe(): Flow<List<RecipeEntity>> = recipesDao.getFavoriteRecipe()

    fun setFavoriteRecipe(recipe: RecipeEntity, newState: Boolean) {
        recipe.isFavorite = newState
        recipesDao.updateFavoriteRecipe(recipe)
    }

}










