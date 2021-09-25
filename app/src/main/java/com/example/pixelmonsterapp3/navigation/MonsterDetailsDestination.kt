package com.example.pixelmonsterapp3.navigation

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import com.example.shared.navigation.core.NavigationDestination
import com.example.shared.navigation.core.argument

private const val MONSTER_ID_ARGS_KEY = "monster_id"

object MonsterDetailsDestination : NavigationDestination(
    domain = "monster_details",
    arguments = mapOf(
        argument(MONSTER_ID_ARGS_KEY) {
            type = NavType.IntType
        }
    )
) {

    fun createRoute(id: Int) = constructRoute(id.toString())

    @Composable
    fun getArgs(bundle: Bundle?, returnArgs: @Composable (id: Int) -> Unit) {
        returnArgs(
            bundle?.getInt(MONSTER_ID_ARGS_KEY)
                ?: throw IllegalStateException("не задан аргумент $MONSTER_ID_ARGS_KEY")
        )
    }
}
