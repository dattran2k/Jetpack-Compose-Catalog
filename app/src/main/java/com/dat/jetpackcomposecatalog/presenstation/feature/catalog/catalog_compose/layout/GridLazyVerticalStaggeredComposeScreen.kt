@file:OptIn(ExperimentalFoundationApi::class, ExperimentalFoundationApi::class)

package com.dat.jetpackcomposecatalog.presenstation.feature.catalog.catalog_compose.layout

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
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
import com.dat.jetpackcomposecatalog.core.common.DataConst.listHorizontalArrangement
import com.dat.jetpackcomposecatalog.core.common.DataConst.listStaggeredGridCells
import com.dat.jetpackcomposecatalog.presenstation.theme.JetpackComposeCatalogTheme
import com.dat.jetpackcomposecatalog.presenstation.widget.EmptyBox
import com.dat.jetpackcomposecatalog.presenstation.widget.SettingComponent
import com.dat.jetpackcomposecatalog.presenstation.widget.ValueSlider
import kotlin.random.Random

@Composable
fun ColumnScope.GridLazyVerticalStaggeredComposeScreen() {
    var horizontalArrangement: Pair<String, Arrangement.Horizontal> by remember {
        mutableStateOf(listHorizontalArrangement.first())
    }
    var columns by remember {
        mutableStateOf(listStaggeredGridCells.first())
    }
    var itemCount by remember {
        mutableStateOf(100)
    }
    var adaptive by remember {
        mutableStateOf(100)
    }
    var verticalItemSpacing by remember {
        mutableStateOf(10)
    }
    var fixed by remember {
        mutableStateOf(2)
    }
    val columnsResult = if (columns.second == StaggeredGridCells.Adaptive::class.java)
        StaggeredGridCells.Adaptive(adaptive.dp)
    else
        StaggeredGridCells.Fixed(fixed)
    LazyVerticalStaggeredGrid(
        modifier = Modifier
            .fillMaxWidth()
            .weight(1f)
            .background(color = MaterialTheme.colorScheme.secondary),
        columns = columnsResult,
        verticalItemSpacing = verticalItemSpacing.dp,
        horizontalArrangement = horizontalArrangement.second
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
    ValueSlider(
        title = "Item count :",
        value = itemCount.toFloat(),
        from = 0f,
        to = 1000f,
        onValueChange = {
            itemCount = it.toInt()
        }
    )
    ValueSlider(
        title = "verticalItemSpacing",
        value = verticalItemSpacing.toFloat(),
        isProperties = true,
        from = 20f,
        to = 100f,
        onValueChange = {
            verticalItemSpacing = it.toInt()
        }
    )
    SettingComponent(
        name = "horizontalArrangement",
        settingSelected = horizontalArrangement.first,
        listSetting = listHorizontalArrangement.map { it.first },
    ) { selected ->
        listHorizontalArrangement.find {
            selected == it.first
        }?.let {
            horizontalArrangement = it
        }
    }
    GroupConfigChange(
        adaptive = adaptive,
        adaptiveChange = {
            adaptive = it.toInt()
        },
        fixed = fixed,
        fixedChange = {
            fixed = it.toInt()
        },
        columns = columns,
        columnsUpdate = {
            columns = it
        })

}

@Composable
fun GroupConfigChange(
    adaptive: Int,
    adaptiveChange: (Float) -> Unit,
    fixed: Int,
    fixedChange: (Float) -> Unit,
    columns: Pair<String, Class<out StaggeredGridCells>>,
    columnsUpdate: (Pair<String, Class<out StaggeredGridCells>>) -> Unit
) {

    SettingComponent(
        name = "columns",
        settingSelected = columns.first,
        listSetting = listStaggeredGridCells.map { it.first },
    ) { selected ->
        listStaggeredGridCells.find {
            selected == it.first
        }?.let {
            columnsUpdate(it)

        }
    }
    if (columns.second == StaggeredGridCells.Adaptive::class.java)
        ValueSlider(
            title = "Adaptive",
            value = adaptive.toFloat(),
            isProperties = true,
            from = 40f,
            to = 400f,
            onValueChange = adaptiveChange
        )
    if (columns.second == StaggeredGridCells.Fixed::class.java)
        ValueSlider(
            title = "Fix",
            value = fixed.toFloat(),
            isProperties = true,
            from = 1f,
            to = 10f,
            onValueChange = fixedChange
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
            GridLazyVerticalStaggeredComposeScreen()
        }
    }
}