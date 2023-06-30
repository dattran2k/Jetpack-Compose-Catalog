@file:OptIn(ExperimentalLayoutApi::class)

package com.dat.jetpackcomposecatalog.core.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImagePainter.State.Empty.painter
import com.dat.jetpackcomposecatalog.presenstation.theme.BlackColor
import com.dat.jetpackcomposecatalog.presenstation.theme.BlueCodeColor
import com.dat.jetpackcomposecatalog.presenstation.theme.JetpackComposeCatalogTheme
import com.dat.jetpackcomposecatalog.presenstation.theme.YellowCodeColor


@Composable
fun SettingComponent(
    modifier: Modifier = Modifier,
    name: String,
    settingSelected: String,
    listSetting: List<String>,
    onSettingSelected: (String) -> Unit
) {
    val openDialog = remember { mutableStateOf(false) }
    if (openDialog.value) StringPickerDialog(
        onDismiss = { openDialog.value = false },
        listSetting
    ) {
        onSettingSelected(it)
    }
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.Black)
            .padding(horizontal = 8.dp, vertical = 16.dp)
            .clickable {
                openDialog.value = true
            },
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "$name = ", style = MaterialTheme.typography.titleSmall.copy(
                color = BlueCodeColor, fontStyle = FontStyle.Italic
            ), modifier = Modifier.weight(1f)
        )
        Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.CenterEnd) {
            Column(
                modifier = Modifier
                    .width(IntrinsicSize.Min)
                    .height(IntrinsicSize.Min),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = settingSelected,
                        style = MaterialTheme.typography.bodyMedium.copy(color = YellowCodeColor),
                        modifier = Modifier.weight(1f)
                    )
                    Icon(
                        imageVector = Icons.Default.Menu,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .size(16.dp),
                        tint = YellowCodeColor
                    )
                }
                Divider(
                    thickness = 1.dp, color = YellowCodeColor
                )
            }
        }

    }

}

@Preview
@Composable
fun SettingComponentPreview() {
    JetpackComposeCatalogTheme() {
        SettingComponent(
            Modifier,
            "1231233123 21m3;l21m3;l21m;lk213 2m13l;321lm;2l1;3ml2;1",
            "123123123123123123",
            listOf("123123123123123123", "1", "2", "3"),
        ) {}
    }
}