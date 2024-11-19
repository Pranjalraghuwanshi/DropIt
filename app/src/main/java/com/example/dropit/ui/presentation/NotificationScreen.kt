package com.example.dropit.ui.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.dropit.R
import com.example.dropit.ui.presentation.common.CommonTopBar

@Composable
fun NotificationScreen(navController: NavController) {

    val notifications = listOf<String>()

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
            // Scrollable Content
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 88.dp) // Adjust this to fit the space occupied by the header
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                if (notifications.isEmpty()) {
                    // Display default image if there are no notifications
                    LottieAnimationNotification()
                    Text(
                        text = "No Notifications Yet",
                        color = colorResource(id = R.color.black),
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Normal
                    )
                } else {
                    // Display list of notifications
                    notifications.forEach { notification ->
                        Text(
                            text = notification,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp),
                            color = colorResource(id = R.color.black),
                            fontSize = 18.sp
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun LottieAnimationNotification() {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.notification))
    val progress by animateLottieCompositionAsState(
        composition = composition, restartOnPlay = true, iterations = LottieConstants.IterateForever
    )

    LottieAnimation(modifier = Modifier.size(300.dp),
        composition = composition,
        progress = { progress })

}

