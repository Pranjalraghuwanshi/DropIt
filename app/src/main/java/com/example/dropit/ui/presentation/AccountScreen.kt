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
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Photo
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.dropit.R
import com.example.dropit.ui.presentation.common.CommonTopBar
import com.example.dropit.ui.theme.ColorOrange

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
                    MenuItem("Edit Profile") {},
                    MenuItem("My Address") {},
                    MenuItem("My Order") {},
                    MenuItem("Change Password") {},
                    MenuItem("Privacy Policy") {},
                    MenuItem("Terms & Conditions") {}
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
            style = MaterialTheme.typography.titleLarge
        )

        Text(
            text = "johnson@gmail.com",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
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
                style = MaterialTheme.typography.bodyLarge
            )
            Icon(
                Icons.Default.ArrowForwardIos,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurfaceVariant
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
            modifier = Modifier.padding(vertical = 8.dp)
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