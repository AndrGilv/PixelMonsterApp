package com.example.shared_ui_core.ui

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.shared_ui_core.components.MyTopAppBar
import com.example.shared_ui_core.presentation.SideEffect
import com.example.shared_ui_core.presentation.State
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOf

@Composable
fun <StateData> ScreenScaffold(
    stateFlow: StateFlow<State<StateData>> = MutableStateFlow(State.Empty()),
    sideEffectFlow: Flow<SideEffect> = flowOf(),
    handleSideEffects: CoroutineScope.(sideEffect: SideEffect) -> Unit,
    modifier: Modifier = Modifier,
    topBar: @Composable () -> Unit = {
        MyTopAppBar(
            modifier = Modifier
        ) {
            Text(text = "My test app")
        }
    },
    bottomBar: @Composable () -> Unit = {},
    floatingActionButton: @Composable (stateFlow: State<StateData>) -> Unit = {},
    floatingActionButtonPosition: FabPosition = FabPosition.End,
    isFloatingActionButtonDocked: Boolean = true,
    drawerContent: @Composable (ColumnScope.() -> Unit)? = {
        Text(text = "drawer item")
    },
    drawerGesturesEnabled: Boolean = true,
    drawerShape: Shape = CutCornerShape(8.dp),
    drawerElevation: Dp = 8.dp,
    drawerBackgroundColor: Color = MaterialTheme.colors.surface,
    drawerScrimColor: Color = MaterialTheme.colors.onSurface.copy(alpha = 0.32f),
    backgroundColor: Color = MaterialTheme.colors.surface,
    content: @Composable (state: State<StateData>) -> Unit,
) = BaseScreen(
    stateFlow = stateFlow,
    sideEffectFlow = sideEffectFlow,
    handleSideEffects = { sideEffect ->
        handleSideEffects(sideEffect)
        // TODO: возможно тут стоит ещё сделать какую то обработку дровера и снейкбара
    },
) { state ->

    val snackbarHostState = remember { SnackbarHostState() }
    val drawerState = remember {
        DrawerState(
            initialValue = DrawerValue.Closed,
            confirmStateChange = { draverValue ->
                true
                // TODO тут явно нужно что то сделать
            }
        )
    }
    Scaffold(
        modifier = modifier,
        scaffoldState = rememberScaffoldState(
            snackbarHostState = snackbarHostState,
            drawerState = drawerState
        ),
        topBar = topBar,
        snackbarHost = { snackBarState ->
            SnackbarHost(
                hostState = snackbarHostState,
                modifier = Modifier
            ) { data ->
                Snackbar(
                    snackbarData = data,
                    modifier = Modifier,
                    actionOnNewLine = false,
                    shape = RoundedCornerShape(4.dp),
                    backgroundColor = MaterialTheme.colors.surface,
                    actionColor = MaterialTheme.colors.primaryVariant,
                    elevation = 6.dp
                )
            }
        },
        bottomBar = bottomBar,
        floatingActionButton = { floatingActionButton(state) },
        floatingActionButtonPosition = floatingActionButtonPosition,
        isFloatingActionButtonDocked = isFloatingActionButtonDocked,
        drawerContent = drawerContent,
        drawerGesturesEnabled = drawerGesturesEnabled,
        drawerShape = drawerShape,
        drawerElevation = drawerElevation,
        drawerBackgroundColor = drawerBackgroundColor,
        drawerScrimColor = drawerScrimColor,
        backgroundColor = backgroundColor,
    ) {
        content(state)
    }
}
