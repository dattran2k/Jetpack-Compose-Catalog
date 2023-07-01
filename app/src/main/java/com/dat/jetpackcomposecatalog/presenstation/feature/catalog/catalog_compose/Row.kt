@file:OptIn(ExperimentalLayoutApi::class)

package com.dat.jetpackcomposecatalog.presenstation.feature.catalog.catalog_compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dat.jetpackcomposecatalog.core.common.DataConst.listHorizontalArrangement
import com.dat.jetpackcomposecatalog.core.common.DataConst.listVerticalAlignment
import com.dat.jetpackcomposecatalog.core.designsystem.component.EmptyBox
import com.dat.jetpackcomposecatalog.core.designsystem.component.SettingComponent
import com.dat.jetpackcomposecatalog.presenstation.theme.JetpackComposeCatalogTheme


@Composable
fun RowComposeScreen() {

    var horizontalArrangement: Pair<String, Arrangement.Horizontal> by remember {
        mutableStateOf(listHorizontalArrangement.first())
    }
    var verticalAlignment: Pair<String, Alignment.Vertical> by remember {
        mutableStateOf(listVerticalAlignment.first())
    }
    Row(
        Modifier
            .fillMaxWidth()
            .height(400.dp)
            .background(color = MaterialTheme.colors.secondary),
        verticalAlignment = verticalAlignment.second,
        horizontalArrangement = horizontalArrangement.second
    ) {
        EmptyBox()
        EmptyBox()
        EmptyBox()
    }
    SettingComponent(
        name = "verticalArrangement",
        settingSelected = horizontalArrangement.first,
        listSetting = listHorizontalArrangement.map { it.first },
    ) { selected ->
        listHorizontalArrangement.find {
            selected == it.first
        }?.let {
            horizontalArrangement = it
        }
    }
    SettingComponent(
        name = "horizontalAlignment",
        settingSelected = verticalAlignment.first,
        listSetting = listVerticalAlignment.map { it.first },
    ) { selected ->
        listVerticalAlignment.find {
            selected == it.first
        }?.let {
            verticalAlignment = it
        }
    }

}

@Preview
@Composable
fun RowComposeScreenPreview() {
    JetpackComposeCatalogTheme(true) {
        Box(Modifier.fillMaxSize()) {
            Row(
                Modifier
                    .fillMaxSize()
                    .statusBarsPadding()
                    .verticalScroll(rememberScrollState())
            ) {
                RowComposeScreen()
            }
        }
    }
}