package com.example.shared.navigation.core

import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.Navigator

sealed class NavigatorEvent {

    abstract fun handleEventBy(navHostController: NavHostController)

    object NavigateUp : NavigatorEvent() {

        override fun handleEventBy(navHostController: NavHostController) {
            navHostController.navigateUp()
        }
    }

    object DefaultBackNavigation : NavigatorEvent() {
        override fun handleEventBy(navHostController: NavHostController) {
            navHostController.popBackStack()
        }
    }

    data class NavigateBack(
        val route: String,
        val inclusive: Boolean,
        val saveState: Boolean,
    ) : NavigatorEvent() {
        override fun handleEventBy(navHostController: NavHostController) {
            navHostController.popBackStack(
                route = route,
                inclusive = inclusive,
                saveState = saveState,
            )
        }
    }

    data class Direction(
        val route: String,
        private val navOptionsBoulder: (NavOptions.Builder.() -> Unit)? = null,
        private val navigatorExtrasBuilder: (Navigator.Extras.() -> Unit)? = null,
    ) : NavigatorEvent() {
        fun createNavOptions(): NavOptions? = navOptionsBoulder?.let { builder ->
            NavOptions.Builder().apply {
                builder()
            }.build()
        }

        fun createNavExtras(): Navigator.Extras? = navigatorExtrasBuilder?.let { builder ->
            TODO("я пока не понимаю как это реализовать и для чего")
        }

        override fun handleEventBy(navHostController: NavHostController) {
            navHostController.navigate(
                route = route,
                navOptions = createNavOptions(),
                navigatorExtras = createNavExtras(),
            )
        }
    }
}
