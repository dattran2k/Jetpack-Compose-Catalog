package com.dat.jetpackcomposecatalog.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.dat.jetpackcomposecatalog.common.MyIcons

@Composable
fun MyBox(
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colorScheme.secondary,
    useItemImage: Boolean = true,
    onItemImageClick: () -> Unit = {},
    content: @Composable () -> Unit = {},
) {
    Surface(
        modifier = modifier
            .padding(2.dp)
            .size(100.dp),
        color = color,
    ) {
        if (useItemImage)
            Image(
                modifier = Modifier
                    .fillMaxHeight()
                    .aspectRatio(1f)
                    .clip(CircleShape)
                    .clickable(onClick = onItemImageClick),
                painter = painterResource(MyIcons.ImDefault),
                contentDescription = "MyAvatar"
            )
        content.invoke()
    }
}