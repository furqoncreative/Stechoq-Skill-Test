package com.stechoq.todo.core.domain.usecase

import com.stechoq.todo.core.domain.repository.ITodosRepository

class TodosInteractor(private val repository: ITodosRepository) : TodosUseCase {

    override fun getAllRecipes() = repository.getAllTodos()

}