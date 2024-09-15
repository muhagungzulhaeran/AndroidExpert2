package com.example.androidexpert1.core.data

import com.example.androidexpert1.core.data.source.remote.network.ApiResponse
import kotlinx.coroutines.flow.*

abstract class NetworkOnlyResource<ResultType, RequestType> {

    private val result: Flow<Result<ResultType>> = flow {
        emit(Result.Loading())
        when (val apiResponse = createeCall().first()) {
            is ApiResponse.Success -> {
                emitAll(loaddFromNetwork(apiResponse.data).map {
                    Result.Success(it)
                })
            }

            is ApiResponse.Error -> {
                emit(Result.Error(apiResponse.errorMessage))
            }
            else -> Unit
        }
    }

    protected abstract fun loaddFromNetwork(data: RequestType): Flow<ResultType>

    protected abstract suspend fun createeCall(): Flow<ApiResponse<RequestType>>

    fun asFlow(): Flow<Result<ResultType>> = result

}










