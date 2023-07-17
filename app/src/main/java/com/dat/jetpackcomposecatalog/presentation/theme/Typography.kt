package com.dat.jetpackcomposecatalog.presentation.theme


import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration

// Set of Material typography styles to start with
val Typography = Typography(
//    bodySmall = TextStyle(
//        fontFamily = FontFamily.SansSerif,
//        fontWeight = FontWeight.Normal,
//        fontSize = 14.sp,
//    ),
//    bodyMedium = TextStyle(
//        fontFamily = FontFamily.SansSerif,
//        fontWeight = FontWeight.SemiBold,
//        fontSize = 16.sp
//    ),
//    bodyLarge = TextStyle(
//        fontFamily = FontFamily.SansSerif,
//        fontWeight = FontWeight.Bold,
//        fontSize = 18.sp
//    ),
)
val TextTitleBloc = Typography.titleMedium.copy(
    textDecoration = TextDecoration.Underline,
    fontWeight = FontWeight.Bold
)
val TextHeadBloc = Typography.headlineSmall.copy(
    fontWeight = FontWeight.Bold
)
