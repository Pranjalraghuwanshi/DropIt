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
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.dropit.ui.navigation.NavGraph
import com.example.dropit.ui.theme.DropItTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Install Splash Screen and add delay
        val splashScreen = installSplashScreen()

        // Keep the splash screen visible for 4 seconds
        splashScreen.setKeepOnScreenCondition {
            // Check if 4 seconds have passed
            false // We'll handle the delay below
        }

        // Add a coroutine to delay the splash screen
        kotlinx.coroutines.GlobalScope.launch {
            delay(4000) // 4-second delay
        }

        enableEdgeToEdge()
        actionBar?.hide()
        setContent {

            val systemController = rememberSystemUiController()

            SideEffect {
                systemController.setStatusBarColor(
                    color = Color.Transparent,
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

