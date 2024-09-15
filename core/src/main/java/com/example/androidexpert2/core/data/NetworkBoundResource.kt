package com.example.androidexpert2.core.data

import com.example.androidexpert2.core.data.source.remote.network.ApiResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

abstract class NetworkBoundResource<ResultType, RequestType> {

    private var result: Flow<Result<ResultType>> = flow {
        emit(Result.Loading())
        val dbSource = loaddFromDB().first()
        if (shoulddFetch(dbSource)) {
            emit(Result.Loading())
            when (val apiResponse = createeCall().first()) {
                is ApiResponse.Success -> {
                    saveCalllResult(apiResponse.data)
                    emitAll(loaddFromDB().map {
                        Result.Success(it)
                    })
                }

                is ApiResponse.Empty -> {
                    emitAll(loaddFromDB().map {
                        Result.Success(it)
                    })
                }

                is ApiResponse.Error -> {
                    onFetchFailedd()
                    emit(Result.Error<ResultType>(apiResponse.errorMessage))
                }
            }
        } else {
            emitAll(loaddFromDB().map {
                Result.Success(it)
            })
        }
    }

    protected open fun onFetchFailedd() {}

    protected abstract fun loaddFromDB(): Flow<ResultType>

    protected abstract fun shoulddFetch(data: ResultType?): Boolean

    protected abstract suspend fun createeCall(): Flow<ApiResponse<RequestType>>

    protected abstract suspend fun saveCalllResult(data: RequestType)

    fun asFlow(): Flow<Result<ResultType>> = result

}










