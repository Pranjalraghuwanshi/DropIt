package com.example.dropit

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.example.dropit.ui.navigation.NavGraph
import com.example.dropit.ui.theme.DropItTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        actionBar?.hide()
        setContent {

            val systemController = rememberSystemUiController()

            SideEffect {
                systemController.setStatusBarColor(
                    color = Color.White,
                    darkIcons = true
                )
            }

            DropItTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    NavGraph(context = this@MainActivity)
                }
            }
        }
    }
}

