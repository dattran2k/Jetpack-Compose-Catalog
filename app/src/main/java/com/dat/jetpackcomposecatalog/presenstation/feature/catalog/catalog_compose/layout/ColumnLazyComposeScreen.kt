@file:OptIn(ExperimentalLayoutApi::class)

package com.dat.jetpackcomposecatalog.presenstation.feature.catalog.catalog_compose.layout

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dat.jetpackcomposecatalog.core.common.DataConst.listHorizontalAlignment
import com.dat.jetpackcomposecatalog.core.common.DataConst.listVerticalArrangement
import com.dat.jetpackcomposecatalog.presenstation.theme.JetpackComposeCatalogTheme
import com.dat.jetpackcomposecatalog.presenstation.widget.MySwitchButtonCompose
import com.dat.jetpackcomposecatalog.presenstation.widget.SettingComponent
import com.dat.jetpackcomposecatalog.presenstation.widget.TextItemLayout
import com.dat.jetpackcomposecatalog.presenstation.widget.ValueSlider

private const val PER_GROUP_COUNT = 10

@ExperimentalFoundationApi
@Composable
fun ColumnScope.ColumnLazyComposeScreen() {
    var verticalArrangement: Pair<String, Arrangement.Vertical> by remember {
        mutableStateOf(listVerticalArrangement.first())
    }
    var horizontalAlignment: Pair<String, Alignment.Horizontal> by remember {
        mutableStateOf(listHorizontalAlignment.first())
    }
    var stickyHeader by remember {
        mutableStateOf(false)
    }
    var itemCount by remember {
        mutableStateOf(100)
    }
    var reverseLayout by remember {
        mutableStateOf(true)
    }
    LazyColumn(
        verticalArrangement = verticalArrangement.second,
        horizontalAlignment = horizontalAlignment.second,
        modifier = Modifier
            .weight(1f)
            .fillMaxWidth(),
        reverseLayout = reverseLayout
    ) {
        if (stickyHeader) {
            val countHeader = itemCount / PER_GROUP_COUNT
            for (group in 1..countHeader) {
                stickyHeader {
                    Text(
                        text = "Group $group",
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(color = Color.Black)
                            .padding(8.dp),
                        style = MaterialTheme.typography.bodyMedium.copy(
                            Color.White
                        )
                    )
                }
                items(PER_GROUP_COUNT) {
                    TextItemLayout("Item $it, group $group")
                }
            }
        } else {
            items(itemCount, key = { it }) {
                TextItemLayout(text = "Item $it")
            }
        }
    }
    GroupConfigChange(
        stickyHeader = stickyHeader,
        reverseLayout = reverseLayout,
        itemCount = itemCount,
        verticalArrangement = verticalArrangement,
        horizontalAlignment = horizontalAlignment,
        updateStickyHeader = {
            stickyHeader = it
        },
        updateReverseLayout = {
            reverseLayout = it
        },
        itemCountChange = {
            itemCount = it.toInt()
        },
        updateHorizontalAlignment = {
            horizontalAlignment = it
        },
        updateVerticalArrangement = {
            verticalArrangement = it
        }
    )
}

@Composable
private fun GroupConfigChange(
    stickyHeader: Boolean,
    reverseLayout: Boolean,
    itemCount: Int,
    verticalArrangement: Pair<String, Arrangement.Vertical>,
    horizontalAlignment: Pair<String, Alignment.Horizontal>,
    updateStickyHeader: (Boolean) -> Unit,
    updateReverseLayout: (Boolean) -> Unit,
    itemCountChange: (Float) -> Unit,
    updateVerticalArrangement: (Pair<String, Arrangement.Vertical>) -> Unit,
    updateHorizontalAlignment: (Pair<String, Alignment.Horizontal>) -> Unit
) {
    ValueSlider(
        title = "Item count :",
        value = itemCount.toFloat(),
        from = 0f,
        to = 1000f,
        onValueChange = itemCountChange
    )
    MySwitchButtonCompose(
        title = "Sticky Header",
        isSelected = stickyHeader,
        onSelectedCallback = updateStickyHeader
    )
    MySwitchButtonCompose(
        title = "reverseLayout =",
        isProperties = true,
        isSelected = reverseLayout,
        onSelectedCallback = updateReverseLayout
    )
    SettingComponent(
        name = "verticalArrangement",
        settingSelected = verticalArrangement.first,
        listSetting = listVerticalArrangement.map { it.first },
    ) { selected ->
        listVerticalArrangement.find {
            selected == it.first
        }?.let {
            updateVerticalArrangement(it)
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
            updateHorizontalAlignment(it)
        }
    }
}

@ExperimentalFoundationApi
@Preview
@Composable
fun LazyColumnComposeScreenPreview() {
    JetpackComposeCatalogTheme {
        Scaffold {
            Box(
                Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {
                Column(
                    Modifier
                        .fillMaxSize()
                        .statusBarsPadding()
                        .verticalScroll(rememberScrollState())
                ) {
                    ColumnLazyComposeScreen()
                }
            }
        }

    }
}