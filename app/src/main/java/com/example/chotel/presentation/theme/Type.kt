package com.example.chotel.presentation.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.chotel.R

// Set of Material typography styles to start with
val Typography = Typography(
    bodyLarge = TextStyle(
        fontSize = 30.sp,
        lineHeight = 36.sp,
        fontFamily = FontFamily(Font(R.font.sf_pro_display)),
        fontWeight = FontWeight(600),
        color = Color.Black,
    ),
    bodyMedium = TextStyle(
        fontSize = 16.sp,
        lineHeight = 19.2.sp,
        fontFamily = FontFamily(Font(R.font.sf_pro_display)),
        fontWeight = FontWeight(500),
    ),
    bodySmall = TextStyle(
        fontSize = 14.sp,
        lineHeight = 16.8.sp,
        fontFamily = FontFamily(Font(R.font.sf_pro_display)),
        fontWeight = FontWeight(500),
    ),
    headlineMedium = TextStyle(
        fontSize = 18.sp,
        lineHeight = 21.6.sp,
        fontFamily = FontFamily(Font(R.font.sf_pro_display)),
        fontWeight = FontWeight(500),
        color = Color.Black,
        textAlign = TextAlign.Center,
    ),
    titleMedium = TextStyle(
        fontSize = 22.sp,
        lineHeight = 26.4.sp,
        fontFamily = FontFamily(Font(R.font.sf_pro_display)),
        fontWeight = FontWeight(500),
        color = Color.Black,
    )
    /* Other default text styles to override
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)