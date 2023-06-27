package com.dat.jetpackcomposecatalog.presenstation.view.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.dat.jetpackcomposecatalog.presenstation.navigation.DetailScreenRoute
import com.dat.jetpackcomposecatalog.presenstation.theme.LocalCustomColorTheme

@Composable
internal fun DetailRoute(onClickNavigate: () -> Unit, detailScreenArg: DetailScreenRoute.DetailScreenArg) {
    DetailScreen(onClickNavigate, detailScreenArg)
}

@Composable
fun DetailScreen(onClickNavigate: () -> Unit, detailScreenArg: DetailScreenRoute.DetailScreenArg) {
    Column(
        Modifier
            .fillMaxSize()
            .background(LocalCustomColorTheme.current.backGround),
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
