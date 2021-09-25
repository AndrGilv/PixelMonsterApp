package com.example.feature.monster.details.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.feature.monster.details.presentation.MonsterDetailsState
import com.example.shared.monster.api.domain.entity.MonsterDetails
import com.example.shared.ui.core.TestData
import com.example.shared.ui.core.presentation.SideEffect
import com.example.shared.ui.core.presentation.State
import com.example.shared.ui.core.sreen.ScreenScaffold
import com.example.shared.ui.core.theme.PixelMonsterApp3Theme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOf

@Composable
fun MonsterDetailsScreen(
    stateFlow: StateFlow<MonsterDetailsState>,
    sideEffectFlow: Flow<SideEffect>,
    navigateBack: () -> Unit,
    deleteMonster: () -> Unit,
) = ScreenScaffold(
    stateFlow = stateFlow,
    sideEffectFlow = sideEffectFlow,
    handleSideEffects = {},
    floatingActionButton = { state ->
        when (state) {
            is State.Success -> {
                FloatingActionButton(onClick = { deleteMonster() }) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "delete")
                }
            }
            else -> Unit
        }
    }
) { state ->
    when (state) {
        is State.Success<MonsterDetails> -> {
            MonsterDetailsViewHolder(monsterDetails = state.value)
        }
        is State.Empty -> {
            EmptyMonsterDetailsViewHolder()
        }
    }
}

@Composable
fun MonsterDetailsViewHolder(
    monsterDetails: MonsterDetails,
) {
    LazyColumn(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        item { Text(text = "Details:") }
        item { Text(text = "id: ${monsterDetails.id}") }
        item { Text(text = "locationId: ${monsterDetails.locationId}") }
        item { Text(text = "element: ${monsterDetails.element}") }
        item { Text(text = "uniqueness: ${monsterDetails.uniqueness}") }
        item { Text(text = "encountered: ${monsterDetails.encountered}") }
        item { Text(text = "level: ${monsterDetails.level}") }
        item {
            Text(
                text = """
                            attributes:
                                * strength: ${monsterDetails.attributes.strength}
                                * dexterity: ${monsterDetails.attributes.dexterity}
                                * endurance: ${monsterDetails.attributes.endurance}
                                * intelligence: ${monsterDetails.attributes.intelligence}
                        """.trimIndent(),
                modifier = Modifier.padding(bottom = 2.dp)
            )
        }
        item {
            Text(
                text = """
                discoveredBy: 
                    * id: ${monsterDetails.discoveredBy.id}
                    * name: ${monsterDetails.discoveredBy.name}
            """.trimIndent()
            )
        }
        item { Text(text = "experience: ${monsterDetails.experience}") }
        item { Text(text = "attributePoints: ${monsterDetails.attributePoints}") }
        item { Text(text = "maxHealth: ${monsterDetails.maxHealth}") }
        item { Text(text = "maxDamage: ${monsterDetails.maxDamage}") }
        item { Text(text = "minDamage: ${monsterDetails.minDamage}") }
        item { Text(text = "criticalDamage: ${monsterDetails.criticalDamage}") }
        item { Text(text = "hitChance: ${monsterDetails.hitChance}") }
        item { Text(text = "dodgeChance: ${monsterDetails.dodgeChance}") }
        item { Text(text = "criticalChance: ${monsterDetails.criticalChance}") }
    }
}

@Composable
fun EmptyMonsterDetailsViewHolder() {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "id = ..."
        )
    }
}

@Preview(
    showBackground = false,
    name = "Monster details preview",
    device = "spec:Normal;1080;1920;px;320dpi;portrait",
    apiLevel = 30,
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL, locale = "ru",
    showSystemUi = true
)
@Composable
fun MonsterDetailsPreview() {
    PixelMonsterApp3Theme {
        MonsterDetailsScreen(
            stateFlow = MutableStateFlow(State.Success(value = TestData.testMonsterDetails)),
            sideEffectFlow = flowOf(),
            navigateBack = {},
            deleteMonster = {},
        )
    }
}
