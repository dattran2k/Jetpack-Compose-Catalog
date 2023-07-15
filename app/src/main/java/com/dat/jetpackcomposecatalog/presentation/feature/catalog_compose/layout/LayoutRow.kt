package com.dat.jetpackcomposecatalog.presentation.feature.catalog_compose.layout

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.dat.jetpackcomposecatalog.data.model.catalog.MyHorizontalArrangement
import com.dat.jetpackcomposecatalog.data.model.catalog.MyVerticalAlignment
import com.dat.jetpackcomposecatalog.presentation.theme.JetpackComposeCatalogTheme
import com.dat.jetpackcomposecatalog.presentation.widget.EmptyBox
import com.dat.jetpackcomposecatalog.presentation.widget.SettingComponent


@Composable
fun LayoutRow(
    modifier: Modifier = Modifier,
    viewModel: LayoutViewModel = hiltViewModel()
) {
    val horizontalArrangement by viewModel.horizontalArrangementState.collectAsState()
    val verticalAlignment by viewModel.verticalAlignmentState.collectAsState()
    Row(
        modifier = modifier,
        verticalAlignment = verticalAlignment.value,
        horizontalArrangement = horizontalArrangement.value
    ) {
        EmptyBox()
        EmptyBox()
        EmptyBox()
    }

    // config
    SettingComponent(
        name = "verticalAlignment",
        settingSelected = verticalAlignment,
        listSetting = MyVerticalAlignment.values().toList(),
        mapName = { it.typeName },
        onSettingSelected = viewModel::onVerticalAlignmentSelected
    )
    SettingComponent(
        name = "horizontalArrangement",
        settingSelected = horizontalArrangement,
        listSetting = MyHorizontalArrangement.values().toList(),
        mapName = { it.typeName },
        onSettingSelected = viewModel::onHorizontalArrangementSelected
    )

}

@Preview
@Composable
fun RowComposeScreenPreview() {
    JetpackComposeCatalogTheme(true) {
        Box(Modifier.fillMaxSize()) {
            Column(
                Modifier
                    .fillMaxSize()
                    .statusBarsPadding()
                    .verticalScroll(rememberScrollState())
            ) {
                LayoutRow()
            }
        }
    }
}