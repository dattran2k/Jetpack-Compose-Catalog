package com.dat.core.designsystem

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.vector.ImageVector

object MyIcons {
    val MenuBook = R.drawable.ic_menu_book
    val MenuBookBorder = R.drawable.ic_menu_book_border
    val Info = R.drawable.ic_info
    val InfoBorder = R.drawable.ic_info_border
    val Animation = R.drawable.ic_animation

    // this is my dog's image, she was dead, so she is here to make me remember that I will always remember her <3
    val ImDefault = R.drawable.im_defaut
}

sealed class Icon {
    data class ImageVectorIcon(val imageVector: ImageVector) : Icon()
    data class DrawableResourceIcon(@DrawableRes val id: Int) : Icon()
}
