package com.stechoq.todo.core.data.repository

import com.stechoq.todo.core.data.NetworkBoundResource
import com.stechoq.todo.core.data.Resource
import com.stechoq.todo.core.data.source.local.LocalDataSource
import com.stechoq.todo.core.data.source.remote.RemoteDataSource
import com.stechoq.todo.core.data.source.remote.network.ApiResponse
import com.stechoq.todo.core.data.source.remote.response.TodoResponse
import com.stechoq.todo.core.domain.model.Todo
import com.stechoq.todo.core.domain.repository.ITodosRepository
import com.stechoq.todo.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TodosRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : ITodosRepository {

    override fun getAllTodos(): Flow<Resource<List<Todo>>> =
        object : NetworkBoundResource<List<Todo>, List<TodoResponse>>() {
            override fun loadFromDB(): Flow<List<Todo>> {
                return localDataSource.getAllTodos().map {
                    DataMapper.mapTodoEntityToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Todo>?): Boolean = data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<TodoResponse>>> =
                remoteDataSource.getAllTodos()

            override suspend fun saveCallResult(data: List<TodoResponse>) {
                val todos = DataMapper.mapTodoResponsesToEntities(data)
                localDataSource.insertTodos(todos)
            }
        }.asFlow()

}

