package com.dat.jetpackcomposecatalog.presenstation.widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dat.jetpackcomposecatalog.data.model.catalog.ModifierConfig
import com.dat.jetpackcomposecatalog.data.model.catalog.updateModifier
import com.dat.jetpackcomposecatalog.presenstation.theme.JetpackComposeCatalogTheme
import com.dat.jetpackcomposecatalog.presenstation.theme.LocalCustomColorTheme


@ExperimentalMaterial3Api
@Composable
fun ModifierConfigCompose(
    title: String = "",
    content: @Composable (modifier: Modifier) -> Unit = {},
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Open)
    val config by remember { mutableStateOf(listOf<ModifierConfig>()) }
    var modifier: Modifier = Modifier
    // TODO working around this
    config.forEach {
        modifier = modifier.updateModifier(it)
    }
    modifier.absoluteOffset()
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                Modifier
                    .fillMaxWidth(0.6f)
                    .padding(horizontal = 4.dp)
            )
            {
                Text(
                    text = "Setting Modifier",
                    modifier = Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center,
                )

                ValueSlider(title = "")
            }
        },
    ) {
        Scaffold(
            topBar = {
                TopAppBar(title = {
                    Text(
                        text = title,
                        modifier = Modifier.fillMaxWidth(),
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Center
                    )
                })
            },
            contentColor = LocalCustomColorTheme.current.background,
        ) { contentPadding ->
            Column(
                modifier = Modifier
                    .padding(paddingValues = contentPadding)
                    .fillMaxSize(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                content(modifier)
            }
        }
    }
}


@ExperimentalMaterial3Api
@Preview
@Composable
fun PreviewBaseDetailCompose() {
    JetpackComposeCatalogTheme(content = {
        ModifierConfigCompose(title = "HELLO", content = {
            Box(modifier = it)
        })
    })
}