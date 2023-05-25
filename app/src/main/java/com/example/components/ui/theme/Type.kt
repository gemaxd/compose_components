package com.example.components.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.components.R

val barlow = FontFamily(
    Font(R.font.barlow_semi_condensed_thin, FontWeight.Thin),
    Font(R.font.barlow_semi_condensed_light, FontWeight.Light),
    Font(R.font.barlow_semi_condensed_medium, FontWeight.Medium),
    Font(R.font.barlow_semi_condensed_bold, FontWeight.Bold),
    Font(R.font.barlow_semi_condensed_black, FontWeight.Black)
)

// Set of Material typography styles to start with
val Typography = Typography(
    h1 = TextStyle(
        fontFamily = barlow
    ),
    h2 = TextStyle(
        fontFamily = barlow
    ),
    h3 = TextStyle(
        fontFamily = barlow
    ),
    h4 = TextStyle(
        fontFamily = barlow
    ),
    h5 = TextStyle(
        fontFamily = barlow
    ),
    h6 = TextStyle(
        fontFamily = barlow
    ),
    subtitle1 = TextStyle(
        fontFamily = barlow
    ),
    subtitle2 = TextStyle(
        fontFamily = barlow
    ),
    body1 = TextStyle(
        fontFamily = barlow
    ),
    body2 = TextStyle(
        fontFamily = barlow
    ),
    button = TextStyle(
        fontFamily = barlow
    ),
    caption = TextStyle(
        fontFamily = barlow
    ),
    overline = TextStyle(
        fontFamily = barlow
    )


    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)