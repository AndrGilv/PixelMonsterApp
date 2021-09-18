package com.example.pixelmonsterapp3.routers

import com.example.feature_monster_details.presentation.MonsterDetailsRouter
import com.example.shared_navigation_core.BaseRouter
import com.example.shared_navigation_core.Navigator
import javax.inject.Inject

class MonsterDetailsRouterImpl @Inject constructor(
    navigator: Navigator,
) : BaseRouter(navigator), MonsterDetailsRouter
