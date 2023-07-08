package com.dat.jetpackcomposecatalog.presentation.feature.menu

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dat.jetpackcomposecatalog.R
import com.dat.jetpackcomposecatalog.data.model.local.DarkThemeConfig
import com.dat.jetpackcomposecatalog.presentation.theme.JetpackComposeCatalogTheme


@Composable
internal fun UserRoute(viewModel: MenuViewModel = hiltViewModel()) {
    val darkModeState by viewModel.menuUIState.collectAsStateWithLifecycle()
    UserScreen(darkModeState, viewModel::updateDarkMode)
}

@Composable
private fun UserScreen(
    menuUIState: MenuUIState,
    onChangeDarkThemeConfig: (darkThemeConfig: DarkThemeConfig) -> Unit = {},
) {
    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        when (menuUIState) {
            MenuUIState.Loading -> {
                Text(
                    text = stringResource(R.string.loading),
                    modifier = Modifier.padding(vertical = 16.dp),
                )
            }
            is MenuUIState.Success -> {
                SettingsPanel(darkMode = menuUIState.darkMode, onChangeDarkThemeConfig)
            }
        }
    }
}

@Composable
private fun SettingsPanel(
    darkMode: DarkThemeConfig,
    onChangeDarkThemeConfig: (darkThemeConfig: DarkThemeConfig) -> Unit = {},
) {
    Column(Modifier.selectableGroup()) {
        Text(
            text = "Dark Mode",
            style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier.padding(start = 16.dp, top = 16.dp)
        )
        SettingsDialogThemeChooserRow(
            text = "Follow system",
            selected = darkMode == DarkThemeConfig.FOLLOW_SYSTEM,
            onClick = { onChangeDarkThemeConfig(DarkThemeConfig.FOLLOW_SYSTEM) },
        )
        SettingsDialogThemeChooserRow(
            text = "Light",
            selected = darkMode == DarkThemeConfig.LIGHT,
            onClick = { onChangeDarkThemeConfig(DarkThemeConfig.LIGHT) },
        )
        SettingsDialogThemeChooserRow(
            text = "Dark",
            selected = darkMode == DarkThemeConfig.DARK,
            onClick = { onChangeDarkThemeConfig(DarkThemeConfig.DARK) },
        )
    }
}

@Composable
private fun SettingsDialogThemeChooserRow(
    text: String,
    selected: Boolean,
    onClick: () -> Unit,
) {
    Row(
        Modifier
            .fillMaxWidth()
            .selectable(
                selected = selected,
                role = Role.RadioButton,
                onClick = onClick,
            )
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        RadioButton(
            selected = selected,
            onClick = null,
        )
        Spacer(Modifier.width(8.dp))
        Text(text, style = MaterialTheme.typography.bodyMedium)
    }
}

@Preview
@Composable
private fun PreviewUser() {
    JetpackComposeCatalogTheme(
        content = {
            UserScreen(menuUIState = MenuUIState.Success(DarkThemeConfig.LIGHT))
        },
    )
}