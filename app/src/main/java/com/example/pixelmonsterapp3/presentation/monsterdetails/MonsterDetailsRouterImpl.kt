package com.example.pixelmonsterapp3.presentation.monsterdetails

import com.example.pixelmonsterapp3.navigation.Navigator
import com.example.pixelmonsterapp3.navigation.BaseRouter
import javax.inject.Inject

class MonsterDetailsRouterImpl @Inject constructor(
    navigator: Navigator
) : BaseRouter(navigator), MonsterDetailsRouter {
}