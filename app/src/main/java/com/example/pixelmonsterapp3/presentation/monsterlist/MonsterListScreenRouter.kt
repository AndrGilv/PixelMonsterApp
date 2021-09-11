package com.example.pixelmonsterapp3.presentation.monsterlist

import com.example.pixelmonsterapp3.navigation.Router

interface MonsterListScreenRouter: Router {

    fun navigateToMonsterDetails(id: Int): Boolean
}