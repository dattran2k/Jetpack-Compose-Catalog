package com.dat.jetpackcomposecatalog.presenstation.view.catalog_compose

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dat.jetpackcomposecatalog.data.model.catalog.ModPadding
import com.dat.jetpackcomposecatalog.data.model.catalog.ModifierConfig
import com.dat.jetpackcomposecatalog.presenstation.theme.JetpackComposeCatalogTheme
import com.dat.jetpackcomposecatalog.presenstation.theme.LocalCustomColorTheme


@ExperimentalMaterial3Api
@Composable
fun ModifierConfigCompose(
    title: String = "",
    viewModel: ConfigModifierViewModel = hiltViewModel(),
    content: @Composable (modifier: Modifier) -> Unit = {}
) {
    val listModifierConfig by viewModel.listConfigModifier.collectAsStateWithLifecycle()
    val modifier by viewModel.modifier.collectAsStateWithLifecycle()
    var propertiesSelected by remember {
        mutableStateOf(-1)
    }
    // TODO dat : draggable
    // TODO dat : deleteAble

    ModifierConfigComposeContent(
        content,
        modifier,
        title,
        listModifierConfig,
        propertiesSelected,
        updatePropertiesSelected = {
            propertiesSelected = if (propertiesSelected == it) -1 else it
        },
        addProperties = {
            viewModel.addModifierConfig(ModPadding.All(size = 1.dp))
        }
    )
}

@Composable
private fun ModifierConfigComposeContent(
    content: @Composable (modifier: Modifier) -> Unit,
    modifier: Modifier,
    title: String,
    listModifierConfig: List<ModifierConfig>,
    propertiesSelected: Int,
    updatePropertiesSelected: (Int) -> Unit = {},
    addProperties: () -> Unit = {}
) {

    Scaffold(
        contentColor = LocalCustomColorTheme.current.background
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize(1f)
                .padding(paddingValues = contentPadding)
        ) {
            Box(modifier = Modifier.fillMaxWidth()) {
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
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                item {
                    Text(
                        text = "Modifier",
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.LightGray)
                            .padding(vertical = 8.dp),
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Center,
                    )
                }
                showCodeCompose(
                    listModifier = listModifierConfig,
                    propertiesSelected = propertiesSelected,
                    updatePropertiesSelected = updatePropertiesSelected,
                    addProperties = addProperties
                )
            }
        }
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