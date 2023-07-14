package com.dat.jetpackcomposecatalog.presentation.feature.catalog_compose.layout

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dat.jetpackcomposecatalog.data.model.catalog.MyHorizontalArrangement
import com.dat.jetpackcomposecatalog.data.model.catalog.MyVerticalAlignment
import com.dat.jetpackcomposecatalog.presentation.theme.JetpackComposeCatalogTheme
import com.dat.jetpackcomposecatalog.presentation.widget.MySwitchButtonCompose
import com.dat.jetpackcomposecatalog.presentation.widget.SettingComponent
import com.dat.jetpackcomposecatalog.presentation.widget.TextItemLayout
import com.dat.jetpackcomposecatalog.presentation.widget.ValueSlider

private const val PER_GROUP_COUNT = 10

@ExperimentalFoundationApi
@Composable
fun LayoutLazyRow(
    modifier: Modifier = Modifier,
    viewModel: CatalogViewModel = hiltViewModel()
) {
    val horizontalArrangement by viewModel.horizontalArrangementState.collectAsState()
    val verticalAlignment by viewModel.verticalAlignmentState.collectAsState()
    val itemCount by viewModel.itemCountState.collectAsState()

    var stickyHeader by remember {
        mutableStateOf(false)
    }
    LazyRow(
        modifier = modifier,
        verticalAlignment = verticalAlignment.value,
        horizontalArrangement = horizontalArrangement.value,
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
                    TextItemLayout(
                        modifier = Modifier.fillMaxHeight(0.5f),
                        text = "Item $it, group $group"
                    )
                }
            }
        } else {
            items(itemCount, key = { it }) {
                TextItemLayout(
                    modifier = Modifier.fillMaxHeight(0.5f),
                    text = "Item $it"
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
    MySwitchButtonCompose(
        title = "Sticky Header : ",
        isSelected = stickyHeader,
        onSelectedCallback = {
            stickyHeader = it
        }
    )

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
                    LayoutLazyRow()
                }
            }
        }

    }
}