package com.dat.jetpackcomposecatalog.presentation.feature.main.home

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.test.ExperimentalTestApi
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import com.dat.designsystem.theme.JetpackComposeCatalogTheme
import com.dat.ui.feature.CatalogComposeEnum
import com.dat.ui.feature.catalog_overview.CatalogOverViewGroup
import com.dat.ui.feature.catalog_overview.CatalogOverviewScreen
import org.junit.Rule
import org.junit.Test


@ExperimentalTestApi
@ExperimentalMaterialApi
class HomeScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun if_list_to_do_not_empty_should_show() {
        composeTestRule.setContent {
            JetpackComposeCatalogTheme(
                content = {
                    CatalogOverviewScreen(
                        CatalogComposeEnum.values()
                            .groupBy { it.group }
                            .map { (group, itemList) ->
                                CatalogOverViewGroup(group, itemList)
                            }
                    )
                },
            )
        }
        composeTestRule.onAllNodes(hasText("Animation", ignoreCase = true)).assertCountEquals(1)
    }
}
