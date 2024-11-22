package com.example.dropit.ui.presentation

import android.net.Uri
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.dropit.R
import com.example.dropit.ui.presentation.common.CommonImage

@Composable
fun ProfileDetailsScreen(navController: NavController) {
    ProfileContent(
        navController = navController,
        imageUrl = null,
        onSave = {},
        onLogout = {},
        name = "John Doe",
        number = "1234567890",
        onNameChange = {},
        onNumberChange = {}
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileContent(
    navController: NavController,
    imageUrl: String?,
    onSave: () -> Unit,
    onLogout: () -> Unit,
    name: String,
    number: String,
    onNameChange: (String) -> Unit,
    onNumberChange: (String) -> Unit
) {
    var isNameFieldEnabled by remember { mutableStateOf(false) }
    var isNumberFieldEnabled by remember { mutableStateOf(false) }

    val chooseProfileImage = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) {}

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(top = 30.dp)
    ) {
        TopAppBar(
            title = { AppBarTitle() },
            navigationIcon = {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(
                        Icons.Default.ArrowBackIosNew,
                        contentDescription = "Back",
                        tint = Color.Black
                    )
                }
            },
            actions = {
                IconButton(onClick = onSave) {
                    Icon(Icons.Default.SaveAs, contentDescription = "Save", tint = Color.Black)
                }
                IconButton(onClick = onLogout) {
                    Icon(Icons.Default.Logout, contentDescription = "Logout", tint = Color.Black)
                }
            },
            colors = TopAppBarDefaults.topAppBarColors(containerColor = colorResource(R.color.white))
        )

        Spacer(modifier = Modifier.height(18.dp))

        ProfileImagePicker(imageUrl = imageUrl, chooseProfileImage = chooseProfileImage)

        Spacer(modifier = Modifier.height(18.dp))

        UserInfoFields(
            name = name,
            number = number,
            onNameChange = onNameChange,
            onNumberChange = onNumberChange,
            isNameFieldEnabled = isNameFieldEnabled,
            isNumberFieldEnabled = isNumberFieldEnabled,
            onNameFieldToggle = { isNameFieldEnabled = !isNameFieldEnabled },
            onNumberFieldToggle = { isNumberFieldEnabled = !isNumberFieldEnabled }
        )
    }
}

@Composable
fun AppBarTitle() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = "My Profile",
            color = Color.Black,
            style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)
        )
        Spacer(modifier = Modifier.weight(1f))
    }
}

@Composable
fun ProfileImagePicker(
    imageUrl: String?,
    chooseProfileImage: ManagedActivityResultLauncher<String, Uri?>
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CommonImage(
            data = imageUrl,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(100.dp)
                .clickable { chooseProfileImage.launch("image/*") }
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Change Profile Picture",
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.W500,
                color = colorResource(id = R.color.black)
            )
        )
    }
}

@Composable
fun UserInfoFields(
    name: String,
    number: String,
    onNameChange: (String) -> Unit,
    onNumberChange: (String) -> Unit,
    isNameFieldEnabled: Boolean,
    isNumberFieldEnabled: Boolean,
    onNameFieldToggle: () -> Unit,
    onNumberFieldToggle: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            modifier = Modifier.width(380.dp),
            value = name,
            onValueChange = onNameChange,
            enabled = isNameFieldEnabled,
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,
                focusedBorderColor = Color.Black,
                cursorColor = Color.Black,
                disabledBorderColor = Color.Black,
                disabledTextColor = Color.Black
            ),
            leadingIcon = {
                Icon(Icons.Default.Person, contentDescription = null, tint = Color.Black)
            },
            trailingIcon = {
                IconButton(onClick = onNameFieldToggle) {
                    Icon(Icons.Default.Edit, contentDescription = null, tint = Color.Black)
                }
            }
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            modifier = Modifier.width(380.dp),
            value = number,
            onValueChange = onNumberChange,
            enabled = isNumberFieldEnabled,
            singleLine = true,
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black,
                focusedBorderColor = Color.Black,
                cursorColor = Color.Black,
                disabledBorderColor = Color.Black,
                disabledTextColor = Color.Black
            ),
            leadingIcon = {
                Icon(Icons.Default.Phone, contentDescription = null, tint = Color.Black)
            },
            trailingIcon = {
                IconButton(onClick = onNumberFieldToggle) {
                    Icon(Icons.Default.Edit, contentDescription = null, tint = Color.Black)
                }
            }
        )
    }
}
