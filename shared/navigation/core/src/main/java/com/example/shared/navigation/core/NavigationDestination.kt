package com.example.shared.navigation.core

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavArgumentBuilder
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDeepLink
import androidx.navigation.navArgument

@Stable
abstract class NavigationDestination {

    open val startDestination: Boolean = false
    open val domain: String =
        this::class.simpleName ?: throw IllegalStateException("destination name not set")
    open val arguments: Map<String, NamedNavArgument> = emptyMap()
    open val deepLinks: List<NavDeepLink> = emptyList()

    val name: String by lazy { constructRoute(ars = arguments.keys.map { "{$it}" }.toTypedArray()) }

    protected fun constructRoute(vararg ars: String): String {
        return if (ars.isEmpty()) {
            domain
        } else {
            ars.joinToString(separator = "/", prefix = "$domain/")
        }
    }

    @Composable
    abstract fun Content(backStackEntry: NavBackStackEntry)
}

typealias Args = Bundle?

fun argument(
    name: String,
    builder: NavArgumentBuilder.() -> Unit,
): Pair<String, NamedNavArgument> =
    name to navArgument(name, builder)

fun Args.getInt(key: String) =
    this?.getInt(key) ?: throw IllegalStateException("argument $key not set")
