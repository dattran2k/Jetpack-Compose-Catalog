package com.dat.jetpackcomposecatalog.data.network

import javax.inject.Inject


// free public api : https://jsonplaceholder.typicode.com/
class ApiDataSource @Inject constructor(
    private val demoServiceRetrofit: DemoServiceRetrofit,
) {
    suspend fun getTodos( ) = demoServiceRetrofit.getTodos()
}

