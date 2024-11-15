package com.example.dropit.ui.presentation

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.dropit.R
import com.example.dropit.ui.navigation.Route
import com.example.dropit.ui.theme.ColorOrange

@Composable
fun BottomBar(navController: NavController, modifier: Modifier = Modifier) {
    // Observe the current back stack entry to get the current route
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Box(modifier = Modifier) {
        Canvas(
            modifier = Modifier
                .fillMaxWidth()
                .height(90.dp)
                .align(Alignment.Center)
                .shadow(elevation = 32.dp)
        ) {
            val cornerRadius = 32.dp.toPx()
            val path = Path().apply {
                moveTo(0f, cornerRadius)
                lineTo(0f, size.height)
                lineTo(size.width, size.height)
                lineTo(size.width, cornerRadius)
                arcTo(
                    rect = Rect(0f, 0f, cornerRadius * 2, cornerRadius * 2),
                    startAngleDegrees = 180f,
                    sweepAngleDegrees = 90f,
                    forceMoveTo = false
                )
                lineTo(size.width - cornerRadius, 0f)
                arcTo(
                    rect = Rect(size.width - cornerRadius * 2, 0f, size.width, cornerRadius * 2),
                    startAngleDegrees = 270f,
                    sweepAngleDegrees = 90f,
                    forceMoveTo = false
                )
                close()
            }
            drawPath(
                path = path,
                color = Color.White
            )
        }

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .height(90.dp)
                .align(Alignment.Center)
        ) {
            IconButton(
                onClick = {
                    navController.navigate(Route.HomeScreen.name)
                }
            ) {
                Image(
                    painter = painterResource(R.drawable.home),
                    contentDescription = null,
                    modifier = Modifier.size(32.dp),
                    colorFilter = androidx.compose.ui.graphics.ColorFilter.tint(
                        if (currentRoute == Route.HomeScreen.name) ColorOrange else Color.LightGray
                    )
                )
            }
            IconButton(onClick = {
                navController.navigate(Route.FavouritesScreen.name)
            }) {
                Image(
                    painter = painterResource(R.drawable.favourites),
                    contentDescription = null,
                    modifier = Modifier.size(32.dp),
                    colorFilter = androidx.compose.ui.graphics.ColorFilter.tint(
                        if (currentRoute == Route.FavouritesScreen.name) ColorOrange else Color.LightGray
                    )
                )
            }
            IconButton(
                onClick = {
                    navController.navigate(Route.PostScreen.name)
                },
                modifier = Modifier
                    .offset(y = (-30).dp) // Apply offset to the entire IconButton
                    .clip(RoundedCornerShape(14.dp))
                    .background(ColorOrange)
                    .size(60.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.AddCircle,
                    contentDescription = "Post",
                    tint = Color.White
                )
            }

            IconButton(onClick = {
                navController.navigate(Route.NotificationScreen.name)
            }) {
                Image(
                    painter = painterResource(R.drawable.notifications),
                    contentDescription = null,
                    modifier = Modifier.size(32.dp),
                    colorFilter = androidx.compose.ui.graphics.ColorFilter.tint(
                        if (currentRoute == Route.NotificationScreen.name) ColorOrange else Color.LightGray
                    )
                )
            }
            IconButton(onClick = {
                navController.navigate(Route.AccountScreen.name)
            }) {
                Image(
                    painter = painterResource(R.drawable.account),
                    contentDescription = null,
                    modifier = Modifier.size(32.dp),
                    colorFilter = androidx.compose.ui.graphics.ColorFilter.tint(
                        if (currentRoute == Route.AccountScreen.name) ColorOrange else Color.LightGray
                    )
                )
            }
        }
    }
}

@Preview
@Composable
fun BottomBarPreview() {
    BottomBar(navController = rememberNavController(), modifier = Modifier)
}
