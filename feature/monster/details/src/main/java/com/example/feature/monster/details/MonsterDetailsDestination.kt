package com.example.feature.monster.details

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.platform.LocalSavedStateRegistryOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavType
import com.example.feature.monster.details.presentation.MonsterDetailsViewModel
import com.example.feature.monster.details.ui.MonsterDetailsScreen
import com.example.shared.navigation.core.NavigationDestination
import com.example.shared.navigation.core.argument
import com.example.shared.navigation.core.getInt
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@Stable
class MonsterDetailsDestination @Inject constructor(
    private val assistedFactory: MonsterDetailsViewModel.MonsterDetailsFactory.MonsterDetailsAssistedFactory,
) : NavigationDestination() {

    companion object {
        private val MONSTER_ID_ARGS_KEY = "monster_id"
    }

    override val arguments = mapOf(
        argument(MONSTER_ID_ARGS_KEY) {
            type = NavType.IntType
        }
    )

    fun createRoute(id: Int) = constructRoute(id.toString())

    @Composable
    override fun Content(backStackEntry: NavBackStackEntry) {
        val monsterDetailsViewModel = viewModel<MonsterDetailsViewModel>(
            factory = assistedFactory.create(
                monsterId = backStackEntry.arguments.getInt(MONSTER_ID_ARGS_KEY),
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
