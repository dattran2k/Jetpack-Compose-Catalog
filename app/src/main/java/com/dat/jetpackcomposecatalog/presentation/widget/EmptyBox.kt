package com.dat.jetpackcomposecatalog.presentation.widget

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun EmptyBox(modifier: Modifier = Modifier, content: @Composable () -> Unit = {}) {

    Card(
        modifier = modifier
            .padding(2.dp)
            .size(60.dp),
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
    ) {
        content.invoke()
    }
}