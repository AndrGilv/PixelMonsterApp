package com.example.pixelmonsterapp3.ui

import android.os.Bundle
import androidx.activity.compose.setContent
import com.example.shared.navigation.core.Navigator
import com.example.shared.ui.core.theme.AppTheme
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var navigator: Navigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                navigator.Content()
            }
        }
    }
}
