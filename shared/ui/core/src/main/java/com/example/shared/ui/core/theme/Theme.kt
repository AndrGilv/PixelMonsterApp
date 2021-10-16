package com.example.shared.ui.core.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember

object AppTheme {

    val colors: AppColors
        @Composable
        @ReadOnlyComposable
        get() = LocalAppColors.current

    val typography: AppTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalAppTypography.current

    val dimensions: AppDimensions
        @Composable
        @ReadOnlyComposable
        get() = LocalAppDimensions.current

    val shapes: AppShapes
        @Composable
        @ReadOnlyComposable
        get() = LocalAppShapes.current

    val themedText: ThemedText
        @Composable
        @ReadOnlyComposable
        get() = LocalThemedText.current

    val themedResources: ThemedResources
        @Composable
        @ReadOnlyComposable
        get() = LocalThemedResources.current

    /* val themedAnimation: ThemedAnimation
         @Composable
         @ReadOnlyComposable
         get() = LocalThemedAnimation.current*/
}

@Composable
fun Theme(
    colors: AppColors = AppTheme.colors,
    typography: AppTypography = AppTheme.typography,
    dimensions: AppDimensions = AppTheme.dimensions,
    shapes: AppShapes = AppTheme.shapes,
    themedText: ThemedText = AppTheme.themedText,
    themedResources: ThemedResources = AppTheme.themedResources,
    /*themedAnimation: ThemedAnimation = AppTheme.themedAnimation,*/
    content: @Composable () -> Unit,
) {

    val rememberedColors = remember { colors.copy() }.apply { updateColorsFrom(colors) }

    CompositionLocalProvider(
        LocalAppColors provides rememberedColors,
        LocalAppTypography provides typography,
        LocalAppDimensions provides dimensions,
        LocalAppShapes provides shapes,
        LocalThemedText provides themedText,
        LocalThemedResources provides themedResources,
        /*LocalThemedAnimation provides themedAnimation,*/
    ) {
        content()
    }
}

@Composable
fun AppTheme(
    colorTheme: ColorTheme = ColorTheme.CLASSIC,
    darkTheme: Boolean = isSystemInDarkTheme(),
    typographyTheme: TypographyTheme = TypographyTheme.CLASSIC,
    dimensionTheme: DimensionTheme = DimensionTheme.CLASSIC,
    shapesTheme: ShapesTheme = ShapesTheme.CUT,
    themedTextTheme: ThemedTextTheme = ThemedTextTheme.CLASSIC,
    themedResourcesTheme: ThemedResourcesTheme = ThemedResourcesTheme.CLASSIC,
    content: @Composable () -> Unit,
) {
    val color = when {
        colorTheme == ColorTheme.CLASSIC && !darkTheme -> classicLightColors
        colorTheme == ColorTheme.CLASSIC && darkTheme -> classicDarkColors
        colorTheme == ColorTheme.AUTUMN && !darkTheme -> autumnLightColors
        colorTheme == ColorTheme.AUTUMN && darkTheme -> autumnDarkColors
        else -> throw IllegalStateException("there is no color theme for $colorTheme theme with dark mode ${if (darkTheme) "enabled" else "disabled"}")
    }

    val typography = when (typographyTheme) {
        TypographyTheme.CLASSIC -> classicTypography
        TypographyTheme.HANDWRITTEN -> handwrittenTypography
    }

    val dimensions = when (dimensionTheme) {
        DimensionTheme.CLASSIC -> classicDimensions
        DimensionTheme.SHRUNKEN -> shrunkenDimensions
    }

    val shapes = when (shapesTheme) {
        ShapesTheme.ROUNDED -> roundedShapes
        ShapesTheme.CUT -> cutShapes
    }

    val themedText = when (themedTextTheme) {
        ThemedTextTheme.CLASSIC -> classicThemedText
        ThemedTextTheme.SMILE -> smileThemedText
    }

    val themedResources = when {
        themedResourcesTheme == ThemedResourcesTheme.CLASSIC && darkTheme -> classicDarkThemedResources
        themedResourcesTheme == ThemedResourcesTheme.CLASSIC && !darkTheme -> classicLightThemedResources
        themedResourcesTheme == ThemedResourcesTheme.MINIMALISM && darkTheme -> minimalismDarkThemedResources
        themedResourcesTheme == ThemedResourcesTheme.MINIMALISM && !darkTheme -> minimalismLightThemedResources
        else -> throw IllegalStateException("there is no themed resources theme for $themedResourcesTheme theme with dark mode ${if (darkTheme) "enabled" else "disabled"}")
    }

    Theme(
        colors = color,
        typography = typography,
        dimensions = dimensions,
        shapes = shapes,
        themedText = themedText,
        themedResources = themedResources,
        /*themedAnimation =,*/
        content = content,
    )
}
