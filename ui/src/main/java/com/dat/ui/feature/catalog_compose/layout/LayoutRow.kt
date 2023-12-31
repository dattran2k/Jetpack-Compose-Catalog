package com.dat.ui.feature.catalog_compose.layout

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.dat.core.model.ui.MyHorizontalArrangement
import com.dat.core.model.ui.MyVerticalAlignment
import com.dat.core.designsystem.component.MyBox
import com.dat.core.designsystem.component.SettingComponent
import com.dat.core.designsystem.theme.JetpackComposeCatalogTheme
import com.dat.core.designsystem.theme.getColorByIndex

@Composable
fun LayoutRowRoute(modifier: Modifier = Modifier) {
    LayoutRowScreen(modifier)
}

@Composable
fun LayoutRowScreen(
    modifier: Modifier = Modifier,
    viewModel: LayoutViewModel = hiltViewModel()
) {
    val horizontalArrangement by viewModel.horizontalArrangementState.collectAsState()
    val verticalAlignment by viewModel.verticalAlignmentState.collectAsState()
    Column(modifier = modifier) {
        Row(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            verticalAlignment = verticalAlignment.value,
            horizontalArrangement = horizontalArrangement.value
        ) {
            MyBox(color = getColorByIndex(0))
            MyBox(color = getColorByIndex(1))
            MyBox(color = getColorByIndex(2))
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
}

@Preview
@Composable
fun RowComposeScreenPreview() {
    JetpackComposeCatalogTheme(true) {
        LayoutRowScreen(
            Modifier
                .fillMaxSize()
                .statusBarsPadding()
        )
    }
}