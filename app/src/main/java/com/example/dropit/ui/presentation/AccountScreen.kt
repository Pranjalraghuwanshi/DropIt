package com.example.dropit.ui.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Photo
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.dropit.R
import com.example.dropit.ui.navigation.Route
import com.example.dropit.ui.presentation.common.CommonTopBar
import com.example.dropit.ui.theme.ColorOrange
import com.example.dropit.ui.theme.h3TextStyle

@Composable
fun AccountScreen(navController: NavController) {
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
            val menuItems = remember {
                listOf(
                    MenuItem("Edit Profile") {
                        navController.navigate(Route.ProfileDetailScreen.name)
                    },
                    MenuItem("My Order") {},
                    MenuItem("Change Password") {
                        navController.navigate(Route.ChangePasswordScreen.name)
                    },
                    MenuItem("Help Screen") {
                        navController.navigate(Route.HelpScreen.name)
                    },
                    MenuItem("Privacy Policy") {
                        navController.navigate(Route.PolicyScreen.name)
                    },
                    MenuItem("Terms & Conditions") {
                        navController.navigate(Route.TermsAndConditions.name)
                    }
                )
            }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                // Profile Header (won't recompose when menu items change)
                ProfileHeader()

                Spacer(modifier = Modifier.height(24.dp))

                // Use LazyColumn for efficient scrolling and item rendering
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(menuItems.size) { index ->
                        MenuListItem(menuItems[index])
                    }

                    item {
                        Spacer(modifier = Modifier.height(24.dp))
                        LogoutButton {}
                    }
                }
            }
        }
    }
}


@Composable
private fun ProfileHeader() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Box {
            Image(
                painter = painterResource(id = R.drawable.dropit),
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .size(80.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )

            // Online indicator
            Surface(
                modifier = Modifier
                    .size(24.dp)
                    .background(Color.White)
                    .align(Alignment.BottomEnd),
                shape = CircleShape
            ) {
                Icon(
                    imageVector = Icons.Default.Photo,
                    contentDescription = "Online Indicator",
                    tint = ColorOrange
                )
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Johnson Smith",
            style = h3TextStyle,
            fontSize = 24.sp,
            color = Color.Black,
            fontFamily = FontFamily.SansSerif,
        )

        Text(
            text = "johnson@gmail.com",
            fontSize = 16.sp,
            color = Color.LightGray,
            fontFamily = FontFamily.SansSerif
        )
    }
}

@Composable
private fun MenuListItem(item: MenuItem) {
    Surface(
        onClick = item.onClick,
        modifier = Modifier.fillMaxWidth().background(Color.White)
    ) {
        Row(
            modifier = Modifier.background(Color.White)
                .padding(vertical = 12.dp, horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = item.title,
                fontSize = 18.sp,
                color = Color.Black,
                fontFamily = FontFamily.SansSerif,
            )
            Icon(
                Icons.Default.ArrowForwardIos,
                contentDescription = null,
                tint = Color.Black
            )
        }
    }
}

@Composable
private fun LogoutButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(
            containerColor = ColorOrange
        )
    ) {
        Text(
            text = "Logout",
            modifier = Modifier.padding(vertical = 8.dp),
            fontSize = 18.sp,
            color = Color.White,
            fontFamily = FontFamily.SansSerif
        )
    }
}

private data class MenuItem(
    val title: String,
    val onClick: () -> Unit
)

@Preview
@Composable
fun PreviewAccountScreen() {
    AccountScreen(rememberNavController())
}