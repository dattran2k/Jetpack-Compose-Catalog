@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.dat.jetpackcomposecatalog.presenstation.widget

import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import com.dat.jetpackcomposecatalog.presenstation.theme.JetpackComposeCatalogTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ValueSlider(
    title: String = "TITLE",
    value: Float = 0f,
    from: Float = 0f,
    to: Float = 100f,
    step: Int = 20,
    onValueChange: (Float) -> Unit = {}
) {
    val interactionSource = MutableInteractionSource()
    Column(Modifier.fillMaxWidth(1f)) {
        Text(text = title, style = MaterialTheme.typography.bodyMedium)
        Text(text = value.toInt().toString(), style = MaterialTheme.typography.bodySmall)
        Slider(
            modifier = Modifier.fillMaxWidth(1f),
            value = value,
            onValueChange = onValueChange,
            valueRange = from..to,
            steps = step,
            interactionSource = interactionSource,
            onValueChangeFinished = {
                // launch some business logic update with the state you hold
            },
            thumb = {
                SliderDefaults.Thumb(
                    interactionSource = interactionSource,
                    thumbSize = DpSize(12.dp, 12.dp)
                )
            },
        )
    }
}


@Preview
@Composable
fun PreviewValueSlider() {
    JetpackComposeCatalogTheme() {
        val value = remember {
            mutableStateOf(0f)
        }
        ValueSlider(value = value.value, from = 0f, to = 1000f) {
            value.value = it
        }
    }
}