package com.example.shared.ui.core.theme

import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf
import com.example.shared.ui.core.R

data class ThemedResources(
    val previewBackgroundImage: Int,
    val isLight: Boolean,
    val theme: ThemedResourcesTheme,
)

enum class ThemedResourcesTheme {
    CLASSIC,
    MINIMALISM,
}

val classicLightThemedResources = classicLightThemedResources()

fun classicLightThemedResources(
    previewBackgroundImage: Int = R.drawable.car_image_light,
) = ThemedResources(
    previewBackgroundImage = previewBackgroundImage,
    isLight = true,
    theme = ThemedResourcesTheme.CLASSIC,
)

val classicDarkThemedResources = classicDarkThemedResources()

fun classicDarkThemedResources(
    previewBackgroundImage: Int = R.drawable.car_image_dark,
) = ThemedResources(
    previewBackgroundImage = previewBackgroundImage,
    isLight = false,
    theme = ThemedResourcesTheme.CLASSIC,
)

val minimalismLightThemedResources = minimalismLightThemedResources()

fun minimalismLightThemedResources(
    previewBackgroundImage: Int = R.drawable.planet_light,
) = ThemedResources(
    previewBackgroundImage = previewBackgroundImage,
    isLight = true,
    theme = ThemedResourcesTheme.MINIMALISM,
)

val minimalismDarkThemedResources = minimalismDarkThemedResources()

fun minimalismDarkThemedResources(
    previewBackgroundImage: Int = R.drawable.planet_dark,
) = ThemedResources(
    previewBackgroundImage = previewBackgroundImage,
    isLight = false,
    theme = ThemedResourcesTheme.MINIMALISM,
)

val LocalThemedResources: ProvidableCompositionLocal<ThemedResources> =
    staticCompositionLocalOf { classicLightThemedResources }
