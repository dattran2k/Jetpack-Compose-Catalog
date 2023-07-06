package com.dat.jetpackcomposecatalog.presenstation.feature.catalog.catalog_compose.layout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.dat.jetpackcomposecatalog.core.common.DataConst.listHorizontalAlignment
import com.dat.jetpackcomposecatalog.core.common.DataConst.listVerticalArrangement
import com.dat.jetpackcomposecatalog.presenstation.theme.JetpackComposeCatalogTheme
import com.dat.jetpackcomposecatalog.presenstation.widget.EmptyBox
import com.dat.jetpackcomposecatalog.presenstation.widget.SettingComponent

@Composable
fun ColumnScope.ColumnComposeScreen() {

    var verticalArrangement: Pair<String, Arrangement.Vertical> by remember {
        mutableStateOf(listVerticalArrangement.first())
    }
    var horizontalAlignment: Pair<String, Alignment.Horizontal> by remember {
        mutableStateOf(listHorizontalAlignment.first())
    }

    Column(
        Modifier
            .fillMaxWidth()
            .weight(1f)
            .background(color = MaterialTheme.colorScheme.secondary),
        verticalArrangement = verticalArrangement.second,
        horizontalAlignment = horizontalAlignment.second
    ) {
        EmptyBox()
        EmptyBox()
        EmptyBox()
    }

    // config
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
        listSetting = listHorizontalAlignment.map { it.first },
    ) { selected ->
        listHorizontalAlignment.find {
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