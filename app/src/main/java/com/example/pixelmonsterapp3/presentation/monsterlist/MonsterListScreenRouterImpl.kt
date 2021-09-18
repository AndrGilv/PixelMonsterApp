package com.example.pixelmonsterapp3.presentation.monsterlist

import com.example.pixelmonsterapp3.navigation.BaseRouter
import com.example.pixelmonsterapp3.navigation.MonsterDetailsDestination
import com.example.pixelmonsterapp3.navigation.Navigator
import javax.inject.Inject

// SomeFeatureRouterImpl
class MonsterListScreenRouterImpl @Inject constructor(
    navigator: Navigator,
) : BaseRouter(navigator), MonsterListScreenRouter {

    override fun navigateToMonsterDetails(id: Int): Boolean =
        navigator.navigate(route = MonsterDetailsDestination.createRoute(id))
}
