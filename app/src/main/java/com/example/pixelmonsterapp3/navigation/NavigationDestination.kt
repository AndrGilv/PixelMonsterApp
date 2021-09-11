package com.example.pixelmonsterapp3.navigation

import android.os.Bundle
import androidx.navigation.*
import androidx.navigation.compose.*
import kotlin.String

abstract class NavigationDestination(
    protected val domain: String,
    val arguments: Map<String, NamedNavArgument> = emptyMap(),
    val deepLinks: List<NavDeepLink> = emptyList()
) {

    val name: String = constructRoute(arsList = arguments.keys.map { "{$it}" })

    protected fun constructRoute(vararg ars: String) = "$domain/${ars.joinToString(separator = "/")}"
    protected fun constructRoute(arsList: List<String>) = "$domain/${arsList.joinToString(separator = "/")}"
    protected fun constructRoute() = domain
}

typealias Args = Bundle?

fun argument(
    name: String,
    builder: NavArgumentBuilder.() -> Unit
): Pair<String, NamedNavArgument> =
    name to navArgument(name, builder)
