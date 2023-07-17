@file:OptIn(ExperimentalFoundationApi::class)

package com.dat.jetpackcomposecatalog.presentation.feature.catalog_compose.layout

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
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
import com.dat.jetpackcomposecatalog.data.model.catalog.MyHorizontalArrangement
import com.dat.jetpackcomposecatalog.data.model.catalog.MyStaggeredGridCells
import com.dat.jetpackcomposecatalog.presentation.theme.JetpackComposeCatalogTheme
import com.dat.jetpackcomposecatalog.presentation.theme.getColorByIndex
import com.dat.jetpackcomposecatalog.presentation.widget.MyBox
import com.dat.jetpackcomposecatalog.presentation.widget.SettingComponent
import com.dat.jetpackcomposecatalog.presentation.widget.ValueSlider
import kotlin.random.Random

@Composable
fun LayoutGridLazyVerticalStaggered(
    modifier: Modifier = Modifier,
    viewModel: LayoutViewModel = hiltViewModel()
) {

    val itemCount by viewModel.itemCountState.collectAsState()
    val horizontalArrangement by viewModel.horizontalArrangementState.collectAsState()
    val staggeredGridCellsData by viewModel.staggeredGridCells.collectAsState()

    var verticalItemSpacing by remember {
        mutableStateOf(0)
    }
    LazyVerticalStaggeredGrid(
        modifier = modifier,
        columns = staggeredGridCellsData.staggeredGridCells,
        verticalItemSpacing = verticalItemSpacing.dp,
        horizontalArrangement = horizontalArrangement.value,
    ) {
        items(itemCount, key = { it }) {
            val size = Random.nextInt(50, 400).dp
            val color = getColorByIndex(it % 6)
            MyBox(modifier = Modifier.size(size), color) {
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
        title = "verticalItemSpacing",
        value = verticalItemSpacing,
        isProperties = true,
        from = 0,
        to = 100,
        onValueChange = {
            verticalItemSpacing = it
        }
    )
    SettingComponent(
        name = "horizontalArrangement",
        settingSelected = horizontalArrangement,
        listSetting = MyHorizontalArrangement.values().toList(),
        mapName = { it.typeName },
        onSettingSelected = viewModel::onHorizontalArrangementSelected
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

@Preview
@Composable
fun GridLazyVerticalStaggeredComposeScreenPreview() {
    JetpackComposeCatalogTheme(true) {
        Column(
            Modifier
                .fillMaxSize()
                .statusBarsPadding()
        ) {
            LayoutGridLazyVerticalStaggered()
        }
    }
}