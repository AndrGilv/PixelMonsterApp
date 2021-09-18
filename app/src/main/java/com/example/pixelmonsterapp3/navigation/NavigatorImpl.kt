package com.example.pixelmonsterapp3.navigation

import androidx.navigation.NavOptions
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject
import javax.inject.Singleton

// QrouterImpl
@Singleton
class NavigatorImpl @Inject constructor() : Navigator {

    private val navigationEvents = Channel<NavigatorEvent>()
    override val destinations = navigationEvents.receiveAsFlow()

    override fun navigateUp(): Boolean =
        navigationEvents.trySend(NavigatorEvent.NavigateUp).isSuccess

    override fun navigateBack(): Boolean =
        navigationEvents.trySend(NavigatorEvent.DefaultBackNavigation).isSuccess

    override fun navigate(route: String): Boolean =
        navigationEvents.trySend(
            NavigatorEvent.Direction(
                route = route,
            )
        ).isSuccess

    //region функции навигации в это регионе вероятнее всего не используются
    override fun navigate(
        route: String,
        navOptionsBoulder: NavOptions.Builder.() -> Unit,
        navigatorExtrasBuilder: androidx.navigation.Navigator.Extras.() -> Unit,
    ): Boolean =
        navigationEvents.trySend(
            NavigatorEvent.Direction(
                route = route,
                navOptionsBoulder = navOptionsBoulder,
                navigatorExtrasBuilder = navigatorExtrasBuilder,
            )
        ).isSuccess

    override fun navigate(
        route: String,
        navOptionsBoulder: NavOptions.Builder.() -> Unit,
    ): Boolean =
        navigationEvents.trySend(
            NavigatorEvent.Direction(
                route = route,
                navOptionsBoulder = navOptionsBoulder,
            )
        ).isSuccess

    override fun navigateBack(
        route: String,
        inclusive: Boolean,
        saveState: Boolean,
    ): Boolean =
        navigationEvents.trySend(
            NavigatorEvent.NavigateBack(
                route = route,
                inclusive = inclusive,
                saveState = saveState,
            )
        ).isSuccess
    //endregion
}
