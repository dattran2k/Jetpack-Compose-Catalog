@file:OptIn(ExperimentalFoundationApi::class)

package com.dat.ui.feature.catalog_compose.modifier

import android.util.Log
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dat.core.designsystem.R
import com.dat.core.model.ui.ModPadding
import com.dat.core.model.ui.ModifierConfig
import com.dat.designsystem.theme.BlackCodeColor
import com.dat.designsystem.theme.BlackColor
import com.dat.designsystem.theme.BlueCodeColor
import com.dat.designsystem.theme.JetpackComposeCatalogTheme
import com.dat.designsystem.theme.WhiteCodeColor
import com.dat.designsystem.theme.WhiteColorAlpha10
import com.dat.designsystem.theme.YellowCodeColor
import org.burnoutcrew.reorderable.ItemPosition
import org.burnoutcrew.reorderable.ReorderableItem
import org.burnoutcrew.reorderable.detectReorderAfterLongPress
import org.burnoutcrew.reorderable.rememberReorderableLazyListState
import org.burnoutcrew.reorderable.reorderable


val editCodeModifier = Modifier
    .background(BlackColor)
    .fillMaxWidth()
    .padding(horizontal = 8.dp)

@Composable
fun ModifierConfigRoute(configViewModel: ModifierConfigViewModel = hiltViewModel()) {
    Log.e("TAG", "ModifierConfigRoute: ")
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Open)
    val configModifier: Modifier by configViewModel.modifier.collectAsStateWithLifecycle()
    val listConfig by configViewModel.listConfigModifier.collectAsStateWithLifecycle()
    ModalNavigationDrawer(
        drawerState = drawerState,
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black),
        drawerContent = {
            ModifierConfigDrawer(listConfig, configViewModel::replaceItem) {
                configViewModel.addModifierConfig(ModPadding.All(size = 1.dp))
            }
        },
        gesturesEnabled = true
    ) {
        ModifierConfigScreen(configModifier)
    }
}

@Composable
fun ModifierConfigScreen(modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .padding(top = 16.dp)
            .then(modifier)
            .background(Color.Red)
    )
}

@Composable
private fun ModifierConfigDrawer(
    listConfig: List<ModifierConfig>,
    replaceItem: (ItemPosition, ItemPosition) -> Unit,
    addModifierConfig: () -> Unit
) {
    val lazyState = rememberReorderableLazyListState(onMove = replaceItem)
    var propertiesSelected by remember {
        mutableStateOf(-1)
    }
    ModalDrawerSheet(
        drawerContainerColor = WhiteColorAlpha10,
        modifier = Modifier
            .fillMaxWidth(0.6f)
            .padding(horizontal = 4.dp)
    ) {

        Text(
            text = "Modifier",
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.LightGray)
                .padding(vertical = 8.dp),
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
        )
        Spacer(modifier = editCodeModifier.padding(top = 8.dp))
        Text(
            text = "Modifier",
            style = MaterialTheme.typography.bodyMedium,
            color = WhiteCodeColor,
            modifier = editCodeModifier
        )
        LazyColumn(
            state = lazyState.listState,
            modifier = Modifier
                .reorderable(lazyState)
                .weight(1f)
        ) {
            items(listConfig.size, { listConfig[it].key }) { index ->
                val modifierConfig = listConfig[index]
                ReorderableItem(
                    lazyState,
                    key = modifierConfig.key,
                ) { isDragging ->
                    val color = animateColorAsState(if (isDragging) Color.Red else Color.Transparent, label = "")
                    Box(
                        modifier = editCodeModifier
                            .detectReorderAfterLongPress(lazyState)
                            .background(color.value)
                            .padding(vertical = 4.dp)
                            .animateItemPlacement(tween())
                    ) {
                        ShowCodeCompose(
                            modifierConfig = modifierConfig,
                            index == propertiesSelected,
                            isDragging
                        ) {
                            propertiesSelected = if (propertiesSelected == index) -1 else index
                        }
                    }
                }
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth(1f)
                .clickable(onClick = addModifierConfig)
                .background(color = WhiteCodeColor)
                .padding(16.dp),
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(id = R.string.add_properties),
                style = MaterialTheme.typography.bodyLarge,
                color = BlackCodeColor,
                textAlign = TextAlign.Center
            )
        }

    }
}

@Composable
fun ShowCodeCompose(
    modifierConfig: ModifierConfig,
    isSelected: Boolean,
    isDragging: Boolean,
    selectCallback: () -> Unit,
) {
    val color by animateColorAsState(if (isDragging || isSelected) Color.Red else Color.Transparent, label = "")
    Box(
        modifier = Modifier
            .background(color)
            .clickable(onClick = selectCallback)

    ) {
        Text(text = buildAnnotatedString {
            withStyle(style = SpanStyle(color = YellowCodeColor)) {
                append(".${modifierConfig.composeName}")
            }
            withStyle(style = SpanStyle(color = YellowCodeColor)) {
                append("(")
            }
            modifierConfig.listShowCode.forEach {
                withStyle(
                    style = SpanStyle(
                        color = BlueCodeColor,
                        fontStyle = FontStyle.Italic
                    )
                ) {
                    append(" ${it.name} = ")
                }
                withStyle(style = SpanStyle(color = WhiteCodeColor)) {
                    append("${it.value},")
                }
            }
            withStyle(style = SpanStyle(color = YellowCodeColor)) {
                append(" )")
            }
        }, style = MaterialTheme.typography.bodySmall)
    }
}

@Preview
@Composable
fun PreviewBaseDetailCompose() {
    JetpackComposeCatalogTheme(content = {
        ModifierConfigRoute()
    })
}