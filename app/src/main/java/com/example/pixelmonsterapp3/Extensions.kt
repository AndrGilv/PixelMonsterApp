package com.example.pixelmonsterapp3

import android.os.Parcelable
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.pixelmonsterapp3.domain.entity.FinishableResult
import com.example.pixelmonsterapp3.domain.entity.Finished
import com.example.pixelmonsterapp3.navigation.NavigationDestination
import com.example.pixelmonsterapp3.presentation.SideEffect
import com.example.pixelmonsterapp3.presentation.State
import kotlinx.coroutines.channels.ProducerScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import org.orbitmvi.orbit.syntax.simple.SimpleSyntax
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce

fun NavGraphBuilder.composable(
    destination: NavigationDestination,
    content: @Composable (NavBackStackEntry) -> Unit
) {
    composable(
        route = destination.name,
        arguments = destination.arguments.values.toList(),
        deepLinks = destination.deepLinks,
        content = content
    )
}

suspend fun <T> ProducerScope<T>.sendAll(flow: Flow<T>) = flow.collect { send(it) }

suspend fun <StateDate> SimpleSyntax<State<StateDate>, SideEffect>.handleFinishableResult(
    result: FinishableResult,
) {
    when (result) {
        is Finished -> postSideEffect(
            SideEffect.Finished()
        )
        is com.example.pixelmonsterapp3.domain.entity.Result.Loading -> postSideEffect(
            SideEffect.Loading(progress = result.progress)
        )
        is com.example.pixelmonsterapp3.domain.entity.Result.Error -> postSideEffect(
            SideEffect.Error(
                exception = result.exception,
                message = result.message,
            )
        )
        is com.example.pixelmonsterapp3.domain.entity.Result.Failure -> postSideEffect(
            SideEffect.Failure(message = result.message)
        )
    }
}

suspend fun <StateDate : Parcelable, ResultData> SimpleSyntax<State<StateDate>, SideEffect>.handleResult(
    result: com.example.pixelmonsterapp3.domain.entity.Result<ResultData>,
    convertToState: (result: ResultData) -> StateDate
) {
    when (result) {
        is com.example.pixelmonsterapp3.domain.entity.Result.Success -> reduce {
            State.Success(value = convertToState(result.value))
        }
        is com.example.pixelmonsterapp3.domain.entity.Result.Loading -> postSideEffect(
            SideEffect.Loading(progress = result.progress)
        )
        is com.example.pixelmonsterapp3.domain.entity.Result.Error -> postSideEffect(
            SideEffect.Error(
                exception = result.exception,
                message = result.message,
            )
        )
        is com.example.pixelmonsterapp3.domain.entity.Result.Failure -> postSideEffect(
            SideEffect.Failure(message = result.message)
        )
    }
}
