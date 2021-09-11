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
