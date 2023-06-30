package com.dat.jetpackcomposecatalog.core.designsystem.icon

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.vector.ImageVector
import com.dat.jetpackcomposecatalog.R

object MyIcons {
    val MenuBook = R.drawable.ic_menu_book
    val MenuBookBorder = R.drawable.ic_menu_book_border
    val Info = R.drawable.ic_info
    val InfoBorder = R.drawable.ic_info_border
}

sealed class Icon {
    data class ImageVectorIcon(val imageVector: ImageVector) : Icon()
    data class DrawableResourceIcon(@DrawableRes val id: Int) : Icon()
}
