package com.technocorp.mqutqaruv.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.technocorp.mqutqaruv.R

val fonts = FontFamily(
    Font(R.font.roboto_regular, weight = FontWeight.W500),
    Font(R.font.roboto_medium, weight = FontWeight.W600),
    Font(R.font.roboto_bold, weight = FontWeight.Bold),
)

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.W500,
        fontSize = 16.sp
    ),
    body2 = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp
    ),
    h1 = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    h2 = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp
    ),
    h3 = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp
    ),
    h4 = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp
    ),
    h5 = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.Bold,
        fontSize = 34.sp
    ),
    h6 = TextStyle(
        fontFamily = fonts,
        fontWeight = FontWeight.W500,
        fontSize = 12.sp
    ),
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