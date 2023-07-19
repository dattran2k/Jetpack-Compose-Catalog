package com.dat.jetpackcomposecatalog.presentation.widget

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.dat.jetpackcomposecatalog.core.designsystem.icon.Icon

@Composable
fun IconLoader(modifier: Modifier = Modifier, icon: Icon) {
    when (icon) {
        is Icon.DrawableResourceIcon -> Icon(
            modifier = modifier,
            painter = painterResource(id = icon.id),
            contentDescription = null,
        )

        is Icon.ImageVectorIcon -> Icon(
            modifier = modifier,
            imageVector = icon.imageVector,
            contentDescription = null,
        )
    }
}