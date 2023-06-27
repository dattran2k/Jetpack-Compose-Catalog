package com.dat.jetpackcomposecatalog.presenstation.view.main.home

import androidx.compose.runtime.Immutable
import com.dat.jetpackcomposecatalog.data.model.TodoItem

@Immutable
data class HomeUIState(
    val listTodoItem: List<TodoItem> = listOf(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val isEndReached : Boolean = false
)