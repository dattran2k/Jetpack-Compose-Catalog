package com.dat.jetpackcomposecatalog.presenstation.widget

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.Center
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
import com.dat.jetpackcomposecatalog.R
import com.dat.jetpackcomposecatalog.data.model.catalog.ModPadding
import com.dat.jetpackcomposecatalog.data.model.catalog.ModifierConfig
import com.dat.jetpackcomposecatalog.presenstation.theme.BlackColor
import com.dat.jetpackcomposecatalog.presenstation.theme.BlueCodeColor
import com.dat.jetpackcomposecatalog.presenstation.theme.JetpackComposeCatalogTheme
import com.dat.jetpackcomposecatalog.presenstation.theme.LocalCustomColorTheme
import com.dat.jetpackcomposecatalog.presenstation.theme.WhiteCodeColor
import com.dat.jetpackcomposecatalog.presenstation.theme.WhiteColor
import com.dat.jetpackcomposecatalog.presenstation.theme.WhiteColorAlpha10
import com.dat.jetpackcomposecatalog.presenstation.theme.YellowCodeColor
import org.burnoutcrew.reorderable.ItemPosition
import org.burnoutcrew.reorderable.ReorderableItem
import org.burnoutcrew.reorderable.detectReorderAfterLongPress
import org.burnoutcrew.reorderable.rememberReorderableLazyListState
import org.burnoutcrew.reorderable.reorderable

val editCodeModifier = Modifier
    .background(BlackColor)
    .fillMaxWidth()
    .padding(horizontal = 8.dp)

@ExperimentalMaterial3Api
@Composable
fun ModifierConfigCompose(
    title: String = "",
    content: @Composable (modifier: Modifier) -> Unit = {},
) {
    val configViewModel: ModifierConfigViewModel = hiltViewModel()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Open)
    val modifier: Modifier by configViewModel.modifier.collectAsStateWithLifecycle()
    val listConfig by configViewModel.listConfigModifier.collectAsStateWithLifecycle()

    ModalNavigationDrawer(
        drawerState = drawerState,
        modifier = Modifier.background(WhiteColorAlpha10),
        drawerContent = {
            ModifierConfigDrawer(
                listConfig,
                addNewModifierConfig = {
                    configViewModel.addModifierConfig(ModPadding.All(size = 1.dp))
                },
                replaceItem = configViewModel::replaceItem
            )
        },
    ) {
        Scaffold(contentColor = LocalCustomColorTheme.current.background) { contentPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize(1f)
                    .padding(paddingValues = contentPadding)
            ) {
                Box(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp)
                            .border(BorderStroke(1.dp, Color.LightGray))
                            .padding(16.dp),
                        contentAlignment = Center
                    ) {
                        content(modifier)
                    }
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Center) {
                        Text(
                            text = title,
                            modifier = Modifier
                                .padding(8.dp)
                                .background(LocalCustomColorTheme.current.background)
                                .padding(horizontal = 8.dp),
                            style = MaterialTheme.typography.bodyLarge,
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun ModifierConfigDrawer(
    listModifierConfig: List<ModifierConfig>,
    replaceItem: (from: ItemPosition, to: ItemPosition) -> Unit,
    addNewModifierConfig: () -> Unit,
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
            modifier = Modifier.reorderable(lazyState)
        ) {
            items(listModifierConfig.size, { listModifierConfig[it].key }) { index ->
                val modifierConfig = listModifierConfig[index]
                ReorderableItem(lazyState, key = modifierConfig.key) { isDragging ->
                    val color = animateColorAsState(if (isDragging) Color.Red else Color.Transparent)
                    Box(
                        modifier = editCodeModifier
                            .detectReorderAfterLongPress(lazyState)
                            .background(color.value)
                            .padding(vertical = 4.dp)
                    ) {
                        ShowCodeCompose(modifierConfig = modifierConfig, index == propertiesSelected) {
                            propertiesSelected = if (propertiesSelected == index) -1 else index
                        }
                    }
                }
            }
        }

        Box(modifier = Modifier.weight(1f)) {
            Text(
                modifier = Modifier
                    .padding(vertical = 8.dp)
                    .fillMaxWidth(1f)
                    .background(color = WhiteCodeColor)
                    .padding(4.dp)
                    .clickable(onClick = addNewModifierConfig),
                text = stringResource(id = R.string.add_properties),
                style = MaterialTheme.typography.bodyMedium,
                color = WhiteColor,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
fun ShowCodeCompose(
    modifierConfig: ModifierConfig,
    isSelected: Boolean,
    selectCallback: () -> Unit,
) {
    Box(
        modifier = Modifier
            .padding(vertical = 2.dp)
            .background(if (isSelected) Color.Red else Color.Transparent)
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
                    // TODO remove last ","
                    append("${it.value},")
                }
            }
            withStyle(style = SpanStyle(color = YellowCodeColor)) {
                append(" )")
            }
        }, style = MaterialTheme.typography.bodySmall)
    }
}

@ExperimentalMaterial3Api
@Preview
@Composable
fun PreviewBaseDetailCompose() {
    JetpackComposeCatalogTheme(content = {
        ModifierConfigCompose(title = "Box", content = {
            Box(
                modifier = it
                    .background(Color.Red)
                    .size(80.dp)
            )
        })
    })
}