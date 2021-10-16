package com.example.pixelmonsterapp3.di.annotations

import com.example.shared.navigation.core.NavigationDestination
import kotlin.reflect.KClass

annotation class Destination(
    val type: KClass<out NavigationDestination>,
)
