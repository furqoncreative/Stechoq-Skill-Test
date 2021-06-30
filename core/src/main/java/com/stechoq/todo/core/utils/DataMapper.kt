package com.stechoq.todo.core.utils

import com.stechoq.todo.core.data.source.local.entity.TodoEntity
import com.stechoq.todo.core.data.source.remote.response.TodoResponse
import com.stechoq.todo.core.domain.model.Todo


object DataMapper {
    fun mapTodoResponsesToEntities(input: List<TodoResponse>): List<TodoEntity> {
        val todos = ArrayList<TodoEntity>()
        input.map {
            val todo = TodoEntity(
                id = it.id,
                completed = it.completed,
                title = it.title,
                userId = it.userId
            )
            todos.add(todo)
        }
        return todos
    }

    fun mapTodoEntityToDomain(input: List<TodoEntity>): List<Todo> =
        input.map {
            Todo(
                id = it.id,
                completed = it.completed,
                title = it.title,
                userId = it.userId
            )
        }


}
