package com.example.shared.ui.core.theme

import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp

data class AppShapes(
    val cardShape: Shape,
    val bottomShite: Shape,
    val drawerShape: Shape,
    val appBarShape: Shape,
    val theme: ShapesTheme,
)

enum class ShapesTheme {
    ROUNDED,
    CUT,
}

val roundedShapes = roundedShapes()

fun roundedShapes(
    cardShape: Shape = RoundedCornerShape(8.dp),
    bottomShite: Shape = RoundedCornerShape(topStart = 8.dp, topEnd = 8.dp),
    drawerShape: Shape = RoundedCornerShape(topEnd = 8.dp, bottomEnd = 8.dp),
    appBarShape: Shape = RoundedCornerShape(bottomStart = 8.dp, bottomEnd = 8.dp),
) = AppShapes(
    cardShape = cardShape,
    bottomShite = bottomShite,
    drawerShape = drawerShape,
    appBarShape = appBarShape,
    theme = ShapesTheme.ROUNDED,
)

val cutShapes = cutShapes()

fun cutShapes(
    cardShape: Shape = CutCornerShape(16.dp),
    bottomShite: Shape = CutCornerShape(topStart = 8.dp, topEnd = 8.dp),
    drawerShape: Shape = CutCornerShape(topEnd = 8.dp, bottomEnd = 8.dp),
    appBarShape: Shape = CutCornerShape(bottomStart = 8.dp, bottomEnd = 8.dp),
) = AppShapes(
    cardShape = cardShape,
    bottomShite = bottomShite,
    drawerShape = drawerShape,
    appBarShape = appBarShape,
    theme = ShapesTheme.CUT,
)

val LocalAppShapes: ProvidableCompositionLocal<AppShapes> =
    staticCompositionLocalOf { cutShapes }
