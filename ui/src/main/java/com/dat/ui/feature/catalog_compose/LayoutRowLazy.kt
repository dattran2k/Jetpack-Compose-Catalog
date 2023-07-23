package com.dat.ui.feature.catalog_compose

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.MaterialTheme
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
import com.dat.designsystem.component.MySwitchButtonCompose
import com.dat.designsystem.component.SettingComponent
import com.dat.designsystem.component.TextItemLayout
import com.dat.designsystem.component.ValueSlider
import com.dat.designsystem.theme.JetpackComposeCatalogTheme
import com.dat.ui.common.ui_model.MyHorizontalArrangement
import com.dat.ui.common.ui_model.MyVerticalAlignment

private const val PER_GROUP_COUNT = 10

@ExperimentalFoundationApi
@Composable
fun LayoutLazyRow(
    modifier: Modifier = Modifier,
    viewModel: LayoutViewModel = hiltViewModel()
) {
    val horizontalArrangement by viewModel.horizontalArrangementState.collectAsState()
    val verticalAlignment by viewModel.verticalAlignmentState.collectAsState()
    val itemCount by viewModel.itemCountState.collectAsState()

    var stickyHeader by remember {
        mutableStateOf(false)
    }
    Column(modifier = modifier) {
        LazyRow(
            modifier = Modifier.weight(1f),
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

}


@ExperimentalFoundationApi
@Preview
@Composable
fun LazyRowComposeScreenPreview() {
    JetpackComposeCatalogTheme {
        LayoutLazyRow(
            Modifier
                .fillMaxSize()
                .statusBarsPadding()
        )
    }

}