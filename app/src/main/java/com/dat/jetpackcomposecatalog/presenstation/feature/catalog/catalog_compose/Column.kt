package com.dat.jetpackcomposecatalog.presenstation.feature.catalog.catalog_compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dat.jetpackcomposecatalog.presenstation.theme.JetpackComposeCatalogTheme

@Composable
fun ColumnComposeRoute() {

}

val listVerticalArrangement: List<Pair<String, Arrangement.Vertical>> = listOf(
    "Arrangement.Top" to Arrangement.Top,
    "Arrangement.Center" to Arrangement.Center,
    "Arrangement.Bottom" to Arrangement.Bottom,
    "Arrangement.SpaceEvenly" to Arrangement.SpaceEvenly,
    "Arrangement.SpaceAround" to Arrangement.SpaceAround,
    "Arrangement.SpaceBetween" to Arrangement.SpaceBetween,
)

val listHorizontalArrangement: List<Pair<String, Alignment.Horizontal>> = listOf(
    "Alignment.Start" to Alignment.Start,
    "Alignment.End" to Alignment.End,
    "Alignment.CenterHorizontally" to Alignment.CenterHorizontally,
)

@Composable
fun ColumnComposeScreen() {

    var verticalArrangement: Arrangement.Vertical by remember {
        mutableStateOf(Arrangement.Top)
    }
    var horizontalAlignment: Alignment.Horizontal by remember {
        mutableStateOf(Alignment.Start)
    }
    val boxModifier = Modifier
        .padding(2.dp)
        .size(80.dp)
        .background(Color.Red)
    Column(
        Modifier
            .fillMaxWidth()
            .height(400.dp)
            .background(color = MaterialTheme.colors.secondary),
        verticalArrangement = verticalArrangement,
        horizontalAlignment = horizontalAlignment
    ) {
        Box(boxModifier) {

        }
        Box(boxModifier) {

        }
    }
    listVerticalArrangement.forEach {
        Text(text = it.first)
    }
    listHorizontalArrangement.forEach {
        Text(text = it.first)
    }
    listVerticalArrangement.forEach {
        Text(text = it.first)
    }
    listHorizontalArrangement.forEach {
        Text(text = it.first)
    }
}

@Preview
@Composable
fun ColumnComposeScreenPreview() {
    JetpackComposeCatalogTheme(true) {
        Box(Modifier.fillMaxSize()) {
            Column(
                Modifier
                    .fillMaxSize()
                    .statusBarsPadding()
                    .verticalScroll(rememberScrollState())
            ) {
                ColumnComposeScreen()
            }
        }
    }
}