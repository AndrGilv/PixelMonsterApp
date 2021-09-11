package com.example.pixelmonsterapp3.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.pixelmonsterapp3.domain.entity.MonsterDetails
import com.example.pixelmonsterapp3.presentation.State
import com.example.pixelmonsterapp3.presentation.monsterdetails.MonsterDetailsSideEffect
import com.example.pixelmonsterapp3.presentation.monsterdetails.MonsterDetailsState
import com.example.pixelmonsterapp3.ui.theme.PixelMonsterApp3Theme
import kotlinx.coroutines.flow.*

@Composable
fun MonsterDetailsScreen(
    stateFlow: StateFlow<MonsterDetailsState>,
    sideEffectFlow: Flow<MonsterDetailsSideEffect>,
    navigateBack: ()->Unit,
) = BaseScreen(stateFlow = stateFlow, sideEffectFlow = sideEffectFlow, handleSideEffects = {}) { state ->
    Column {
        Text(
            text = "details",
        )
        when(state){
            is State.Success<MonsterDetails> -> {
                val monster = state.value
                Text(
                    text = "id = ${monster.id}",
                    modifier = Modifier.clickable{
                        navigateBack()
                    }
                )
            }
            is State.Empty -> {
                Text(
                    text = "id = ...",
                    modifier = Modifier.clickable{
                        navigateBack()
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    PixelMonsterApp3Theme {
        MonsterDetailsScreen(
            stateFlow = MutableStateFlow(State.Success(value = MonsterDetails.random())),
            sideEffectFlow = flowOf(),
            navigateBack = {}
        )
    }
}