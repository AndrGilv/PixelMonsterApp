package com.example.shared_ui_core

import android.os.Parcelable
import com.example.shared_monster_api.domain.entity.FinishableResult
import com.example.shared_monster_api.domain.entity.Finished
import com.example.shared_monster_api.domain.entity.Result
import com.example.shared_ui_core.presentation.SideEffect
import com.example.shared_ui_core.presentation.State
import org.orbitmvi.orbit.syntax.simple.SimpleSyntax
import org.orbitmvi.orbit.syntax.simple.postSideEffect
import org.orbitmvi.orbit.syntax.simple.reduce

suspend fun <StateDate> SimpleSyntax<State<StateDate>, SideEffect>.handleFinishableResult(
    result: FinishableResult,
) {
    when (result) {
        is Finished -> postSideEffect(
            SideEffect.Finished()
        )
        is Result.Loading -> postSideEffect(
            SideEffect.Loading(progress = result.progress)
        )
        is Result.Error -> postSideEffect(
            SideEffect.Error(
                exception = result.exception,
                message = result.message,
            )
        )
        is Result.Failure -> postSideEffect(
            SideEffect.Failure(message = result.message)
        )
    }
}

suspend fun <StateDate : Parcelable, ResultData> SimpleSyntax<State<StateDate>, SideEffect>.handleResult(
    result: Result<ResultData>,
    convertToState: (result: ResultData) -> StateDate,
) {
    when (result) {
        is Result.Success -> reduce {
            State.Success(value = convertToState(result.value))
        }
        is Result.Loading -> postSideEffect(
            SideEffect.Loading(progress = result.progress)
        )
        is Result.Error -> postSideEffect(
            SideEffect.Error(
                exception = result.exception,
                message = result.message,
            )
        )
        is Result.Failure -> postSideEffect(
            SideEffect.Failure(message = result.message)
        )
    }
}
