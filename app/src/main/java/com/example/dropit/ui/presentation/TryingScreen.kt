package com.example.dropit.ui.presentation

//import androidx.compose.animation.core.animateFloatAsState
//import androidx.compose.foundation.BorderStroke
//import androidx.compose.foundation.Canvas
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.gestures.detectHorizontalDragGestures
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.PaddingValues
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.offset
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.layout.width
//import androidx.compose.foundation.layout.wrapContentSize
//import androidx.compose.foundation.lazy.LazyRow
//import androidx.compose.foundation.lazy.items
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Close
//import androidx.compose.material.icons.filled.FavoriteBorder
//import androidx.compose.material3.Card
//import androidx.compose.material3.Icon
//import androidx.compose.material3.IconButton
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateListOf
//import androidx.compose.runtime.mutableStateMapOf
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.geometry.Offset
//import androidx.compose.ui.graphics.Brush
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.SolidColor
//import androidx.compose.ui.graphics.graphicsLayer
//import androidx.compose.ui.graphics.vector.ImageVector
//import androidx.compose.ui.input.pointer.pointerInput
//import androidx.compose.ui.input.pointer.positionChange
//import androidx.compose.ui.platform.LocalDensity
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.IntOffset
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.zIndex
//import androidx.navigation.compose.rememberNavController
//import com.example.dropit.R
//import com.example.dropit.ui.theme.ColorOrange
//import kotlin.math.roundToInt
//
//
//@Composable
//fun TryingScreen() {
//    val sampleImages = listOf(
//        R.drawable.add_icon,
//        R.drawable.account,
//        R.drawable.forward,
//        R.drawable.onboard,
//        R.drawable.onboardd,
//        R.drawable.onboarddd,
//    )
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(
//                brush = Brush.linearGradient(
//                    colors = listOf(ColorOrange.copy(alpha = 0.3f), Color(0x00C4C4C4)),
//                    start = Offset(0f, 0f),
//                    end = Offset(0f, Float.POSITIVE_INFINITY)
//                )
//            )
//    ) {
//        Image(
//            painter = painterResource(R.drawable.account), contentDescription = null,
//            modifier = Modifier
//                .align(Alignment.TopStart)
//                .padding(start = 25.dp)
//                .offset(y = 50.dp)
//                .size(50.dp)
//        )
//        Image(
//            painter = painterResource(R.drawable.add_icon), contentDescription = null,
//            modifier = Modifier
//                .align(Alignment.TopStart)
//                .offset(y = 130.dp)
//                .padding(start = 25.dp)
//        )
//        Image(
//            painter = painterResource(R.drawable.notifications), contentDescription = null,
//            modifier = Modifier
//                .align(Alignment.TopStart)
//                .offset(y = 150.dp)
//                .padding(start = 25.dp)
//        )
//        IconButton(
//            onClick = { /*TODO*/ },
//            modifier = Modifier
//                .align(Alignment.TopStart)
//                .offset(y = 190.dp)
//                .padding(start = 25.dp)
//
//
//        ) {
//            Image(
//                painter = painterResource(R.drawable.forward),
//                contentDescription = null,
//                modifier = Modifier.size(50.dp)
//            )
//        }
//        DogsLazy(
//            modifier = Modifier
//                .align(Alignment.TopStart)
//                .offset(y = 190.dp)
//                .padding(start = 75.dp), images = sampleImages
//        )
//        SwipeCardExample(
//            modifier = Modifier
//                .align(Alignment.TopCenter)
//                .offset(y = 155.dp)
//        )
//        IconButton(
//            onClick = { /*TODO*/ },
//            modifier = Modifier
//                .align(Alignment.TopEnd)
//                .padding(end = 25.dp)
//                .offset(y = 50.dp)
//        ) {
//            Image(
//                painter = painterResource(R.drawable.home), contentDescription = null
//            )
//        }
//    }
//}
//
//
//@Composable
//fun DogsLazy(modifier: Modifier = Modifier, images: List<Int>) {
//    LazyRow(
//        contentPadding = PaddingValues(horizontal = 16.dp),
//        horizontalArrangement = Arrangement.spacedBy(8.dp),
//        modifier = modifier
//    ) {
//        items(images) { imageResId ->
//            Box(
//                modifier = Modifier
//                    .size(72.dp)
//                    .clip(CircleShape)
//                    .background(Color(0x99F7B327))
//            ) {
//                Image(
//                    painter = painterResource(id = imageResId),
//                    contentDescription = null,
//                    modifier = Modifier
//                        .size(64.dp)
//                        .clip(CircleShape)
//                        .align(Alignment.Center)
//                )
//            }
//        }
//    }
//}
//
//
//
//
//

//
//
//
//@Preview
//@Composable
//fun PreviewSwipeCard() {
//    TryingScreen()
//}