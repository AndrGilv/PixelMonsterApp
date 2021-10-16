package com.example.pixelmonsterapp3.routers

import com.example.feature.monster.details.MonsterDetailsDestination
import com.example.feature.monster.list.presentation.MonsterListScreenRouter
import com.example.shared.navigation.core.BaseRouter
import com.example.shared.navigation.core.Navigator
import javax.inject.Inject
import javax.inject.Singleton

// SomeFeatureRouterImpl
@Singleton
class MonsterListScreenRouterImpl @Inject constructor(
    navigator: Navigator,
    private val monsterDetailsDestination: MonsterDetailsDestination,
) : BaseRouter(navigator), MonsterListScreenRouter {

    override fun navigateToMonsterDetails(id: Int): Boolean =
        navigator.navigate(route = monsterDetailsDestination.createRoute(id))
}
