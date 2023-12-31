@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.dat.core.designsystem.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.dat.core.designsystem.theme.BlackCodeColor
import com.dat.core.designsystem.theme.BlueCodeColor
import com.dat.core.designsystem.theme.JetpackComposeCatalogTheme

@Composable
fun ValueSlider(
    modifier: Modifier = Modifier,
    title: String = "Title",
    isProperties: Boolean = false,
    value: Int = 0,
    from: Int = 0,
    to: Int = 100,
    step: Int = 20,
    onValueChange: (Int) -> Unit = {}
) {
    val interactionSource = MutableInteractionSource()
    val titleStyle = if (isProperties)
        MaterialTheme.typography.bodyMedium.copy(color = BlueCodeColor)
    else
        MaterialTheme.typography.bodyMedium
    Row(
        modifier
            .fillMaxWidth(1f)
            .border(border = BorderStroke(1.dp, color = Color.LightGray))
            .background(if (isProperties) BlackCodeColor else Color.Transparent)
            .padding(horizontal = 8.dp),
        Arrangement.Center,
        verticalAlignment = CenterVertically,
    ) {
        Text(text = "$title = $value", style = titleStyle)
        Spacer(modifier = Modifier.width(8.dp))
        Slider(
            modifier = Modifier
                .weight(1f)
                .height(28.dp),
            value = value.toFloat(),
            onValueChange = {
                onValueChange(it.toInt())
            },
            valueRange = from.toFloat()..to.toFloat(),
            steps = step,
            interactionSource = interactionSource,
            thumb = {
                SliderDefaults.Thumb(
                    interactionSource = interactionSource,
                    thumbSize = DpSize(18.dp, 18.dp),
                )
            },
        )
    }
}


@Preview
@Composable
fun PreviewValueSlider() {
    JetpackComposeCatalogTheme {
        val value = remember {
            mutableStateOf(0)
        }
        ValueSlider(value = value.value, from = 0, to = 100) {
            value.value = it
        }
    }
}