package com.example.pixelmonsterapp3.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalSavedStateRegistryOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.feature.monster.details.presentation.MonsterDetailsViewModel
import com.example.feature.monster.details.ui.MonsterDetailsScreen
import com.example.feature.monster.list.MonsterListDestination
import com.example.feature.monster.list.presentation.MonsterListViewModel
import com.example.feature.monster.list.ui.MonsterListScreen
import com.example.pixelmonsterapp3.composable
import com.example.pixelmonsterapp3.di.ViewModelFactories
import com.example.pixelmonsterapp3.navigation.MonsterDetailsDestination
import com.example.shared.navigation.core.Navigator
import com.example.shared.navigation.core.NavigatorEvent
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@Composable
fun NavigationComponent(
    navController: NavHostController,
    navigator: Navigator,
    assistedFactories: ViewModelFactories,
) {

    LaunchedEffect("navigation") {
        navigator.destinations.onEach { navigationEvent ->
            when (navigationEvent) {
                is NavigatorEvent.Direction -> navController.navigate(
                    route = navigationEvent.route,
                    navOptions = navigationEvent.createNavOptions(),
                    navigatorExtras = navigationEvent.createNavExtras(),
                )
                is NavigatorEvent.NavigateUp -> navController.navigateUp()
                is NavigatorEvent.DefaultBackNavigation -> navController.popBackStack()
                is NavigatorEvent.NavigateBack -> navController.popBackStack(
                    route = navigationEvent.route,
                    inclusive = navigationEvent.inclusive,
                    saveState = navigationEvent.saveState,
                )
            }
        }.launchIn(this)
    }

    NavHost(
        navController = navController,
        startDestination = MonsterListDestination.name
    ) {
        composable(MonsterListDestination) {

            val monsterListViewModel = viewModel<MonsterListViewModel>(
                factory = assistedFactories.monsterListViewModelAssistedFactory.create(
                    LocalSavedStateRegistryOwner.current
                )
            )

            MonsterListScreen(
                stateFlow = monsterListViewModel.container.stateFlow,
                sideEffectFlow = monsterListViewModel.container.sideEffectFlow,
                navigateToDetails = monsterListViewModel::navigateToMonsterDetails,
                addNewMonster = monsterListViewModel::generateRandomMonster,
                deleteMonster = monsterListViewModel::deleteMonster,
                deleteAllMonsters = monsterListViewModel::deleteAllMonsters,
            )
        }
        composable(MonsterDetailsDestination) { backStackEntry ->
            MonsterDetailsDestination.getArgs(backStackEntry.arguments) { id ->
                val monsterDetailsViewModel = viewModel<MonsterDetailsViewModel>(
                    factory = assistedFactories.monsterDetailsViewModelAssistedFactory.create(
                        monsterId = id,
                        owner = LocalSavedStateRegistryOwner.current
                    )
                )

                MonsterDetailsScreen(
                    stateFlow = monsterDetailsViewModel.container.stateFlow,
                    sideEffectFlow = monsterDetailsViewModel.container.sideEffectFlow,
                    navigateBack = monsterDetailsViewModel::navigateUp,
                    deleteMonster = monsterDetailsViewModel::deleteMonster,
                )
            }
        }
    }
}
