package com.stechoq.todo.core.data.source.remote.network


import com.stechoq.todo.core.data.source.remote.response.TodoResponse
import retrofit2.http.GET

interface ApiService {

    @GET("/todos")
    suspend fun getAllTodos(): List<TodoResponse>

}
