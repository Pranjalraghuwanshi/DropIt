package com.example.dropit.ui.presentation

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.dropit.R
import com.example.dropit.ui.navigation.Route
import com.example.dropit.ui.theme.ColorOrange
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PostScreen(navController: NavController) {
    var productName by remember { mutableStateOf(TextFieldValue()) }
    var description by remember { mutableStateOf(TextFieldValue()) }
    var price by remember { mutableStateOf("") }
    var pickupPoint by remember { mutableStateOf(TextFieldValue()) }
    var deliveryPoint by remember { mutableStateOf(TextFieldValue()) }
    var date by remember { mutableStateOf("") }
    var time by remember { mutableStateOf("") }

    val calendar = Calendar.getInstance()
    val context = LocalContext.current
    val datePickerDialog = DatePickerDialog(
        context,
        { _, year, month, day -> date = "$day/${month + 1}/$year" },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )

    val timePickerDialog = TimePickerDialog(
        context,
        { _, hour, minute -> time = String.format("%02d:%02d", hour, minute) },
        calendar.get(Calendar.HOUR_OF_DAY),
        calendar.get(Calendar.MINUTE),
        false
    )

    var selectedImageUris by remember { mutableStateOf<List<android.net.Uri>>(emptyList()) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument()
    ) { uris ->
        uris?.let {
            selectedImageUris = selectedImageUris + (if (uris is List<*>) uris.filterIsInstance<android.net.Uri>() else listOf(uris))
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Post Product Details",
                        fontSize = 24.sp,
                        modifier = Modifier.padding(start = 24.dp),
                        color = Color.Black
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.navigate(Route.HomeScreen.name)
                        },
                        modifier = Modifier
                            .clip(RoundedCornerShape(28.dp))
                            .background(ColorOrange)
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBackIosNew,
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxWidth()
                    .padding(12.dp),
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White
                )
            )
        },
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(Color.White)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                "Add details about the product you are posting",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )

            OutlinedTextField(
                value = productName,
                onValueChange = { productName = it },
                label = { Text("Product Name") },
                placeholder = { Text("Enter product name") },
                modifier = Modifier.fillMaxWidth(),
                colors = outlinedTextFieldColors()
            )

            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Description") },
                placeholder = { Text("Describe your product") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp),
                colors = outlinedTextFieldColors()
            )

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .clickable { launcher.launch(arrayOf("image/*")) },
                elevation = CardDefaults.elevatedCardElevation(4.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(Color.White),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    LottieAnimationUploadImage()
                    Text("Upload Photos", style = MaterialTheme.typography.bodyMedium)
                }
            }

            if (selectedImageUris.isNotEmpty()) {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(selectedImageUris.size) { index ->
                        AsyncImage(
                            model = selectedImageUris[index],
                            contentDescription = "Selected Image",
                            modifier = Modifier
                                .size(100.dp)
                                .background(Color.White)
                                .padding(4.dp),
                            contentScale = androidx.compose.ui.layout.ContentScale.Crop
                        )
                    }
                }
            }
            OutlinedTextField(
                value = price,
                onValueChange = { price = it },
                label = { Text("Price") },
                prefix = { Text("₹") },
                modifier = Modifier.fillMaxWidth(),
                colors = outlinedTextFieldColors(),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number
                )
            )

            OutlinedTextField(
                value = pickupPoint,
                onValueChange = { pickupPoint = it },
                label = { Text("Pickup Point") },
                placeholder = { Text("Enter pickup location") },
                modifier = Modifier.fillMaxWidth(),
                colors = outlinedTextFieldColors()
            )

            OutlinedTextField(
                value = deliveryPoint,
                onValueChange = { deliveryPoint = it },
                label = { Text("Delivery Point") },
                placeholder = { Text("Enter delivery location") },
                modifier = Modifier.fillMaxWidth(),
                colors = outlinedTextFieldColors()
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    value = date,
                    onValueChange = { date = it },
                    placeholder = { Text("Select Date") },
                    readOnly = true,
                    modifier = Modifier
                        .weight(1f)
                        .clickable { datePickerDialog.show() },
                    leadingIcon = {
                        Icon(
                            Icons.Default.DateRange,
                            contentDescription = "Select Date",
                            modifier = Modifier.clickable { datePickerDialog.show() }
                        )
                    },
                    colors = outlinedTextFieldColors()
                )

                OutlinedTextField(
                    value = time,
                    onValueChange = {},
                    placeholder = { Text("Select Time") },
                    readOnly = true,
                    modifier = Modifier
                        .weight(1f)
                        .clickable { timePickerDialog.show() },
                    leadingIcon = {
                        Icon(
                            Icons.Default.AccessTime,
                            contentDescription = "Select Time",
                            modifier = Modifier.clickable { timePickerDialog.show() }
                        )
                    },
                    colors = outlinedTextFieldColors()
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = { /* Handle post creation */ },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp),
                colors = ButtonDefaults.buttonColors(containerColor = ColorOrange)
            ) {
                Text("Post")
            }
        }
    }
}

@Composable
fun outlinedTextFieldColors() = OutlinedTextFieldDefaults.colors(
    focusedBorderColor = ColorOrange,
    focusedTextColor = Color.Black,
    unfocusedTextColor = Color.Black,
    cursorColor = Color.Black
)

@Composable
fun LottieAnimationUploadImage() {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.upload))
    val progress by animateLottieCompositionAsState(
        composition = composition, restartOnPlay = true, iterations = LottieConstants.IterateForever
    )
    LottieAnimation(
        composition = composition,
        progress = { progress },
        modifier = Modifier.size(100.dp)
    )
}


@Preview
@Composable
fun PreviewPostScreen() {
    PostScreen(rememberNavController())
}
