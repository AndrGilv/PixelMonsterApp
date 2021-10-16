package com.example.pixelmonsterapp3.routers

import com.example.feature.monster.details.presentation.MonsterDetailsRouter
import com.example.shared.navigation.core.BaseRouter
import com.example.shared.navigation.core.Navigator
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MonsterDetailsRouterImpl @Inject constructor(
    navigator: Navigator,
) : BaseRouter(navigator), MonsterDetailsRouter
