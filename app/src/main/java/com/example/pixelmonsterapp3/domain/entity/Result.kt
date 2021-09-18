package com.example.pixelmonsterapp3.domain.entity

sealed class Result<T> {
    data class Success<R>(val value: R) : Result<R>()
    data class Loading<R>(val progress: Double = 0.0) : Result<R>()
    data class Error<R>(val exception: Throwable, val message: String) : Result<R>()
    data class Failure<R>(val message: String) : Result<R>()

    companion object {
        val Finished = Finished(Unit)
    }
}

typealias Finished = Result.Success<Unit>
typealias FinishableResult = Result<Unit>
