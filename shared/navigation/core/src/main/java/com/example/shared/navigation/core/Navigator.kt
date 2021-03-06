package com.example.shared.navigation.core

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.navigation.NavOptions
import kotlinx.coroutines.flow.Flow

// QRouter
@Stable
interface Navigator {

    val destinations: Set<NavigationDestination>

    val navigationEventsFlow: Flow<NavigatorEvent>

    fun navigateUp(): Boolean

    fun navigate(
        route: String,
        navOptionsBoulder: (NavOptions.Builder.() -> Unit),
        navigatorExtrasBuilder: (androidx.navigation.Navigator.Extras.() -> Unit),
    ): Boolean

    fun navigate(
        route: String,
        navOptionsBoulder: NavOptions.Builder.() -> Unit,
    ): Boolean

    fun navigate(
        route: String,
    ): Boolean

    fun navigateBack(
        route: String,
        inclusive: Boolean,
        saveState: Boolean,
    ): Boolean

    fun navigateBack(): Boolean

    @Composable
    fun Content()
}
