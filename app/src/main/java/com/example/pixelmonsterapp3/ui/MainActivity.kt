package com.example.pixelmonsterapp3.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSavedStateRegistryOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.*
import androidx.navigation.compose.*
import com.example.pixelmonsterapp3.composable
import com.example.pixelmonsterapp3.di.*
import com.example.pixelmonsterapp3.domain.entity.Monster
import com.example.pixelmonsterapp3.domain.entity.MonsterDetails
import com.example.pixelmonsterapp3.navigation.*
import com.example.pixelmonsterapp3.navigation.Navigator
import com.example.pixelmonsterapp3.presentation.*
import com.example.pixelmonsterapp3.presentation.State
import com.example.pixelmonsterapp3.presentation.monsterdetails.*
import com.example.pixelmonsterapp3.presentation.monsterlist.*
import com.example.pixelmonsterapp3.ui.theme.PixelMonsterApp3Theme
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var navigator: Navigator

    @Inject
    lateinit var assistedFactories: ViewModelFactories

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PixelMonsterApp3Theme {
                val navController = rememberNavController()
                Surface(modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background) {
                    NavigationComponent(
                        navController = navController,
                        navigator = navigator,
                        assistedFactories = assistedFactories,
                    )
                }
            }
        }
    }
}

@Composable
fun NavigationComponent(
    navController: NavHostController,
    navigator: Navigator,
    assistedFactories: ViewModelFactories
) {

    LaunchedEffect("navigation") {
        navigator.destinations.onEach { navigationEvent ->
            when(navigationEvent){
                is NavigatorEvent.Direction -> navController.navigate(
                    route = navigationEvent.route,
                    navOptions = navigationEvent.createNavOptions(),
                    navigatorExtras = navigationEvent.createNavExtras(),
                )
                is NavigatorEvent.NavigateUp -> navController.navigateUp()
                is NavigatorEvent.DefaultBackNavigation -> navController.popBackStack()
                is NavigatorEvent.NavigateBack -> navController.popBackStack(
                    route = navigationEvent.route,
                    inclusive = navigationEvent.inclusive,
                    saveState = navigationEvent.saveState,
                )
            }
        }.launchIn(this)
    }

    NavHost(
        navController = navController,
        startDestination = MonsterListDestination.name
    ) {
        composable(MonsterListDestination) {

            val monsterListViewModel = viewModel<MonsterListViewModel>(
                factory = assistedFactories.monsterListViewModelAssistedFactory.create( LocalSavedStateRegistryOwner.current)
            )

            MonsterListScreen(
                stateFlow = monsterListViewModel.container.stateFlow,
                sideEffectFlow = monsterListViewModel.container.sideEffectFlow,
                navigateToDetails = monsterListViewModel::navigateToMonsterDetails,
            )
        }
        composable(MonsterDetailsDestination) { backStackEntry ->
            MonsterDetailsDestination.getArgs( backStackEntry.arguments) { id ->
                val monsterDetailsViewModel = viewModel<MonsterDetailsViewModel>(
                    factory = assistedFactories.monsterDetailsViewModelAssistedFactory.create(
                        monsterId = id,
                        owner = LocalSavedStateRegistryOwner.current
                    )
                )

                MonsterDetailsScreen(
                    stateFlow = monsterDetailsViewModel.container.stateFlow,
                    sideEffectFlow = monsterDetailsViewModel.container.sideEffectFlow,
                    navigateBack = monsterDetailsViewModel::navigateUp,
                )
            }
        }
    }
}

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
