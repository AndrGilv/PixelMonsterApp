package com.example.pixelmonsterapp3.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavOptions
import androidx.navigation.Navigator.Extras
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.shared.navigation.core.NavigationDestination
import com.example.shared.navigation.core.Navigator
import com.example.shared.navigation.core.NavigatorEvent
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject
import javax.inject.Singleton

// QrouterImpl
@Singleton
@Stable
class NavigatorImpl @Inject constructor(
    override val destinations: Set<@JvmSuppressWildcards NavigationDestination>,
) : Navigator {

    private val navigationEvents = Channel<NavigatorEvent>()
    override val navigationEventsFlow = navigationEvents.receiveAsFlow()

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
        navigatorExtrasBuilder: Extras.() -> Unit,
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

    @Composable
    override fun Content() {

        val navHostController = rememberNavController()

        LaunchedEffect("navigation") {
            navigationEventsFlow.onEach { event ->
                event.handleEventBy(navHostController)
            }.launchIn(this)
        }

        val rememberedNavigator = remember { this }
        val rememberedStartDestination = remember {
            destinations.find { it.startDestination }
                ?: throw IllegalStateException("no start destination found")
        }

        NavHost(
            navController = navHostController,
            startDestination = rememberedStartDestination.name,
        ) {
            rememberedNavigator.destinations.forEach { destination ->
                composable(
                    route = destination.name,
                    arguments = destination.arguments.values.toList(),
                    deepLinks = destination.deepLinks,
                    content = { destination.Content(it) }
                )
            }
        }
    }
}
