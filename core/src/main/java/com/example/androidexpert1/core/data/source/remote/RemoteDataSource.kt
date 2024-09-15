package com.example.androidexpert1.core.data.source.remote

import android.util.Log
import com.example.androidexpert1.core.data.source.remote.network.ApiResponse
import com.example.androidexpert1.core.data.source.remote.network.ApiService
import com.example.androidexpert1.core.data.source.remote.response.RecipeResponses
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService){
    suspend fun getRecipes(): Flow<ApiResponse<List<RecipeResponses>>> {
        return flow {
            try {
                val response = apiService.getRecipes()
                val dataArray = response.results
                if (dataArray.isNotEmpty()) {
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getDetailRecipes(id: Int): Flow<ApiResponse<RecipeResponses>> {
        return flow {
            try {
                val response = apiService.getDetailRecipes(id)
                if (!response.equals("")) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }

}








