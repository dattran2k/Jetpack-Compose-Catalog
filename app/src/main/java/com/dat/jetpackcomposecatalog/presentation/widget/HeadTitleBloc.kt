package com.dat.jetpackcomposecatalog.presentation.widget

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.dat.jetpackcomposecatalog.presentation.theme.TextHeadBloc

@Composable
fun HeadTitleBloc(title: String) {
    Text(text = title, style = TextHeadBloc)
    Divider(Modifier.padding(vertical = 8.dp))
}