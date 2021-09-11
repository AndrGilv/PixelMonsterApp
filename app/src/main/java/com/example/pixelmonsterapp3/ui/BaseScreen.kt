package com.example.pixelmonsterapp3.ui

import androidx.compose.runtime.*
import com.example.pixelmonsterapp3.presentation.State
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*

@Composable
fun <StateData, CurrentState: State<StateData>, SideEffect> BaseScreen(
    stateFlow: StateFlow<CurrentState>,
    sideEffectFlow: Flow<SideEffect>,
    handleSideEffects: CoroutineScope.(sideEffect: SideEffect)-> Unit,
    content: @Composable (state: CurrentState)->Unit,
) {
    val state by stateFlow.collectAsState()

    LaunchedEffect(state::class.simpleName){
        sideEffectFlow.collect{
            handleSideEffects(it)
        }
    }

    content(state)
}