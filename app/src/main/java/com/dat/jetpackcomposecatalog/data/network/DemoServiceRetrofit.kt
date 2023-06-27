package com.dat.jetpackcomposecatalog.data.network

import com.dat.jetpackcomposecatalog.data.model.TodoItem
import retrofit2.Response
import retrofit2.http.GET

interface DemoServiceRetrofit {
    @GET("/todos")
    suspend fun getTodos(): Response<List<TodoItem>>

}