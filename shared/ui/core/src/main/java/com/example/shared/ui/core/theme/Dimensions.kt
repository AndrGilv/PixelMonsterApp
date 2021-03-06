package com.example.shared.ui.core.theme

import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class AppDimensions(
    val largePadding: Dp,
    val defaultPadding: Dp,
    val smallPadding: Dp,
    val extraSmallPadding: Dp,
    val largeSpacing: Dp,
    val defaultSpacing: Dp,
    val smallSpacing: Dp,
    val theme: DimensionTheme,
)

enum class DimensionTheme {
    CLASSIC,
    SHRUNKEN,
}

val classicDimensions = classicDimensions()

fun classicDimensions(
    largePadding: Dp = 24.dp,
    defaultPadding: Dp = 16.dp,
    smallPadding: Dp = 8.dp,
    extraSmallPadding: Dp = 4.dp,
    largeSpacing: Dp = 16.dp,
    defaultSpacing: Dp = 8.dp,
    smallSpacing: Dp = 4.dp,
) = AppDimensions(
    largePadding = largePadding,
    defaultPadding = defaultPadding,
    smallPadding = smallPadding,
    extraSmallPadding = extraSmallPadding,
    largeSpacing = largeSpacing,
    defaultSpacing = defaultSpacing,
    smallSpacing = smallSpacing,
    theme = DimensionTheme.CLASSIC,
)

val shrunkenDimensions = shrunkenDimensions()

fun shrunkenDimensions(
    largePadding: Dp = 8.dp,
    defaultPadding: Dp = 4.dp,
    smallPadding: Dp = 2.dp,
    extraSmallPadding: Dp = 1.dp,
    largeSpacing: Dp = 4.dp,
    defaultSpacing: Dp = 2.dp,
    smallSpacing: Dp = 1.dp,
) = AppDimensions(
    largePadding = largePadding,
    defaultPadding = defaultPadding,
    smallPadding = smallPadding,
    extraSmallPadding = extraSmallPadding,
    largeSpacing = largeSpacing,
    defaultSpacing = defaultSpacing,
    smallSpacing = smallSpacing,
    theme = DimensionTheme.CLASSIC,
)

val LocalAppDimensions: ProvidableCompositionLocal<AppDimensions> =
    staticCompositionLocalOf { classicDimensions }
