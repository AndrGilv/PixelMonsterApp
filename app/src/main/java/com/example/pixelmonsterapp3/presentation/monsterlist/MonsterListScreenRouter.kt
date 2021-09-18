package com.example.pixelmonsterapp3.presentation.monsterlist

import com.example.pixelmonsterapp3.navigation.Router

// SomeFeatureRouter
interface MonsterListScreenRouter: Router {

    fun navigateToMonsterDetails(id: Int): Boolean
}
