package com.example.pixelmonsterapp3.presentation.monsterlist

import android.os.Bundle
import android.util.Log
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.*
import androidx.savedstate.SavedStateRegistryOwner
import com.example.pixelmonsterapp3.domain.entity.*
import com.example.pixelmonsterapp3.domain.usecase.*
import com.example.pixelmonsterapp3.presentation.*
import dagger.assisted.*
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.collect
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.*
import org.orbitmvi.orbit.viewmodel.container

typealias MonsterListState = State<ParcelableList<Monster>>

class MonsterListViewModel(
    private val deleteAllMonstersUseCase: DeleteAllMonstersUseCase,
    private val deleteMonsterUseCase: DeleteMonsterUseCase,
    private val generateRandomMonsterUseCase: GenerateRandomMonsterUseCase,
    private val getGeneratedMonsterListFlowUseCase: GetGeneratedMonsterListFlowUseCase,
    router: MonsterListScreenRouter,
    savedStateHandle: SavedStateHandle,
) :
    ViewModel(),
    ContainerHost<MonsterListState, MonsterListScreenSideEffect>,
    MonsterListScreenRouter by router {

    override val container =
        container<MonsterListState, MonsterListScreenSideEffect>(
            initialState = State.Empty(),
            savedStateHandle = savedStateHandle,
            settings = Container.Settings(
                exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
                    Log.e("error", throwable.message ?: "неизвестная ошибка")
                }
            )
        ) {
            intent {
                repeatOnSubscription {
                    getGeneratedMonsterListFlowUseCase().collect(this@intent::handleResult)
                }
            }
        }

    fun generateRandomMonster() = intent {
        generateRandomMonsterUseCase().collect(this::handleFinishableResult)
    }

    fun deleteMonster(id: Int) = intent {
        deleteMonsterUseCase(id).collect(this::handleFinishableResult)
    }

    fun deleteAllMonsters() = intent {
        deleteAllMonstersUseCase().collect(this::handleFinishableResult)
    }

    class MonsterListFactory @AssistedInject constructor(
        private val deleteAllMonstersUseCase: DeleteAllMonstersUseCase,
        private val deleteMonsterUseCase: DeleteMonsterUseCase,
        private val generateRandomMonsterUseCase: GenerateRandomMonsterUseCase,
        private val getGeneratedMonsterListFlowUseCase: GetGeneratedMonsterListFlowUseCase,
        private val router: MonsterListScreenRouter,
        @Assisted owner: SavedStateRegistryOwner,
        @Assisted defaultArgs: Bundle?,
    ) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(
            key: String,
            modelClass: Class<T>,
            handle: SavedStateHandle,
        ): T {
            require(modelClass == MonsterListViewModel::class.java)
            return MonsterListViewModel(
                deleteAllMonstersUseCase = deleteAllMonstersUseCase,
                deleteMonsterUseCase = deleteMonsterUseCase,
                generateRandomMonsterUseCase = generateRandomMonsterUseCase,
                getGeneratedMonsterListFlowUseCase = getGeneratedMonsterListFlowUseCase,
                router = router,
                savedStateHandle = handle,
            ) as T
        }

        @AssistedFactory
        interface MonsterListAssistedFactory {
            fun create(
                @Assisted owner: SavedStateRegistryOwner,
                @Assisted defaultArgs: Bundle? = null,
            ): MonsterListFactory
        }
    }
}

private suspend fun SimpleSyntax<MonsterListState, MonsterListScreenSideEffect>.handleFinishableResult(
    result: FinishableResult,
) {
    when (result) {
        is Finished -> postSideEffect(
            MonsterListScreenSideEffect.Finished
        )
        is Result.Loading -> postSideEffect(
            MonsterListScreenSideEffect.Loading(progress = result.progress)
        )
        is Result.Error -> postSideEffect(
            MonsterListScreenSideEffect.Error(
                exception = result.exception,
                message = result.message,
            )
        )
        is Result.Failure -> postSideEffect(
            MonsterListScreenSideEffect.Failure(message = result.message)
        )
    }
}

private suspend fun SimpleSyntax<MonsterListState, MonsterListScreenSideEffect>.handleResult(
    result: Result<List<Monster>>
) {
    when (result) {
        is Result.Success -> reduce {
            State.Success(value = result.value.toParcelableList())
        }
        is Result.Loading -> postSideEffect(
            MonsterListScreenSideEffect.Loading(progress = result.progress)
        )
        is Result.Error -> postSideEffect(
            MonsterListScreenSideEffect.Error(
                exception = result.exception,
                message = result.message,
            )
        )
        is Result.Failure -> postSideEffect(
            MonsterListScreenSideEffect.Failure(message = result.message)
        )
    }
}