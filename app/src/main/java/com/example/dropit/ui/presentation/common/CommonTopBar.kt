package com.example.dropit.ui.presentation.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.dropit.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommonTopBar(
) {
    TopAppBar(
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 16.dp)
            ) {
                // Left side logo
                Image(
                    painter = painterResource(id = R.drawable.logodrop), // Replace with your logo drawable
                    contentDescription = "",
                    modifier = Modifier
                        .size(128.dp) // Adjust size as needed
                        .padding(end = 8.dp)
                        .offset(x = (-10).dp),
                    contentScale = ContentScale.Crop
                )
            }
        },
        modifier = Modifier,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.White
        )
    )
}