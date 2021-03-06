package com.example.feature.monster.list.ui

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.feature.monster.list.presentation.MonsterListState
import com.example.shared.monster.api.domain.entity.Monster
import com.example.shared.ui.core.TestData
import com.example.shared.ui.core.presentation.SideEffect
import com.example.shared.ui.core.presentation.State
import com.example.shared.ui.core.sreen.ScreenScaffold
import com.example.shared.ui.core.theme.AppTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOf

@Composable
fun MonsterListScreen(
    stateFlow: StateFlow<MonsterListState>,
    sideEffectFlow: Flow<SideEffect>,
    navigateToDetails: (id: Int) -> Unit,
    addNewMonster: () -> Unit,
    deleteAllMonsters: () -> Unit,
    deleteMonster: (id: Int) -> Unit,
) = ScreenScaffold(
    stateFlow = stateFlow,
    sideEffectFlow = sideEffectFlow,
    handleSideEffects = { sideEffect ->

    },
    floatingActionButton = {
        Column {
            FloatingActionButton(onClick = { addNewMonster() }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "add")
            }
            Spacer(modifier = Modifier.height(16.dp))
            FloatingActionButton(onClick = { deleteAllMonsters() }) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "deleteAll")
            }
        }
    }
) { state ->
    AnimatedVisibility(visible = state is State.Success) {
        require(state is State.Success)
        LazyColumn {
            item {
                Text(
                    text = "list:",
                    modifier = Modifier.padding(AppTheme.dimensions.largePadding)
                )
            }
            items(
                items = state.value,
                key = { monster -> monster.id }
            ) { monster ->
                MonsterViewHolder(
                    monster = monster,
                    onClicked = navigateToDetails,
                    onDeleteClicked = deleteMonster
                )
            }
        }
    }
}

@Composable
fun MonsterViewHolder(
    monster: Monster,
    onClicked: (id: Int) -> Unit,
    onDeleteClicked: (id: Int) -> Unit,
) {
    Card(
        modifier = Modifier
            .padding(horizontal = AppTheme.dimensions.largePadding,
                vertical = AppTheme.dimensions.extraSmallPadding)
            .fillMaxWidth()
            .clickable {
                onClicked(monster.id)
            },
        shape = AppTheme.shapes.cardShape,
        backgroundColor = AppTheme.colors.surface,
        contentColor = AppTheme.colors.onSurface,
    ) {
        Row(modifier = Modifier.padding(AppTheme.dimensions.defaultPadding)) {
            Column {
                monster.run {
                    Text(text = "id: $id", modifier = Modifier.padding(bottom = 2.dp))
                    Text(text = "locationId: $locationId",
                        modifier = Modifier.padding(bottom = 2.dp))
                    Text(text = "element: $element", modifier = Modifier.padding(bottom = 2.dp))
                    Text(text = "uniqueness: $uniqueness",
                        modifier = Modifier.padding(bottom = 2.dp))
                    Text(text = "encountered: $encountered",
                        modifier = Modifier.padding(bottom = 2.dp))
                    Text(
                        text = """
                            attributes:
                                * strength: ${attributes.strength}
                                * dexterity: ${attributes.dexterity}
                                * endurance: ${attributes.endurance}
                                * intelligence: ${attributes.intelligence}
                        """.trimIndent(),
                        modifier = Modifier.padding(bottom = 2.dp),
                        color = AppTheme.colors.onSurface,
                    )
                    Text(text = "level: $level", modifier = Modifier.padding(bottom = 2.dp))
                }
            }
            IconButton(onClick = { onDeleteClicked(monster.id) }) {
                Icon(imageVector = Icons.Default.Delete, contentDescription = "delete")
            }
        }
    }
}

@Composable
@Preview(
    showBackground = false,
    name = "Monster list",
    device = "spec:Normal;1080;1920;px;320dpi;portrait",
    apiLevel = 30,
    uiMode = Configuration.UI_MODE_NIGHT_NO or Configuration.UI_MODE_TYPE_NORMAL, locale = "ru",
    showSystemUi = true
)
fun MonsterListScreenPreview() {
    AppTheme {
        MonsterListScreen(
            stateFlow = MutableStateFlow(
                State.Success(value = TestData.testMonsterList)
            ),
            sideEffectFlow = flowOf(),
            navigateToDetails = {},
            addNewMonster = {},
            deleteMonster = {},
            deleteAllMonsters = {},
        )
    }
}
