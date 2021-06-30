package com.stechoq.todo.core.domain.repository

import com.stechoq.todo.core.data.Resource
import com.stechoq.todo.core.domain.model.Todo
import kotlinx.coroutines.flow.Flow

interface ITodosRepository {

    fun getAllTodos(): Flow<Resource<List<Todo>>>

}