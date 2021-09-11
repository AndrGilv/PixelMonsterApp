package com.example.pixelmonsterapp3.navigation

import androidx.navigation.NavOptions
import androidx.navigation.Navigator

sealed class NavigatorEvent {
    object NavigateUp : NavigatorEvent()

    object DefaultBackNavigation: NavigatorEvent()

    data class NavigateBack(
        val route: String,
        val inclusive: Boolean,
        val saveState: Boolean,
    ) : NavigatorEvent()

    data class Direction(
        val route: String,
        private val navOptionsBoulder: (NavOptions.Builder.() -> Unit)? = null,
        private val navigatorExtrasBuilder: (Navigator.Extras.()->Unit)? = null,
    ) : NavigatorEvent(){
        fun createNavOptions(): NavOptions? = navOptionsBoulder?.let { builder ->
            NavOptions.Builder().apply {
                builder()
            }.build()
        }

        fun createNavExtras(): Navigator.Extras? = navigatorExtrasBuilder?.let { builder ->
            TODO("я пока не понимаю как это реализовать и для чего")
        }
    }
}