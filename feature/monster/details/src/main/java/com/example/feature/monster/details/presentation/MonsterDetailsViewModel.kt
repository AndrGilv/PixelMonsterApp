package com.example.feature.monster.details.presentation

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.example.shared.monster.api.domain.entity.MonsterDetails
import com.example.shared.monster.api.domain.usecase.DeleteMonsterUseCase
import com.example.shared.monster.api.domain.usecase.GetMonsterDetailsUseCase
import com.example.shared.ui.core.handleFinishableResult
import com.example.shared.ui.core.handleResult
import com.example.shared.ui.core.presentation.SideEffect
import com.example.shared.ui.core.presentation.State
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.collect
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.repeatOnSubscription
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Singleton

typealias MonsterDetailsState = State<MonsterDetails>

class MonsterDetailsViewModel(
    private val deleteMonsterUseCase: DeleteMonsterUseCase,
    private val getMonsterDetailsUseCase: GetMonsterDetailsUseCase,
    private val monsterId: Int,
    router: MonsterDetailsRouter,
    savedStateHandle: SavedStateHandle,
) :
    ViewModel(),
    ContainerHost<MonsterDetailsState, SideEffect>,
    MonsterDetailsRouter by router {

    override val container =
        container<MonsterDetailsState, SideEffect>(
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
                    getMonsterDetailsUseCase(monsterId).collect { result ->
                        handleResult(
                            result = result,
                            convertToState = { it }
                        )
                    }
                }
            }
        }

    fun updateMonsterDetails() = intent {
        getMonsterDetailsUseCase(monsterId).collect { result ->
            handleResult(
                result = result,
                convertToState = { it }
            )
        }
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
        @Singleton
        interface MonsterDetailsAssistedFactory {
            fun create(
                @Assisted monsterId: Int,
                @Assisted owner: SavedStateRegistryOwner,
                @Assisted defaultArgs: Bundle? = null,
            ): MonsterDetailsFactory
        }
    }
}
