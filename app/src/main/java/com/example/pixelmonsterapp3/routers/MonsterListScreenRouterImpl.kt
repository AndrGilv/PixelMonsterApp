package com.example.pixelmonsterapp3.routers

import com.example.monster_list.presentation.MonsterListScreenRouter
import com.example.pixelmonsterapp3.navigation.MonsterDetailsDestination
import com.example.shared_navigation_core.BaseRouter
import com.example.shared_navigation_core.Navigator
import javax.inject.Inject

// SomeFeatureRouterImpl
class MonsterListScreenRouterImpl @Inject constructor(
    navigator: Navigator,
) : BaseRouter(navigator), MonsterListScreenRouter {

    override fun navigateToMonsterDetails(id: Int): Boolean =
        navigator.navigate(route = MonsterDetailsDestination.createRoute(id))
}
