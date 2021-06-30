package com.stechoq.todo.core.data.source.remote

import com.stechoq.todo.core.data.source.remote.network.ApiResponse
import com.stechoq.todo.core.data.source.remote.network.ApiService
import com.stechoq.todo.core.data.source.remote.response.TodoResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getAllTodos(): Flow<ApiResponse<List<TodoResponse>>> {
        return flow {
            try {
                val response = apiService.getAllTodos()
                if (response.isNotEmpty()) {
                    emit(ApiResponse.Success(response))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }


}

