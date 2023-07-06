@file:OptIn(ExperimentalFoundationApi::class, ExperimentalFoundationApi::class)

package com.dat.jetpackcomposecatalog.presenstation.feature.catalog.catalog_compose.layout

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.staggeredgrid.LazyHorizontalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dat.jetpackcomposecatalog.core.common.DataConst.listStaggeredGridCells
import com.dat.jetpackcomposecatalog.core.common.DataConst.listVerticalArrangement
import com.dat.jetpackcomposecatalog.presenstation.theme.JetpackComposeCatalogTheme
import com.dat.jetpackcomposecatalog.presenstation.widget.EmptyBox
import com.dat.jetpackcomposecatalog.presenstation.widget.SettingComponent
import com.dat.jetpackcomposecatalog.presenstation.widget.ValueSlider
import kotlin.random.Random

@Composable
fun ColumnScope.GridLazyHorizontalStaggeredComposeScreen() {
    var verticalArrangement by remember {
        mutableStateOf(listVerticalArrangement.first())
    }
    var rowsState by remember {
        mutableStateOf(listStaggeredGridCells.first())
    }
    var itemCount by remember {
        mutableStateOf(100)
    }
    var adaptive by remember {
        mutableStateOf(100)
    }
    var horizontalItemSpacing by remember {
        mutableStateOf(10)
    }
    var fixed by remember {
        mutableStateOf(2)
    }
    val rows = if (rowsState.second == StaggeredGridCells.Adaptive::class.java)
        StaggeredGridCells.Adaptive(adaptive.dp)
    else
        StaggeredGridCells.Fixed(fixed)
    LazyHorizontalStaggeredGrid(
        modifier = Modifier
            .fillMaxWidth()
            .weight(1f)
            .background(color = MaterialTheme.colorScheme.secondary),
        rows = rows,
        horizontalItemSpacing = horizontalItemSpacing.dp,
        verticalArrangement = verticalArrangement.second
    ) {
        items(itemCount, key = { it }) {
            val size = Random.nextInt(50, 400).dp
            EmptyBox(modifier = Modifier.size(size)) {
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
        from = 0,
        to = 1000,
        onValueChange = {
            itemCount = it
        }
    )
    ValueSlider(
        title = "HorizontalItemSpacing",
        value = horizontalItemSpacing,
        isProperties = true,
        from = 20,
        to = 100,
        onValueChange = {
            horizontalItemSpacing = it
        }
    )
    SettingComponent(
        name = "horizontalArrangement",
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
        name = "columns",
        settingSelected = rowsState.first,
        listSetting = listStaggeredGridCells.map { it.first },
    ) { selected ->
        listStaggeredGridCells.find {
            selected == it.first
        }?.let {
            rowsState = it
        }
    }

    if (rowsState.second == StaggeredGridCells.Adaptive::class.java)
        ValueSlider(
            title = "Adaptive",
            value = adaptive,
            isProperties = true,
            from = 40,
            to = 400,
            onValueChange = {
                adaptive = it
            }
        )
    if (rowsState.second == StaggeredGridCells.Fixed::class.java)
        ValueSlider(
            title = "Fix",
            value = fixed,
            isProperties = true,
            from = 1,
            to = 10,
            onValueChange = {
                fixed = it
            }
        )
}

@Preview
@Composable
fun GridLazyHorizontalStaggeredComposeScreenPreview() {
    JetpackComposeCatalogTheme(true) {
        Column(
            Modifier
                .fillMaxSize()
                .statusBarsPadding()
        ) {
            GridLazyHorizontalStaggeredComposeScreen()
        }
    }
}