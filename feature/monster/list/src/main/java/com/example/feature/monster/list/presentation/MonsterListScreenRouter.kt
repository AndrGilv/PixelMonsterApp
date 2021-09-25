package com.example.feature.monster.list.presentation

import com.example.shared.navigation.core.Router

// SomeFeatureRouter
interface MonsterListScreenRouter : Router {

    fun navigateToMonsterDetails(id: Int): Boolean
}
