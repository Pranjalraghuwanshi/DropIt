package com.example.dropit.ui.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.dropit.MainActivity
import com.example.dropit.ui.presentation.AccountScreen
import com.example.dropit.ui.presentation.BottomBarScreen
import com.example.dropit.ui.presentation.HomeScreen
import com.example.dropit.ui.presentation.LoginScreen
import com.example.dropit.ui.presentation.NotificationScreen
import com.example.dropit.ui.presentation.OnboardingScreen
import com.example.dropit.ui.presentation.PostScreen
import com.example.dropit.ui.presentation.SignUpScreen
import com.google.accompanist.navigation.animation.AnimatedNavHost

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavGraph(context : MainActivity) {

    val navController = rememberNavController()

    AnimatedNavHost(
        navController = navController,
        startDestination = Route.OnboardingScreen.name,
        enterTransition = { slideInHorizontally(initialOffsetX = { 1000 }) + fadeIn() },
        exitTransition = { slideOutHorizontally(targetOffsetX = { -1000 }) + fadeOut() },
        popEnterTransition = { slideInHorizontally(initialOffsetX = { -1000 }) + fadeIn() },
        popExitTransition = { slideOutHorizontally(targetOffsetX = { 1000 }) + fadeOut() }
    ) {
        composable(Route.OnboardingScreen.name) {
            OnboardingScreen(navController, context)
        }
        composable(Route.LoginScreen.name) {
            LoginScreen(navController)
        }
        composable(Route.SignUpScreen.name) {
            SignUpScreen(navController)
        }
        composable(Route.SignUpScreen.name) {
            BottomBarScreen(navController)
        }
        composable(Route.HomeScreen.name) {
            HomeScreen(navController)
        }
        composable(Route.PostScreen.name) {
            PostScreen(navController)
        }
        composable(Route.NotificationScreen.name) {
            NotificationScreen(navController)
        }
        composable(Route.AccountScreen.name) {
            AccountScreen(navController)
        }
    }
}