package com.example.shared.ui.core.theme

import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.staticCompositionLocalOf
import com.example.shared.ui.core.R

data class ThemedText(
    val title: Int,
    val theme: ThemedTextTheme,
)

enum class ThemedTextTheme {
    CLASSIC,
    SMILE,
}

val classicThemedText = classicThemedText()

fun classicThemedText(
    title: Int = R.string.app_title_classic,
) = ThemedText(
    title = title,
    theme = ThemedTextTheme.CLASSIC,
)

val smileThemedText = smileThemedText()

fun smileThemedText(
    title: Int = R.string.app_title_smile,
) = ThemedText(
    title = title,
    theme = ThemedTextTheme.SMILE,
)

val LocalThemedText: ProvidableCompositionLocal<ThemedText> =
    staticCompositionLocalOf { classicThemedText }
