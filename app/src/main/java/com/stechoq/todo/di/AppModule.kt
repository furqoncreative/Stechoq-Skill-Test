package com.stechoq.todo.di

import com.stechoq.todo.core.domain.usecase.TodosInteractor
import com.stechoq.todo.core.domain.usecase.TodosUseCase
import com.stechoq.todo.todolist.TodoListViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<TodosUseCase> { TodosInteractor(get()) }
}

val viewModelModule = module {
    viewModel { TodoListViewModel(get()) }
}