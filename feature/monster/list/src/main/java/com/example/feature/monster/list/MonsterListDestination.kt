package com.example.feature.monster.list

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.platform.LocalSavedStateRegistryOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import com.example.feature.monster.list.presentation.MonsterListViewModel
import com.example.feature.monster.list.ui.MonsterListScreen
import com.example.shared.navigation.core.NavigationDestination
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@Stable
class MonsterListDestination @Inject constructor(
    private val assistedFactory: MonsterListViewModel.MonsterListFactory.MonsterListAssistedFactory,
) : NavigationDestination() {

    override val startDestination: Boolean = true

    fun createRoute() = constructRoute()

    @Composable
    override fun Content(backStackEntry: NavBackStackEntry) {

        val monsterListViewModel = viewModel<MonsterListViewModel>(
            factory = assistedFactory.create(LocalSavedStateRegistryOwner.current)
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
}
