package com.example.pixelmonsterapp3.presentation.monsterlist

sealed class MonsterListScreenSideEffect {
    data class Loading(val progress: Double) : MonsterListScreenSideEffect()
    object Finished : MonsterListScreenSideEffect()
    data class Error(val exception: Throwable, val message: String) : MonsterListScreenSideEffect()
    data class Failure(val message: String) : MonsterListScreenSideEffect()
}