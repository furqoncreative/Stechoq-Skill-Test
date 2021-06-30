package com.stechoq.todo.core.data.source.local

import com.stechoq.todo.core.data.source.local.entity.TodoEntity
import com.stechoq.todo.core.data.source.local.room.TodoDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource(
    private val todoDao: TodoDao
) {

    fun getAllTodos(): Flow<List<TodoEntity>> = todoDao.getAllTodos()

    fun getTodo(param: Int): Flow<TodoEntity> = todoDao.getTodo(param)

    suspend fun insertTodos(todos: List<TodoEntity>) =
        todoDao.insertAllTodos(todos)

}