package com.example.shared_navigation_core

import androidx.navigation.NavOptions
import kotlinx.coroutines.flow.Flow

// QRouter
interface Navigator {

    val destinations: Flow<NavigatorEvent>

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
}
