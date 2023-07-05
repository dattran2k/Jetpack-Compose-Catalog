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
import androidx.compose.foundation.lazy.LazyRow
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
import com.dat.jetpackcomposecatalog.core.common.DataConst.listHorizontalArrangement
import com.dat.jetpackcomposecatalog.core.common.DataConst.listVerticalAlignment
import com.dat.jetpackcomposecatalog.presenstation.theme.JetpackComposeCatalogTheme
import com.dat.jetpackcomposecatalog.presenstation.widget.MySwitchButtonCompose
import com.dat.jetpackcomposecatalog.presenstation.widget.SettingComponent
import com.dat.jetpackcomposecatalog.presenstation.widget.TextItemLayout
import com.dat.jetpackcomposecatalog.presenstation.widget.ValueSlider

private const val PER_GROUP_COUNT = 10

@ExperimentalFoundationApi
@Composable
fun ColumnScope.LazyRowComposeScreen() {
    var horizontalArrangement: Pair<String, Arrangement.Horizontal> by remember {
        mutableStateOf(listHorizontalArrangement.first())
    }
    var verticalAlignment: Pair<String, Alignment.Vertical> by remember {
        mutableStateOf("Alignment.CenterHorizontally" to Alignment.CenterVertically)
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
    LazyRow(
        verticalAlignment = verticalAlignment.second,
        horizontalArrangement = horizontalArrangement.second,
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
        horizontalArrangement = horizontalArrangement,
        verticalAlignment = verticalAlignment,
        updateStickyHeader = {
            stickyHeader = it
        },
        updateReverseLayout = {
            reverseLayout = it
        },
        itemCountChange = {
            itemCount = it.toInt()
        },
        updateHorizontalArrangement = {
            horizontalArrangement = it
        },
        updateVerticalAlignment = {
            verticalAlignment = it
        }
    )
}

@Composable
private fun GroupConfigChange(
    stickyHeader: Boolean,
    reverseLayout: Boolean,
    itemCount: Int,
    horizontalArrangement: Pair<String, Arrangement.Horizontal>,
    verticalAlignment: Pair<String, Alignment.Vertical>,
    updateStickyHeader: (Boolean) -> Unit,
    updateReverseLayout: (Boolean) -> Unit,
    itemCountChange: (Float) -> Unit,
    updateHorizontalArrangement: (Pair<String, Arrangement.Horizontal>) -> Unit,
    updateVerticalAlignment: (Pair<String, Alignment.Vertical>) -> Unit
) {
    ValueSlider(
        title = "Item count :",
        value = itemCount.toFloat(),
        from = 0f,
        to = 1000f,
        onValueChange = itemCountChange
    )
    MySwitchButtonCompose(
        title = "Sticky Header : ",
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
        name = "horizontalArrangement",
        settingSelected = horizontalArrangement.first,
        listSetting = listHorizontalArrangement.map { it.first },
    ) { selected ->
        listHorizontalArrangement.find {
            selected == it.first
        }?.let {
            updateHorizontalArrangement(it)
        }
    }
    SettingComponent(
        name = "verticalAlignment",
        settingSelected = verticalAlignment.first,
        listSetting = listVerticalAlignment.map { it.first },
    ) { selected ->
        listVerticalAlignment.find {
            selected == it.first
        }?.let {
            updateVerticalAlignment(it)
        }
    }
}


@ExperimentalFoundationApi
@Preview
@Composable
fun LazyRowComposeScreenPreview() {
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
                    LazyRowComposeScreen()
                }
            }
        }

    }
}