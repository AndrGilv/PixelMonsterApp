package com.example.pixelmonsterapp3.presentation.monsterdetails

sealed class MonsterDetailsSideEffect{
    data class Loading(val progress: Double) : MonsterDetailsSideEffect()
    object Finished: MonsterDetailsSideEffect()
    data class Error(val exception: Throwable, val message: String) : MonsterDetailsSideEffect()
    data class Failure(val message: String) : MonsterDetailsSideEffect()
}