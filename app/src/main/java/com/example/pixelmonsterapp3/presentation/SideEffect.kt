package com.example.pixelmonsterapp3.presentation

sealed class SideEffect {
    data class Loading(val progress: Double) : SideEffect()
    data class Finished(val message: String = "") : SideEffect()
    data class Error(val exception: Throwable, val message: String) : SideEffect()
    data class Failure(val message: String) : SideEffect()
    data class Info(val message: String)
}
