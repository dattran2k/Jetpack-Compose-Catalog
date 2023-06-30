@file:OptIn(ExperimentalLayoutApi::class)

package com.dat.jetpackcomposecatalog.presenstation.feature.catalog.catalog_compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
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
import com.dat.jetpackcomposecatalog.core.designsystem.component.SettingComponent
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

    var verticalArrangement: Pair<String, Arrangement.Vertical> by remember {
        mutableStateOf(listVerticalArrangement.first())
    }
    var horizontalAlignment: Pair<String, Alignment.Horizontal> by remember {
        mutableStateOf(listHorizontalArrangement.first())
    }
    val boxModifier = Modifier
        .padding(2.dp)
        .size(60.dp)
        .background(Color.Red)
    Column(
        Modifier
            .fillMaxWidth()
            .height(400.dp)
            .background(color = MaterialTheme.colors.secondary),
        verticalArrangement = verticalArrangement.second,
        horizontalAlignment = horizontalAlignment.second
    ) {
        Box(boxModifier) {}
        Box(boxModifier) {}
        Box(boxModifier) {}
    }
    SettingComponent(
        name = "verticalArrangement",
        settingSelected = verticalArrangement.first,
        listSetting = listVerticalArrangement.map { it.first },
    ) { selected ->
        listVerticalArrangement.find {
            selected == it.first
        }?.let {
            verticalArrangement = it
        }
    }
    SettingComponent(
        name = "horizontalAlignment",
        settingSelected = horizontalAlignment.first,
        listSetting = listHorizontalArrangement.map { it.first },
    ) { selected ->
        listHorizontalArrangement.find {
            selected == it.first
        }?.let {
            horizontalAlignment = it
        }
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