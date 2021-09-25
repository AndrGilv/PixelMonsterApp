package com.example.shared.ui.core.sreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.shared.ui.core.presentation.SideEffect
import com.example.shared.ui.core.presentation.State
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect

@Composable
fun <StateData> BaseScreen(
    stateFlow: StateFlow<State<StateData>>,
    sideEffectFlow: Flow<SideEffect>,
    handleSideEffects: CoroutineScope.(sideEffect: SideEffect) -> Unit,
    content: @Composable (state: State<StateData>) -> Unit,
) {
    val state by stateFlow.collectAsState()

    LaunchedEffect(state::class.simpleName) {
        sideEffectFlow.collect {
            handleSideEffects(it)
        }
    }

    SystemBarHandlerScreen {
        content(state)
    }
}
