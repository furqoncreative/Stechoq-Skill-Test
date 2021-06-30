package com.stechoq.todo.todolist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.stechoq.todo.core.domain.usecase.TodosUseCase

class TodoListViewModel(
    todosUseCase: TodosUseCase
) : ViewModel() {
    val todos = todosUseCase.getAllTodos().asLiveData()
}
