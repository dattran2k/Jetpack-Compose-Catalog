package com.dat.jetpackcomposecatalog.presenstation.widget


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dat.jetpackcomposecatalog.data.model.catalog.ModifierConfig
import com.dat.jetpackcomposecatalog.data.model.catalog.PaddingAll
import com.dat.jetpackcomposecatalog.data.model.catalog.PaddingHorVer
import com.dat.jetpackcomposecatalog.data.model.catalog.PaddingWay
import com.dat.jetpackcomposecatalog.presenstation.theme.BlackColor
import com.dat.jetpackcomposecatalog.presenstation.theme.BlueCodeColor
import com.dat.jetpackcomposecatalog.presenstation.theme.JetpackComposeCatalogTheme
import com.dat.jetpackcomposecatalog.presenstation.theme.LocalCustomColorTheme
import com.dat.jetpackcomposecatalog.presenstation.theme.WhiteCodeColor
import com.dat.jetpackcomposecatalog.presenstation.theme.WhiteColor
import com.dat.jetpackcomposecatalog.presenstation.theme.YellowCodeColor

@Composable
fun ShowCodeCompose(list: List<ModifierConfig>) {
    Column(modifier = Modifier.background(BlackColor)) {
        Text(text = "Modifier", style = MaterialTheme.typography.bodyMedium, color = WhiteCodeColor)
        list.forEach {
            Text(text = buildAnnotatedString {
                withStyle(style = SpanStyle(color = YellowCodeColor)) {
                    append(".${it.composeName}")
                }
                withStyle(style = SpanStyle(color = YellowCodeColor)) {
                    append("(")
                }
                it.listShowCode.forEach {
                    withStyle(style = SpanStyle(color = BlueCodeColor, fontStyle = FontStyle.Italic)) {
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
}

@Preview
@Composable
fun ShowCodeComposePreview() {
    JetpackComposeCatalogTheme() {
        val list = listOf(
            PaddingAll(10.dp),
            PaddingWay(12.dp, 13.dp, 14.dp, 15.dp),
            PaddingHorVer(12.dp, 13.dp)
        )
        ShowCodeCompose( list = list)
    }
}