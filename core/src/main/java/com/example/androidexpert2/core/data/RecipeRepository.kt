package com.example.androidexpert2.core.data

import com.example.androidexpert2.core.data.source.local.LocalDataSource
import com.example.androidexpert2.core.data.source.remote.RemoteDataSource
import com.example.androidexpert2.core.data.source.remote.network.ApiResponse
import com.example.androidexpert2.core.data.source.remote.response.RecipeResponses
import com.example.androidexpert2.core.domain.model.Recipe
import com.example.androidexpert2.core.domain.repository.IRecipeRepository
import com.example.androidexpert2.core.utils.AppExecutors
import com.example.androidexpert2.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RecipeRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
): IRecipeRepository{
    override fun getRecipes(): Flow<Result<List<Recipe>>> =
        object : NetworkBoundResource<List<Recipe>, List<RecipeResponses>>() {
            override fun loaddFromDB(): Flow<List<Recipe>> {
                return localDataSource.getRecipes().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override suspend fun createeCall(): Flow<ApiResponse<List<RecipeResponses>>> =
                remoteDataSource.getRecipes()

            override suspend fun saveCalllResult(data: List<RecipeResponses>) {
                val recipeList = DataMapper.mapResponsesToEntities(data)
                localDataSource.insertRecipe(recipeList)
            }

            override fun shoulddFetch(data: List<Recipe>?): Boolean =
                data.isNullOrEmpty()

        }.asFlow()

    override fun getDetailRecipe(id: Int): Flow<Result<Recipe>> {
        return object : NetworkOnlyResource<Recipe, RecipeResponses>() {
            override fun loaddFromNetwork(data: RecipeResponses): Flow<Recipe> {
                return DataMapper.mapResponseToDomain(data)
            }

            override suspend fun createeCall(): Flow<ApiResponse<RecipeResponses>> {
                return remoteDataSource.getDetailRecipes(id)
            }

        }.asFlow()
    }

    override fun getRecipeById(id: Int): Flow<Recipe> {
        return localDataSource.getRecipeById(id).map {
            DataMapper.mapEntityToDomain(it)
        }
    }

    override fun getFavoriteRecipe(): Flow<List<Recipe>> {
        return localDataSource.getFavoriteRecipe().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteRecipe(recipe: Recipe, state: Boolean) {
        val recipeEntity = DataMapper.mapDomainToEntity(recipe)
        appExecutors.diskIO().execute {
            localDataSource.setFavoriteRecipe(recipeEntity, state)
        }
    }

}











