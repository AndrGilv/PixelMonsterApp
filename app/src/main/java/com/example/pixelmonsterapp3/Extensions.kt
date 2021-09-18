package com.example.pixelmonsterapp3

import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.pixelmonsterapp3.navigation.NavigationDestination
import kotlinx.coroutines.channels.ProducerScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect

fun NavGraphBuilder.composable(
    destination: NavigationDestination,
    content: @Composable (NavBackStackEntry) -> Unit
) {
    composable(
        route = destination.name,
        arguments = destination.arguments.values.toList(),
        deepLinks = destination.deepLinks,
        content = content
    )
}

suspend fun <T> ProducerScope<T>.sendAll(flow: Flow<T>) = flow.collect { send(it) }
