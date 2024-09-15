package com.example.androidexpert2.core.data.source.remote.network

import com.example.androidexpert2.core.BuildConfig
import com.example.androidexpert2.core.data.source.remote.response.ListRecipesResponse
import com.example.androidexpert2.core.data.source.remote.response.RecipeResponses
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("complexSearch")
    suspend fun getRecipes(
        @Query("apiKey") apiKey: String = BuildConfig.API_KEY,
        @Query("addRecipeInformation") addRecipeInformation: String = "true",
        @Query("fillIngredients") fillIngredients: String = "true"
    ): ListRecipesResponse

    @GET("{id}/information")
    suspend fun getDetailRecipes(
        @Path("id") id: Int,
        @Query("apiKey")apiKey: String = BuildConfig.API_KEY
    ): RecipeResponses
}