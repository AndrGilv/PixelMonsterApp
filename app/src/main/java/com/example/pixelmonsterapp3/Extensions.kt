package com.example.pixelmonsterapp3

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.shared.navigation.core.NavigationDestination

fun NavGraphBuilder.composable(
    destination: NavigationDestination,
    content: @Composable (NavBackStackEntry) -> Unit,
) {
    composable(
        route = destination.name,
        arguments = destination.arguments.values.toList(),
        deepLinks = destination.deepLinks,
        content = content
    )
}
