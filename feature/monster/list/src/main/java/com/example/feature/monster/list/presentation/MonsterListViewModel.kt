package com.example.feature.monster.list.presentation

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.example.shared.monster.api.domain.entity.Monster
import com.example.shared.monster.api.domain.usecase.DeleteAllMonstersUseCase
import com.example.shared.monster.api.domain.usecase.DeleteMonsterUseCase
import com.example.shared.monster.api.domain.usecase.GenerateRandomMonsterUseCase
import com.example.shared.monster.api.domain.usecase.GetGeneratedMonsterListFlowUseCase
import com.example.shared.ui.core.handleFinishableResult
import com.example.shared.ui.core.handleResult
import com.example.shared.ui.core.presentation.ParcelableList
import com.example.shared.ui.core.presentation.SideEffect
import com.example.shared.ui.core.presentation.State
import com.example.shared.ui.core.presentation.toParcelableList
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

typealias MonsterListState = State<ParcelableList<Monster>>

// SomePresenter
class MonsterListViewModel(
    private val deleteAllMonstersUseCase: DeleteAllMonstersUseCase,
    private val deleteMonsterUseCase: DeleteMonsterUseCase,
    private val generateRandomMonsterUseCase: GenerateRandomMonsterUseCase,
    private val getGeneratedMonsterListFlowUseCase: GetGeneratedMonsterListFlowUseCase,
    router: MonsterListScreenRouter,
    savedStateHandle: SavedStateHandle,
) :
    ViewModel(),
    ContainerHost<MonsterListState, SideEffect>,
    MonsterListScreenRouter by router {

    override val container =
        container<MonsterListState, SideEffect>(
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
                    getGeneratedMonsterListFlowUseCase().collect {
                        handleResult(
                            result = it,
                            convertToState = { it.toParcelableList() }
                        )
                    }
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
        @Singleton
        interface MonsterListAssistedFactory {
            fun create(
                @Assisted owner: SavedStateRegistryOwner,
                @Assisted defaultArgs: Bundle? = null,
            ): MonsterListFactory
        }
    }
}
