package com.example.monster_list

object MonsterListDestination : com.example.shared_navigation_core.NavigationDestination(
    domain = "monster_list"
) {

    fun createRoute() = constructRoute()
}
