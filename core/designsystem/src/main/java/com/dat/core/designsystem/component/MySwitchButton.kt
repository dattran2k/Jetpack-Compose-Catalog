package com.dat.core.designsystem.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.dat.core.designsystem.theme.BlackCodeColor
import com.dat.core.designsystem.theme.BlueCodeColor
import com.dat.core.designsystem.theme.JetpackComposeCatalogTheme
import com.dat.core.designsystem.theme.OrangeCodeColor

@Composable
fun MySwitchButtonCompose(
    modifier: Modifier = Modifier,
    title: String = "",
    isProperties: Boolean = false,
    isSelected: Boolean = false,
    onSelectedCallback: (Boolean) -> Unit = {}
) {
    Row(
        modifier
            .fillMaxWidth()
            .background(if (isProperties) BlackCodeColor else Color.Transparent)
            .border(border = BorderStroke(1.dp, color = Color.LightGray))
            .padding(horizontal = 8.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        val titleStyle = if (isProperties)
            MaterialTheme.typography.bodyMedium.copy(
                BlueCodeColor,
                fontStyle = FontStyle.Italic
            )
        else
            MaterialTheme.typography.bodyMedium
        val valueStyle = if (isProperties)
            MaterialTheme.typography.bodyMedium.copy(color = OrangeCodeColor)
        else
            MaterialTheme.typography.bodyMedium
        Text(
            text = title,
            style = titleStyle
        )
        Spacer(modifier = Modifier.width(16.dp))

        Text(
            text = if (isSelected) "True" else "False",
            style = valueStyle
        )
        Spacer(modifier = Modifier.weight(1f))
        CustomSwitch(isSelected = isSelected, onSelectedCallback = onSelectedCallback)
    }

}

@Composable
fun CustomSwitch(
    width: Dp = 52.dp,
    height: Dp = 30.dp,
    checkedTrackColor: Color = MaterialTheme.colorScheme.primary,
    uncheckedTrackColor: Color = Color.Gray,
    gapBetweenThumbAndTrackEdge: Dp = 8.dp,
    borderWidth: Dp = 2.dp,
    cornerSize: Int = 50,
    iconInnerPadding: Dp = 4.dp,
    thumbSize: Dp = 18.dp,
    isSelected: Boolean = false,
    onSelectedCallback: (Boolean) -> Unit = {}
) {
    // this is to disable the ripple effect
    val interactionSource = remember {
        MutableInteractionSource()
    }
    // for moving the thumb
    val alignment by animateAlignmentAsState(if (isSelected) Alignment.CenterEnd else Alignment.CenterStart)

    Spacer(modifier = Modifier.width(16.dp))
    Box(
        modifier = Modifier
            .size(width = width, height = height)
            .border(
                width = borderWidth,
                color = if (isSelected) checkedTrackColor else uncheckedTrackColor,
                shape = RoundedCornerShape(percent = cornerSize)
            )
            .clickable(
                indication = null,
                interactionSource = interactionSource
            ) {
                onSelectedCallback(!isSelected)
            },
        contentAlignment = Alignment.Center
    ) {
        // this is to add padding at the each horizontal side
        Box(
            modifier = Modifier
                .padding(
                    start = gapBetweenThumbAndTrackEdge,
                    end = gapBetweenThumbAndTrackEdge
                )
                .fillMaxSize(),
            contentAlignment = alignment
        ) {
            // thumb with icon
            Icon(
                imageVector = if (isSelected) Icons.Filled.Done else Icons.Filled.Close,
                contentDescription = if (isSelected) "Enabled" else "Disabled",
                modifier = Modifier
                    .size(size = thumbSize)
                    .background(
                        color = if (isSelected) checkedTrackColor else uncheckedTrackColor,
                        shape = CircleShape
                    )
                    .padding(all = iconInnerPadding),
                tint = Color.White
            )
        }
    }


}



@Preview
@Composable
fun MySwitchButtonComposePreview() {
    JetpackComposeCatalogTheme {
        MySwitchButtonCompose()
    }
}