package com.dat.ui.feature.catalog_overview

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dat.core.designsystem.component.LotteAnimInfinityLoader
import com.dat.core.designsystem.theme.JetpackComposeCatalogTheme
import com.dat.core.designsystem.theme.LocalIsDarkMode
import com.dat.ui.feature.CatalogComposeEnum
import com.dat.ui.feature.CatalogComposeGroup
import com.dat.core.designsystem.R as designsystemR

data class CatalogOverViewGroup(
    val catalogComposeGroup: CatalogComposeGroup,
    val listItem: List<CatalogComposeEnum>,
    var isExpand: Boolean = true,
)

@Composable
fun CatalogOverviewRoute(
    navigateCatalogDetail: (CatalogComposeEnum) -> Unit = {},
) {
    CatalogOverviewScreen(navigateCatalogDetail)
}

@Composable
fun CatalogOverviewScreen(
    navigateCatalogDetail: (CatalogComposeEnum) -> Unit = {},
    viewModel: CatalogOverviewViewModel = hiltViewModel()
) {
    val catalogList by viewModel.catalogOverViewGroupList.collectAsStateWithLifecycle()
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(catalogList.size) { index ->
            CatalogGroupItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                catalogGroup = catalogList[index],
                navigateCatalogDetail = navigateCatalogDetail,
                updateExpand = viewModel::updateExpand
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
    var isExpand by remember { mutableStateOf(catalogGroup.isExpand) }
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        ),
        shape = MaterialTheme.shapes.extraSmall,
        border = BorderStroke(0.5.dp, color = Color.LightGray)
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            DescribeCatalog(catalogGroup) {
                isExpand = !isExpand
                updateExpand(it)
            }
            AnimatedVisibility(visible = isExpand) {
                Column {
                    Divider(
                        modifier = Modifier
                            .padding(top = 8.dp)
                            .background(Color.LightGray)
                    )
                    catalogGroup.listItem.forEachIndexed { index, catalogComposeEnum ->
                        Text(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    navigateCatalogDetail(catalogComposeEnum)
                                }
                                .padding(start = 16.dp, top = 8.dp, bottom = 8.dp),
                            text = "${index + 1}. ${catalogComposeEnum.name}",
                            style = MaterialTheme.typography.bodyMedium
                        )
                    }
                }
            }

        }
    }
}

@Composable
fun DescribeCatalog(
    catalogGroup: CatalogOverViewGroup,
    updateExpand: (CatalogOverViewGroup) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(intrinsicSize = IntrinsicSize.Min)
            .clickable {
                updateExpand(catalogGroup)
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = catalogGroup.catalogComposeGroup.name,
            style = MaterialTheme.typography.titleMedium
        )
        when (catalogGroup.catalogComposeGroup) {
//            CatalogComposeGroup.Widgets -> {
//
//            }
            CatalogComposeGroup.Modifier -> {

            }

            CatalogComposeGroup.Layout -> {
                Spacer(modifier = Modifier.weight(1f))
                LotteAnimInfinityLoader(
                    Modifier.size(24.dp),
                    if (LocalIsDarkMode.current)
                        designsystemR.raw.anim_layout_dark
                    else
                        designsystemR.raw.anim_layout_light
                )
            }

            CatalogComposeGroup.Animation -> {
                Spacer(modifier = Modifier.weight(1f))
                LotteAnimInfinityLoader(
                    Modifier.size(24.dp),
                    designsystemR.raw.anim_animation_switch
                )
            }


        }
    }
}


@Preview
@Composable
fun CatalogOverviewScreenPreview() {
    JetpackComposeCatalogTheme {
        CatalogOverviewRoute()
    }
}