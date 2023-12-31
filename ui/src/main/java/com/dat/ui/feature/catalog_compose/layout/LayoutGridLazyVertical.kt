package com.dat.ui.feature.catalog_compose.layout

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dat.core.model.ui.MyGridCells
import com.dat.core.model.ui.MyHorizontalArrangement
import com.dat.core.model.ui.MyVerticalArrangement
import com.dat.core.designsystem.component.MyBox
import com.dat.core.designsystem.component.SettingComponent
import com.dat.core.designsystem.component.ValueSlider
import com.dat.core.designsystem.theme.JetpackComposeCatalogTheme
import com.dat.core.designsystem.theme.getColorByIndex
import kotlin.random.Random

@Composable
fun LayoutGridLazyVerticalRoute(modifier: Modifier = Modifier) {
    LayoutGridLazyVerticalScreen(modifier)
}

@Composable
fun LayoutGridLazyVerticalScreen(
    modifier: Modifier = Modifier,
    viewModel: LayoutViewModel = hiltViewModel()
) {
    val itemCount by viewModel.itemCountState.collectAsState()
    val verticalArrangement by viewModel.verticalArrangementState.collectAsState()
    val horizontalArrangement by viewModel.horizontalArrangementState.collectAsState()

    val gridCellsData by viewModel.gridCells.collectAsState()
    Column(modifier = modifier) {
        LazyVerticalGrid(
            modifier = Modifier.weight(1f),
            columns = gridCellsData.gridCells,
            verticalArrangement = verticalArrangement.value,
            horizontalArrangement = horizontalArrangement.value
        ) {
            items(itemCount, key = { it }) {
                val size = Random.nextInt(50, 400).dp
                val color = getColorByIndex(it % 6)
                MyBox(modifier = Modifier.size(size), useItemImage = false, color = color) {
                    Text(
                        text = "index $it, size $size",
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Center
                    )
                }
            }
        }

        // config
        ValueSlider(
            title = "Item count :",
            value = itemCount,
            from = 2,
            to = 100,
            onValueChange = viewModel::onUpdateItemCount
        )
        SettingComponent(
            name = "horizontalArrangement",
            settingSelected = horizontalArrangement,
            listSetting = MyHorizontalArrangement.values().toList(),
            mapName = { it.typeName },
            onSettingSelected = viewModel::onHorizontalArrangementSelected
        )
        SettingComponent(
            name = "verticalArrangement",
            settingSelected = verticalArrangement,
            listSetting = MyVerticalArrangement.values().toList(),
            mapName = { it.typeName },
            onSettingSelected = viewModel::onVerticalArrangementSelected
        )

        // TODO improve this
        SettingComponent(
            name = "columns",
            settingSelected = gridCellsData.myGridCells,
            listSetting = MyGridCells.values().toList(),
            mapName = { it.typeName },
            onSettingSelected = viewModel::onSelectGridCells
        )

        if (gridCellsData.gridCells is GridCells.Adaptive)
            ValueSlider(
                title = "Adaptive",
                value = gridCellsData.value,
                isProperties = true,
                from = 40,
                to = 400,
                onValueChange = viewModel::updateAdaptive
            )
        if (gridCellsData.gridCells is GridCells.Fixed)
            ValueSlider(
                title = "Fix",
                value = gridCellsData.value,
                isProperties = true,
                from = 1,
                to = 10,
                onValueChange = viewModel::updateFixed
            )
    }
}

@Preview
@Composable
fun GridLazyVerticalComposeScreenPreview() {
    JetpackComposeCatalogTheme(true) {
        LayoutGridLazyVerticalScreen(
            Modifier
                .fillMaxSize()
                .statusBarsPadding()
        )
    }
}