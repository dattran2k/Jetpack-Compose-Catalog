package com.dat.jetpackcomposecatalog.presentation.feature.catalog_compose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dat.jetpackcomposecatalog.data.model.catalog.MyHorizontalAlignment
import com.dat.jetpackcomposecatalog.data.model.catalog.MyVerticalArrangement
import com.dat.jetpackcomposecatalog.presentation.theme.JetpackComposeCatalogTheme
import com.dat.jetpackcomposecatalog.presentation.widget.EmptyBox
import com.dat.jetpackcomposecatalog.presentation.widget.SettingComponent

@Composable
fun LayoutColumn(
    modifier: Modifier = Modifier,
    catalogViewModel: CatalogViewModel = hiltViewModel()
) {
    val verticalArrangement by catalogViewModel.verticalArrangementState.collectAsStateWithLifecycle()
    val horizontalAlignment by catalogViewModel.horizontalAlignmentState.collectAsStateWithLifecycle()

    Column(
        modifier = modifier,
        verticalArrangement = verticalArrangement.value,
        horizontalAlignment = horizontalAlignment.value
    ) {
        EmptyBox()
        EmptyBox()
        EmptyBox()
    }

    // config
    SettingComponent(
        name = "verticalArrangement",
        settingSelected = verticalArrangement,
        listSetting = MyVerticalArrangement.values().toList(),
        mapName = { it.typeName },
        onSettingSelected = catalogViewModel::onVerticalArrangementSelected
    )
    SettingComponent(
        name = "horizontalAlignment",
        settingSelected = horizontalAlignment,
        listSetting = MyHorizontalAlignment.values().toList(),
        mapName = { it.typeName },
        onSettingSelected = catalogViewModel::onHorizontalAlignmentSelected
    )
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
                LayoutColumn()
            }
        }
    }
}