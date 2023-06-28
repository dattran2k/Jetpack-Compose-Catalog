package com.dat.jetpackcomposecatalog.presenstation.view.catalog_compose


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import androidx.compose.ui.unit.sp
import com.dat.jetpackcomposecatalog.R
import com.dat.jetpackcomposecatalog.data.model.catalog.ModPadding
import com.dat.jetpackcomposecatalog.data.model.catalog.ModifierConfig
import com.dat.jetpackcomposecatalog.presenstation.theme.BlackColor
import com.dat.jetpackcomposecatalog.presenstation.theme.BlueCodeColor
import com.dat.jetpackcomposecatalog.presenstation.theme.JetpackComposeCatalogTheme
import com.dat.jetpackcomposecatalog.presenstation.theme.WhiteCodeColor
import com.dat.jetpackcomposecatalog.presenstation.theme.WhiteColor
import com.dat.jetpackcomposecatalog.presenstation.theme.YellowCodeColor

fun LazyListScope.showCodeCompose(
    listModifier: List<ModifierConfig>,
    modifier: Modifier = Modifier
        .background(BlackColor)
        .fillMaxWidth()
        .padding(horizontal = 8.dp),
    propertiesSelected: Int = -1,
    updatePropertiesSelected: (Int) -> Unit = {},
    addProperties: () -> Unit = {}
) {
    item {
        Spacer(modifier = modifier.padding(top = 8.dp))
        Text(
            text = "Modifier Setting",
            style = MaterialTheme.typography.bodyMedium,
            color = WhiteCodeColor,
            modifier = modifier
        )

    }
    items(listModifier.size) { index ->
        val modifierConfig = listModifier[index]

        Box(
            modifier = modifier
        ) {
            PropertiesLineCompose(
                modifierConfig = modifierConfig,
                index == propertiesSelected
            ) {
                updatePropertiesSelected(index)
            }
        }
    }
    item {
        Box(modifier = modifier) {
            AddProperties(addProperties)
        }
    }
}

@Composable
fun AddProperties(addProperties: () -> Unit) {
    Text(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .fillMaxWidth(1f)
            .background(color = WhiteCodeColor)
            .padding(4.dp)
            .clickable { addProperties.invoke() },
        text = stringResource(id = R.string.add_properties),
        style = MaterialTheme.typography.bodySmall.copy(
            fontSize = 11.sp
        ),
        color = WhiteColor,
        textAlign = TextAlign.Center
    )
}

@Composable
fun PropertiesLineCompose(
    modifierConfig: ModifierConfig,
    isSelected: Boolean,
    selectCallback: () -> Unit
) {
    Box(
        modifier = Modifier
            .padding(vertical = 2.dp)
            .background(if (isSelected) Color.Red else Color.Transparent)
            .clickable {
                selectCallback.invoke()
            }
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

@Preview
@Composable
fun ShowCodeComposePreview() {
    JetpackComposeCatalogTheme() {
        val data = remember {
            mutableStateOf(
                listOf(
                    ModPadding.All(10.dp),
                    ModPadding.Way(12.dp, 13.dp, 14.dp, 15.dp),
                    ModPadding.HorVer(12.dp, 13.dp)
                )
            )
        }
        LazyColumn(Modifier.fillMaxSize()) {
            showCodeCompose(listModifier = data.value,)
        }

    }
}