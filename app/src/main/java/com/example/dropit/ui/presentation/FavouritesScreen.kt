package com.example.dropit.ui.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.dropit.R
import com.example.dropit.ui.presentation.common.CommonTopBar

@Composable
fun FavouritesScreen(navController: NavController) {
    Scaffold(
        topBar = {
            CommonTopBar()
        },
        bottomBar = {
            BottomBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(90.dp),
                navController = navController
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ){
                LottieAnimationFavourites()
            }
        }
    }
}

@Composable
fun LottieAnimationFavourites() {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.favourites))
    val progress by animateLottieCompositionAsState(
        composition = composition, restartOnPlay = true, iterations = LottieConstants.IterateForever
    )

    LottieAnimation(modifier = Modifier.size(300.dp),
        composition = composition,
        progress = { progress })
}

@Preview
@Composable
fun FavouritesScreenPreview() {
    FavouritesScreen(navController = rememberNavController())
}