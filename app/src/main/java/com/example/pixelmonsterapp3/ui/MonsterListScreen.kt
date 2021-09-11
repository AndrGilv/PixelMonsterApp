package com.example.pixelmonsterapp3.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.pixelmonsterapp3.domain.entity.Monster
import com.example.pixelmonsterapp3.presentation.State
import com.example.pixelmonsterapp3.presentation.monsterlist.MonsterListScreenSideEffect
import com.example.pixelmonsterapp3.presentation.monsterlist.MonsterListState
import com.example.pixelmonsterapp3.presentation.toParcelableList
import com.example.pixelmonsterapp3.ui.theme.PixelMonsterApp3Theme
import kotlinx.coroutines.flow.*

@Composable
fun MonsterListScreen(
    stateFlow: StateFlow<MonsterListState>,
    sideEffectFlow: Flow<MonsterListScreenSideEffect>,
    navigateToDetails: (id: Int)->Unit,
) = BaseScreen(stateFlow = stateFlow, sideEffectFlow = sideEffectFlow, handleSideEffects = {}) { state ->
    LazyColumn{
        item {
            Text(
                text = "list:",
            )
        }
        if(state is State.Success){
            items(state.value){ monster ->
                Text(
                    text = "id = ${monster.id}",
                    modifier = Modifier.clickable{
                        navigateToDetails(monster.id)
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MonsterListScreenPreview() {
    PixelMonsterApp3Theme {
        MonsterListScreen(
            stateFlow = MutableStateFlow(State.Success(value = Monster.randomList().toParcelableList())),
            sideEffectFlow = flowOf(),
            navigateToDetails = {},
        )
    }
}