package com.stechoq.todo.core.domain.usecase


import com.stechoq.todo.core.data.Resource
import com.stechoq.todo.core.domain.model.Todo
import kotlinx.coroutines.flow.Flow

interface TodosUseCase {
    fun getAllTodos(): Flow<Resource<List<Todo>>>
}