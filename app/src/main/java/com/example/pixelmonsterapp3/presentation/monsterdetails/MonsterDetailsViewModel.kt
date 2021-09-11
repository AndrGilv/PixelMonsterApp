package com.example.pixelmonsterapp3.presentation.monsterdetails

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.*
import androidx.savedstate.SavedStateRegistryOwner
import com.example.pixelmonsterapp3.domain.entity.*
import com.example.pixelmonsterapp3.domain.usecase.DeleteMonsterUseCase
import com.example.pixelmonsterapp3.domain.usecase.GetMonsterDetailsUseCase
import com.example.pixelmonsterapp3.presentation.State
import dagger.assisted.*
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.collect
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.*
import org.orbitmvi.orbit.viewmodel.container

typealias MonsterDetailsState = State<MonsterDetails>
class MonsterDetailsViewModel(
    private val deleteMonsterUseCase: DeleteMonsterUseCase,
    private val getMonsterDetailsUseCase: GetMonsterDetailsUseCase,
    private val monsterId: Int,
    router: MonsterDetailsRouter,
    savedStateHandle: SavedStateHandle,
) :
    ViewModel(),
    ContainerHost<MonsterDetailsState, MonsterDetailsSideEffect>,
    MonsterDetailsRouter by router {

    override val container =
        container<MonsterDetailsState, MonsterDetailsSideEffect>(
            initialState = State.Empty(),
            savedStateHandle = savedStateHandle,
            settings = Container.Settings(
                exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
                    Log.e("error", throwable.message ?: "неизвестная ошибка")
                }
            )
        ){
            intent {
                repeatOnSubscription{
                    getMonsterDetailsUseCase(monsterId).collect(this@intent::handleResult)
                }
            }
        }

    fun updateMonsterDetails() = intent {
        getMonsterDetailsUseCase(monsterId).collect(this@intent::handleResult)
    }

    fun deleteMonster() = intent {
        deleteMonsterUseCase(monsterId).collect(this@intent::handleFinishableResult)
    }

    class MonsterDetailsFactory @AssistedInject constructor(
        @Assisted private val monsterId: Int,
        private val deleteMonsterUseCase: DeleteMonsterUseCase,
        private val getMonsterDetailsUseCase: GetMonsterDetailsUseCase,
        private val router: MonsterDetailsRouter,
        @Assisted owner: SavedStateRegistryOwner,
        @Assisted defaultArgs: Bundle?,
    ) : AbstractSavedStateViewModelFactory(
        owner,
        defaultArgs
    ) {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(
            key: String,
            modelClass: Class<T>,
            handle: SavedStateHandle,
        ): T {
            require(modelClass == MonsterDetailsViewModel::class.java)
            return MonsterDetailsViewModel(
                monsterId = monsterId,
                deleteMonsterUseCase = deleteMonsterUseCase,
                getMonsterDetailsUseCase = getMonsterDetailsUseCase,
                router = router,
                savedStateHandle = handle,
            ) as T
        }

        @AssistedFactory
        interface MonsterDetailsAssistedFactory{
            fun create(
                @Assisted monsterId: Int,
                @Assisted owner: SavedStateRegistryOwner,
                @Assisted defaultArgs: Bundle? = null
            ): MonsterDetailsFactory
        }
    }
}

private suspend fun SimpleSyntax<MonsterDetailsState, MonsterDetailsSideEffect>.handleResult(result: Result<MonsterDetails>){
    when(result){
        is Result.Success -> reduce {
            State.Success(value = result.value)
        }
        is Result.Loading -> postSideEffect(
            MonsterDetailsSideEffect.Loading(progress = result.progress)
        )
        is Result.Error -> postSideEffect(
            MonsterDetailsSideEffect.Error(
                exception = result.exception,
                message = result.message,
            )
        )
        is Result.Failure -> postSideEffect(
            MonsterDetailsSideEffect.Failure(message = result.message)
        )
    }
}

private suspend fun SimpleSyntax<MonsterDetailsState, MonsterDetailsSideEffect>.handleFinishableResult(result: FinishableResult){
    when(result){
        is Finished -> postSideEffect(
            MonsterDetailsSideEffect.Finished
        )
        is Result.Loading -> postSideEffect(
            MonsterDetailsSideEffect.Loading(progress = result.progress)
        )
        is Result.Error -> postSideEffect(
            MonsterDetailsSideEffect.Error(
                exception = result.exception,
                message = result.message,
            )
        )
        is Result.Failure -> postSideEffect(
            MonsterDetailsSideEffect.Failure(message = result.message)
        )
    }
}

