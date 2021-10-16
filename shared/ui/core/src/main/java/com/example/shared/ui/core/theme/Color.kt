package com.example.shared.ui.core.theme

import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color

open class AppColors(
    primary: Color,
    primaryDark: Color,
    surface: Color,
    background: Color,
    secondary: Color,
    onPrimary: Color,
    onSurface: Color,
    onSecondary: Color,
    isLight: Boolean,
    colorTheme: ColorTheme,
) {

    var primary by mutableStateOf(primary)
        private set
    var primaryDark by mutableStateOf(primaryDark)
        private set
    var surface by mutableStateOf(surface)
        private set
    var background by mutableStateOf(background)
        private set
    var secondary by mutableStateOf(secondary)
        private set
    var onPrimary by mutableStateOf(onPrimary)
        private set
    var onSurface by mutableStateOf(onSurface)
        private set
    var onSecondary by mutableStateOf(onSecondary)
        private set
    var isLight by mutableStateOf(isLight)
        private set
    var colorTheme by mutableStateOf(colorTheme)
        private set

    fun contentColorFor(backgroundColor: Color): Color {
        return when (backgroundColor) {
            primary -> onPrimary
            primaryDark -> onPrimary
            secondary -> onSecondary
            surface -> onSurface
            background -> onSurface
            else -> throw IllegalStateException("onColor equivalent not defined for $backgroundColor")
        }
    }

    fun updateColorsFrom(other: AppColors) {
        primary = other.primary
        primaryDark = other.primaryDark
        surface = other.surface
        background = other.background
        secondary = other.secondary
        onPrimary = other.onPrimary
        onSurface = other.onSurface
        onSecondary = other.onSecondary
        isLight = other.isLight
        colorTheme = other.colorTheme
    }

    fun copy(
        primary: Color = this.primary,
        primaryDark: Color = this.primaryDark,
        surface: Color = this.surface,
        background: Color = this.background,
        secondary: Color = this.secondary,
        onPrimary: Color = this.onPrimary,
        onSurface: Color = this.onSurface,
        onSecondary: Color = this.onSecondary,
        isLight: Boolean = this.isLight,
        colorTheme: ColorTheme = this.colorTheme,
    ) = AppColors(
        primary = primary,
        primaryDark = primaryDark,
        surface = surface,
        background = background,
        secondary = secondary,
        onPrimary = onPrimary,
        onSurface = onSurface,
        onSecondary = onSecondary,
        isLight = isLight,
        colorTheme = colorTheme,
    )
}

enum class ColorTheme {
    CLASSIC,
    AUTUMN,
}

val white = Color(0xFFFFFFFF)
val black = Color(0xFF000000)

//region classic light palette
// palette from https://coolors.co/e63946-f1faee-a8dadc-457b9d-1d3557
val imperialRed = Color(0xFFE63946) // secondary
val honeydew = Color(0xFFD3EDEE) // surface
val powderBlue = Color(0xFFA8DADC) // background
val celadonBlue = Color(0xFF457B9D) // primary
val prussianBlue = Color(0xFF1D3557) // primary dark

var classicLightColors = classicLightColors()
fun classicLightColors(
    primary: Color = celadonBlue,
    primaryDark: Color = prussianBlue,
    surface: Color = honeydew,
    background: Color = powderBlue,
    secondary: Color = imperialRed,
    onPrimary: Color = white,
    onSurface: Color = black,
    onSecondary: Color = white,
) = AppColors(
    primary = primary,
    primaryDark = primaryDark,
    surface = surface,
    background = background,
    secondary = secondary,
    onPrimary = onPrimary,
    onSurface = onSurface,
    onSecondary = onSecondary,
    isLight = true,
    colorTheme = ColorTheme.CLASSIC,
)
//endregion

//region classic dark palette
// palette from https://coolors.co/03224c-042b62-021127-010914-720910
val upMaroon = Color(0xFF720910)
val richBlackFogra29 = Color(0xFF010914)
val oxfordBlueDark = Color(0xFF021127)
val royalBlueDark = Color(0xFF042B62)
val oxfordBlue = Color(0xFF03224C)

val classicDarkColors = classicDarkColors()
fun classicDarkColors(
    primary: Color = royalBlueDark,
    primaryDark: Color = oxfordBlue,
    surface: Color = richBlackFogra29,
    background: Color = oxfordBlueDark,
    secondary: Color = upMaroon,
    onPrimary: Color = white,
    onSurface: Color = white,
    onSecondary: Color = white,
) = AppColors(
    primary = primary,
    primaryDark = primaryDark,
    surface = surface,
    background = background,
    secondary = secondary,
    onPrimary = onPrimary,
    onSurface = onSurface,
    onSecondary = onSecondary,
    isLight = false,
    colorTheme = ColorTheme.CLASSIC,
)
//endregion

//region autumn light palette
// palette from https://coolors.co/606c38-283618-fefae0-dda15e-bc6c25
val liverDogs = Color(0xFFBC6C25)
val fawn = Color(0xFFDDA15E)
val cornsilk = Color(0xFFFEFAE0)
val kombuGreen = Color(0xFF283618)
val darkOliveGreen = Color(0xFF606C38)

val autumnLightColors = autumnLightColors()
fun autumnLightColors(
    primary: Color = kombuGreen,
    primaryDark: Color = darkOliveGreen,
    surface: Color = cornsilk,
    background: Color = fawn,
    secondary: Color = liverDogs,
    onPrimary: Color = white,
    onSurface: Color = black,
    onSecondary: Color = white,
) = AppColors(
    primary = primary,
    primaryDark = primaryDark,
    surface = surface,
    background = background,
    secondary = secondary,
    onPrimary = onPrimary,
    onSurface = onSurface,
    onSecondary = onSecondary,
    isLight = true,
    colorTheme = ColorTheme.AUTUMN,
)
//endregion

//region autumn dark palette
// palette from https://coolors.co/063a21-12562a-000000-45040c-720714
val prussianPlum = Color(0xFF720714)
val blackBean = Color(0xFF45040C)
val eerieBlack = Color(0xFF141414)
val hunterGreen = Color(0xFF12562A)
val britishRAcingGreen = Color(0xFF063A21)

val autumnDarkColors = autumnDarkColors()

fun autumnDarkColors(
    primary: Color = hunterGreen,
    primaryDark: Color = britishRAcingGreen,
    surface: Color = eerieBlack,
    background: Color = blackBean,
    secondary: Color = prussianPlum,
    onPrimary: Color = white,
    onSurface: Color = white,
    onSecondary: Color = white,
) = AppColors(
    primary = primary,
    primaryDark = primaryDark,
    surface = surface,
    background = background,
    secondary = secondary,
    onPrimary = onPrimary,
    onSurface = onSurface,
    onSecondary = onSecondary,
    isLight = false,
    colorTheme = ColorTheme.CLASSIC,
)
//endregion

@Composable
@ReadOnlyComposable
fun contentColorFor(backgroundColor: Color) =
    AppTheme.colors.contentColorFor(backgroundColor)

val LocalAppColors: ProvidableCompositionLocal<AppColors> =
    staticCompositionLocalOf { classicLightColors }
