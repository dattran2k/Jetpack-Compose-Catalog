@file:OptIn(ExperimentalFoundationApi::class)

package com.dat.ui.feature.catalog_compose.layout

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.staggeredgrid.LazyHorizontalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dat.core.model.ui.MyStaggeredGridCells
import com.dat.core.model.ui.MyVerticalArrangement
import com.dat.core.designsystem.component.MyBox
import com.dat.core.designsystem.component.SettingComponent
import com.dat.core.designsystem.component.ValueSlider
import com.dat.core.designsystem.theme.JetpackComposeCatalogTheme
import com.dat.core.designsystem.theme.getColorByIndex
import kotlin.random.Random

@Composable
fun LayoutGridLazyHorizontalStaggeredRoute(modifier: Modifier = Modifier) {
    LayoutGridLazyHorizontalStaggeredScreen(modifier)
}

@Composable
fun LayoutGridLazyHorizontalStaggeredScreen(
    modifier: Modifier = Modifier,
    viewModel: LayoutViewModel = hiltViewModel()
) {
    val itemCount by viewModel.itemCountState.collectAsState()
    val verticalArrangement by viewModel.verticalArrangementState.collectAsState()
    val staggeredGridCellsData by viewModel.staggeredGridCells.collectAsState()
    var horizontalItemSpacing by remember {
        mutableStateOf(0)
    }
    Column(modifier = modifier) {
        LazyHorizontalStaggeredGrid(
            modifier = Modifier.weight(1f),
            rows = staggeredGridCellsData.staggeredGridCells,
            horizontalItemSpacing = horizontalItemSpacing.dp,
            verticalArrangement = verticalArrangement.value
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
        ValueSlider(
            title = "HorizontalItemSpacing",
            value = horizontalItemSpacing,
            isProperties = true,
            from = 0,
            to = 100,
            onValueChange = {
                horizontalItemSpacing = it
            }
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
            settingSelected = staggeredGridCellsData.myStaggeredGridCells,
            listSetting = MyStaggeredGridCells.values().toList(),
            mapName = { it.typeName },
            onSettingSelected = viewModel::onSelectStaggeredGridCells
        )

        if (staggeredGridCellsData.staggeredGridCells is StaggeredGridCells.Adaptive)
            ValueSlider(
                title = "Adaptive",
                value = staggeredGridCellsData.value,
                isProperties = true,
                from = 40,
                to = 400,
                onValueChange = viewModel::updateStaggeredAdaptive
            )
        if (staggeredGridCellsData.staggeredGridCells is StaggeredGridCells.Fixed)
            ValueSlider(
                title = "Fix",
                value = staggeredGridCellsData.value,
                isProperties = true,
                from = 1,
                to = 10,
                onValueChange = viewModel::updateStaggeredFixed
            )
    }

}

@Preview
@Composable
fun GridLazyHorizontalStaggeredComposeScreenPreview() {
    JetpackComposeCatalogTheme(true) {
        LayoutGridLazyHorizontalStaggeredScreen(
            Modifier
                .fillMaxSize()
                .statusBarsPadding()
        )
    }
}