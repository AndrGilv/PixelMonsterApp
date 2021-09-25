package com.example.pixelmonsterapp3.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.pixelmonsterapp3.di.ViewModelFactories
import com.example.shared.navigation.core.Navigator
import com.example.shared.ui.core.theme.PixelMonsterApp3Theme
import dagger.android.support.DaggerAppCompatActivity
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
