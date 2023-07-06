package com.dat.jetpackcomposecatalog.presenstation.feature.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.dat.jetpackcomposecatalog.presenstation.navigation.Screen
import com.dat.jetpackcomposecatalog.presenstation.theme.LocalCustomColorTheme

@Composable
internal fun DetailRoute(onClickNavigate: () -> Unit, detailScreenArg: Screen.DetailScreen.DetailScreenArg) {
    DetailScreen(onClickNavigate, detailScreenArg)
}

@Composable
fun DetailScreen(onClickNavigate: () -> Unit, detailScreenArg: Screen.DetailScreen.DetailScreenArg) {
    Column(
        Modifier
            .fillMaxSize()
            .background(LocalCustomColorTheme.current.background),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    )
    {
        Text(
            text = "Detail Screen, arg1 = ${detailScreenArg.id}, arg2 = ${detailScreenArg.title}",
            color = LocalCustomColorTheme.current.textTitle
        )
        Button(onClick = onClickNavigate) {
            Text(text = "Click here to navigate")
        }
    }
}