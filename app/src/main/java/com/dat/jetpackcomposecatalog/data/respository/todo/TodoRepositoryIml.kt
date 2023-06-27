package com.dat.jetpackcomposecatalog.data.respository.todo

import com.dat.jetpackcomposecatalog.core.common.Resource
import com.dat.jetpackcomposecatalog.core.common.flowSafeApiCall
import com.dat.jetpackcomposecatalog.core.di.Dispatcher
import com.dat.jetpackcomposecatalog.core.di.MyDispatchers
import com.dat.jetpackcomposecatalog.data.model.TodoItem
import com.dat.jetpackcomposecatalog.data.network.ApiDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class TodoRepositoryIml @Inject constructor(
    private val apiDataSource: ApiDataSource,
    @Dispatcher(MyDispatchers.IO) private val dispatcher: CoroutineDispatcher,
) : TodoRepository {
    override fun getTodos(): Flow<Resource<List<TodoItem>>> {
        return flowSafeApiCall(dispatcher) {
            apiDataSource.getTodos()
        }
    }
}
