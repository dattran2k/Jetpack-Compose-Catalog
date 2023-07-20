package com.dat.jetpackcomposecatalog.feature.catalog_compose.layout

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
import com.dat.jetpackcomposecatalog.common.model.MyHorizontalAlignment
import com.dat.jetpackcomposecatalog.common.model.MyVerticalArrangement
import com.dat.jetpackcomposecatalog.theme.JetpackComposeCatalogTheme
import com.dat.jetpackcomposecatalog.theme.getColorByIndex
import com.dat.jetpackcomposecatalog.widget.MyBox
import com.dat.jetpackcomposecatalog.widget.SettingComponent


@Composable
fun LayoutColumn(
    modifier: Modifier = Modifier,
    layoutViewModel: LayoutViewModel = hiltViewModel()
) {
    val verticalArrangement by layoutViewModel.verticalArrangementState.collectAsStateWithLifecycle()
    val horizontalAlignment by layoutViewModel.horizontalAlignmentState.collectAsStateWithLifecycle()

    Column(
        modifier = modifier,
        verticalArrangement = verticalArrangement.value,
        horizontalAlignment = horizontalAlignment.value
    ) {
        MyBox(color = getColorByIndex(0))
        MyBox(color = getColorByIndex(1))
        MyBox(color = getColorByIndex(2))
    }

    // config
    SettingComponent(
        name = "verticalArrangement",
        settingSelected = verticalArrangement,
        listSetting = MyVerticalArrangement.values().toList(),
        mapName = { it.typeName },
        onSettingSelected = layoutViewModel::onVerticalArrangementSelected
    )
    SettingComponent(
        name = "horizontalAlignment",
        settingSelected = horizontalAlignment,
        listSetting = MyHorizontalAlignment.values().toList(),
        mapName = { it.typeName },
        onSettingSelected = layoutViewModel::onHorizontalAlignmentSelected
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