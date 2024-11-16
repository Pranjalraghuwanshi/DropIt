package com.example.dropit.ui.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIosNew
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.dropit.ui.navigation.Route
import com.example.dropit.ui.theme.ColorOrange

@Composable
fun OtpVerificationScreen(navController: NavController) {
    var code by remember { mutableStateOf(List(4) { "" }) }
    val focusManager = LocalFocusManager.current
    val focusRequesters = List(4) { FocusRequester() }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(start = 24.dp, top = 48.dp, end = 24.dp)
        ) {
            IconButton(
                onClick = { navController.popBackStack() },
                modifier = Modifier
                    .align(Alignment.Start)
                    .clip(RoundedCornerShape(28.dp))
                    .background(ColorOrange)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBackIosNew,
                    contentDescription = "Back",
                    tint = Color.White
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Verification Code",
                fontSize = 24.sp,
                color = Color.Black,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "We have sent the verification\ncode to your email address",
                fontSize = 14.sp,
                color = Color.Gray,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(32.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                code.forEachIndexed { index, value ->
                    CodeInput(
                        code = value,
                        onValueChange = { newChar ->
                            if (newChar.length <= 1 && newChar.all { it.isDigit() }) {
                                code = code.toMutableList().apply { this[index] = newChar }
                                if (newChar.isNotEmpty() && index < 3) {
                                    focusRequesters[index + 1].requestFocus()
                                } else if (newChar.isEmpty() && index > 0) {
                                    focusRequesters[index - 1].requestFocus()
                                }
                            }
                        },
                        focusRequester = focusRequesters[index]
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            Button(
                onClick = {
                    if (code.all { it.isNotEmpty() }) {
                        navController.navigate(Route.HomeScreen.name)
                    }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = ColorOrange),
                shape = RoundedCornerShape(16.dp)
            ) {
                Text("Confirm", color = Color.White)
            }
        }
    }
}

@Composable
fun CodeInput(
    code: String,
    onValueChange: (String) -> Unit,
    focusRequester: FocusRequester
) {
    BasicTextField(
        value = code,
        onValueChange = onValueChange,
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        modifier = Modifier
            .size(64.dp)
            .focusRequester(focusRequester)
            .clip(RoundedCornerShape(16.dp))
            .border(
                width = 2.dp,
                color = if (code.isNotEmpty()) ColorOrange else Color.LightGray,
                shape = RoundedCornerShape(16.dp)
            )
            .background(Color(0xFFF5F5F5), RoundedCornerShape(16.dp))
            .padding(2.dp),
        decorationBox = { innerTextField ->
            Box(
                contentAlignment = Alignment.Center
            ) {
                innerTextField()
            }
        },
        textStyle = LocalTextStyle.current.copy(
            color = Color.Black,
            fontSize = 24.sp,
            textAlign = TextAlign.Center
        )
    )
}

@Preview
@Composable
fun PreviewVerificationCodeScreen() {
    OtpVerificationScreen(rememberNavController())
}
