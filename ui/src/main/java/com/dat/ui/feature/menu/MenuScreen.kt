package com.dat.ui.feature.menu

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.dat.core.model.DarkThemeConfig
import com.dat.designsystem.theme.JetpackComposeCatalogTheme
import com.dat.designsystem.theme.LocalIsDarkMode
import com.dat.ui.common.Utils
import com.dat.core.designsystem.R as designsystemR

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
    val context = LocalContext.current
    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Project Info",
            style = MaterialTheme.typography.headlineLarge,
        )
        Column(
            modifier = Modifier
                .clickable {
                    Utils.openUrl(
                        context,
                        context.getString(designsystemR.string.project_source_github)
                    )
                },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LoaderGithub()
            Text(
                text = "Source code",
                style = MaterialTheme.typography.bodyMedium,
            )
        }
        Divider(modifier = Modifier.padding(16.dp))
        Text(
            text = "Setting",
            style = MaterialTheme.typography.headlineMedium,
        )
        when (menuUIState) {
            MenuUIState.Loading -> {
                Text(
                    text = stringResource(designsystemR.string.loading),
                    modifier = Modifier.padding(vertical = 16.dp),
                    style = MaterialTheme.typography.titleMedium
                )
            }

            is MenuUIState.Success -> {
                SettingsPanel(darkMode = menuUIState.darkMode, onChangeDarkThemeConfig)
            }
        }
    }
}

@Composable
fun LoaderGithub(modifier: Modifier = Modifier) {
    val composition by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(
            if (LocalIsDarkMode.current)
                designsystemR.raw.anim_github_dark
            else
                designsystemR.raw.anim_github_light
        )
    )
    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever
    )
    LottieAnimation(
        modifier = modifier.size(100.dp),
        composition = composition,
        progress = { progress },
    )
}

@Composable
private fun SettingsPanel(
    darkMode: DarkThemeConfig,
    onChangeDarkThemeConfig: (darkThemeConfig: DarkThemeConfig) -> Unit = {},
) {
    Column(Modifier.selectableGroup()) {
        Text(
            text = "Dark Mode",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(start = 16.dp)
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
    JetpackComposeCatalogTheme(true) {
        UserScreen(menuUIState = MenuUIState.Success(DarkThemeConfig.LIGHT))
    }
}