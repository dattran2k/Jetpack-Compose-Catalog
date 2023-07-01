package com.dat.jetpackcomposecatalog.presenstation.feature.catalog.catalog_overview

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dat.jetpackcomposecatalog.presenstation.feature.catalog.CatalogComposeEnum
import com.dat.jetpackcomposecatalog.presenstation.feature.catalog.CatalogGroup
import com.dat.jetpackcomposecatalog.presenstation.theme.JetpackComposeCatalogTheme

data class CatalogOverViewGroup(
    val catalogGroup: CatalogGroup,
    val listItem: List<CatalogComposeEnum>,
    var isExpand: Boolean = false,
)

@Composable
fun CatalogOverviewRoute(
    navigateCatalogDetail: (CatalogComposeEnum) -> Unit,
    viewModel: CatalogOverviewViewModel = hiltViewModel()
) {
    val catalogList by viewModel.catalogOverViewGroupList.collectAsStateWithLifecycle()
    CatalogOverviewScreen(catalogList, navigateCatalogDetail, viewModel::updateExpand)
}

@Composable
fun CatalogOverviewScreen(
    catalogOverViewGroupList: List<CatalogOverViewGroup>,
    navigateCatalogDetail: (CatalogComposeEnum) -> Unit,
    updateExpand: (CatalogOverViewGroup) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
    ) {
        catalogOverViewGroupList.forEach { catalogGroup ->
            CatalogGroupItem(
                Modifier.padding(8.dp),
                catalogGroup,
                navigateCatalogDetail,
                updateExpand
            )
        }
    }
}

@Composable
private fun CatalogGroupItem(
    modifier: Modifier,
    catalogGroup: CatalogOverViewGroup,
    navigateCatalogDetail: (CatalogComposeEnum) -> Unit,
    updateExpand: (CatalogOverViewGroup) -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        shape = MaterialTheme.shapes.extraSmall,
        border = BorderStroke(0.5.dp, color = Color.LightGray)
    ) {
        Column(modifier = modifier) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        updateExpand(catalogGroup)
                    },
                text = catalogGroup.catalogGroup.name,
                style = MaterialTheme.typography.titleMedium
            )
            AnimatedVisibility(visible = catalogGroup.isExpand) {
                Column {
                    Divider(
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .background(MaterialTheme.colorScheme.tertiary)
                    )
                    catalogGroup.listItem.forEachIndexed { index, catalogComposeEnum ->
                        Text(
                            modifier = Modifier
                                .padding(start = 16.dp, top = 8.dp, bottom = 8.dp)
                                .clickable {
                                    navigateCatalogDetail(catalogComposeEnum)
                                },
                            text = "${index + 1}. ${catalogComposeEnum.name}",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }

        }
    }
}

@Preview
@Composable
fun CatalogOverviewScreenPreview() {
    val groupMap = CatalogComposeEnum.values().groupBy { it.group }
    val catalogOverViewGroupList = groupMap.map { (group, itemList) ->
        CatalogOverViewGroup(group, itemList)
    }
    JetpackComposeCatalogTheme {
        CatalogOverviewScreen(catalogOverViewGroupList, {}, {})
    }
}