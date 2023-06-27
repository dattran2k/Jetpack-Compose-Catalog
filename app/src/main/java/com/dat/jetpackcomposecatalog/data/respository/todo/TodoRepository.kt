package com.dat.jetpackcomposecatalog.data.respository.todo


import com.dat.jetpackcomposecatalog.core.common.Resource
import com.dat.jetpackcomposecatalog.data.model.TodoItem
import kotlinx.coroutines.flow.Flow


interface TodoRepository {
    fun getTodos(): Flow<Resource<List<TodoItem>>>
}