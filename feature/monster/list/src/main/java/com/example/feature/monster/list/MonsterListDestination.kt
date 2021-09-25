package com.example.feature.monster.list

import com.example.shared.navigation.core.NavigationDestination

object MonsterListDestination : NavigationDestination(
    domain = "monster_list"
) {

    fun createRoute() = constructRoute()
}
