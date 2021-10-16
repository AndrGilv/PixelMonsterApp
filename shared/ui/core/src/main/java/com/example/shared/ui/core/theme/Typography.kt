package com.example.shared.ui.core.theme

import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

data class AppTypography(
    val title: TextStyle,
    val body: TextStyle,
    val theme: TypographyTheme,
)

enum class TypographyTheme {
    CLASSIC,
    HANDWRITTEN,
}

//region classic typography
val classicTitle = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.Normal,
    fontSize = 16.sp
)
val classicBody = TextStyle(
    fontFamily = FontFamily.Default,
    fontWeight = FontWeight.Normal,
    fontSize = 16.sp
)

val classicTypography = classicTypography()

fun classicTypography(
    title: TextStyle = classicTitle,
    body: TextStyle = classicBody,
) = AppTypography(
    title = title,
    body = body,
    theme = TypographyTheme.CLASSIC,
)
//endregion

//region handwritten typography
val handwrittenTypography = handwrittenTypography()

fun handwrittenTypography(
    title: TextStyle = classicTitle,
    body: TextStyle = classicBody,
) = AppTypography(
    title = title,
    body = body,
    theme = TypographyTheme.HANDWRITTEN,
)
//endregion

val LocalAppTypography: ProvidableCompositionLocal<AppTypography> =
    staticCompositionLocalOf { classicTypography }
