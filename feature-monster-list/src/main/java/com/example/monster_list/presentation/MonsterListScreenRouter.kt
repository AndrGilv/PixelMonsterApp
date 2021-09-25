package com.example.monster_list.presentation

import com.example.shared_navigation_core.Router

// SomeFeatureRouter
interface MonsterListScreenRouter : Router {

    fun navigateToMonsterDetails(id: Int): Boolean
}
