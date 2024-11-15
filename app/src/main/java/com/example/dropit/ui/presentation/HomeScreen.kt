package com.example.dropit.ui.presentation

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.positionChange
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.dropit.R
import com.example.dropit.ui.theme.ColorOrange
import kotlin.math.roundToInt

@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        topBar = {
            TopBar(
                onSearchClicked = {
//                    navController.navigate("search")
                }
            )
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
            SwipeCardExample(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(top = 32.dp)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    onSearchClicked: () -> Unit
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

                Spacer(modifier = Modifier.weight(1f)) // Spacer to push search icon to the right

                // Right side search icon
                IconButton(
                    onClick = onSearchClicked,
                    modifier = Modifier
                        .clip(RoundedCornerShape(28.dp))
                        .size(48.dp)
                        .background(ColorOrange)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.search), // Replace with your search icon drawable
                        contentDescription = "Search",
                        tint = Color.White
                    )
                }
            }
        },
        modifier = Modifier,
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.White
        )
    )
}

@Composable
fun SwipeCardExample(modifier: Modifier) {
    val images = remember {
        mutableStateListOf(
            CardData(0, R.drawable.onboardd),
            CardData(1, R.drawable.onboard),
            CardData(2, R.drawable.onboarddd),
        )
    }
    var currentImageIndex by remember { mutableIntStateOf(0) }
    val iconStates = remember { mutableStateMapOf<Int, Pair<Boolean, Boolean>>() }
    val cardModifier = modifier
        .size(266.dp, 378.dp)

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        for (i in 0 until images.size) {
            val imageIndex = (currentImageIndex - i + images.size) % images.size
            val rotation = when (i) {
                0 -> -15f
                1 -> 15f
                2 -> 0f
                else -> 0f
            }
            SwipeCard(
                modifier = cardModifier
                    .zIndex((images.size + i).toFloat())
                    .graphicsLayer {
                        rotationZ = rotation
                    },
                cardData = images[imageIndex],
                onSwipeLeft = { cardData ->
                    iconStates[cardData.id] = true to false
                },
                onSwipeRight = { cardData ->
                    iconStates[cardData.id] = false to true
                },
                onDragEnd = { cardData ->
                    iconStates[cardData.id] = false to false
                    currentImageIndex = (currentImageIndex + 1) % images.size
                },
                sensitivityFactor = 3f
            ) { cardData ->
                Card(
                    modifier = Modifier.wrapContentSize(),
                    shape = RoundedCornerShape(16.dp),
                    border = BorderStroke(4.dp, ColorOrange)
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.wrapContentSize()
                    ) {
                        Image(
                            painter = painterResource(images[imageIndex].image),
                            contentDescription = null,
                            modifier = Modifier
                                .align(Alignment.Center)
                                .fillMaxSize()
                        )
                        Row(
                            modifier = Modifier
                                .align(Alignment.BottomCenter)
                                .padding(16.dp)
                        ) {
                            val (favIcon, closeIcon) = iconStates[cardData.id] ?: (false to false)
                            GradientCircleWithIcon(
                                icon = Icons.Default.FavoriteBorder,
                                selectedIcon = favIcon
                            )
                            Spacer(modifier = Modifier.width(16.dp))
                            GradientCircleWithIcon(
                                icon = Icons.Default.Close,
                                selectedIcon = closeIcon
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun SwipeCard(
    modifier: Modifier,
    cardData: CardData,
    onSwipeLeft: (CardData) -> Unit = {},
    onSwipeRight: (CardData) -> Unit = {},
    onDragEnd: (CardData) -> Unit = {},
    sensitivityFactor: Float = 3f,
    content: @Composable (CardData) -> Unit,
) {
    var offset by remember { mutableFloatStateOf(0f) }
    var dismissRight by remember { mutableStateOf(false) }
    var dismissLeft by remember { mutableStateOf(false) }
    val density = LocalDensity.current.density

    LaunchedEffect(dismissRight) {
        if (dismissRight) {
            onSwipeRight.invoke(cardData)
            dismissRight = false
        }
    }

    LaunchedEffect(dismissLeft) {
        if (dismissLeft) {
            onSwipeLeft.invoke(cardData)
            dismissLeft = false
        }
    }
    var dragStarted by remember { mutableStateOf(false) }

    Box(modifier = modifier
        .offset { IntOffset(offset.roundToInt(), 0) }
        .pointerInput(Unit) {
            detectHorizontalDragGestures(
                onDragStart = { dragStarted = true },
                onDragEnd = {
                    dragStarted = false
                    offset = 0f
                    onDragEnd(cardData)
                    dismissLeft = false
                    dismissRight = false
                }
            ) { change, dragAmount ->
                if (dragStarted) {
                    offset += (dragAmount / density) * sensitivityFactor
                }

                if (offset > 0) {
                    dismissRight = true
                } else if (offset < 0) {
                    dismissLeft = true
                }

                if (change.positionChange() != Offset.Zero) change.consume()
            }
        }
        .graphicsLayer(
            alpha = 10f - animateFloatAsState(if (dismissRight) 1f else 0f, label = "").value,
            rotationZ = animateFloatAsState(offset / 50, label = "").value
        )) {
        content(cardData)
    }
}

@Composable
fun GradientCircleWithIcon(icon: ImageVector, selectedIcon: Boolean) {
    Box(
        modifier = Modifier
            .size(53.dp)
    ) {
        Canvas(
            modifier = Modifier
                .matchParentSize()
        ) {
            val circleColor = if (selectedIcon) {
                Brush.linearGradient(
                    colors = listOf(Color(0xFFEF7E06), Color(0xFFF7B327)),
                    start = Offset.Zero,
                    end = Offset.Infinite
                )
            } else {
                SolidColor(Color.White)
            }

            drawCircle(
                brush = circleColor,
                radius = size.minDimension / 2,
                center = Offset(size.width / 2, size.height / 2)
            )
        }
        Icon(
            imageVector = icon,
            contentDescription = "Icon",
            tint = if (selectedIcon) Color.White else Color(0xFFEF7E06),
            modifier = Modifier
                .size(24.dp)
                .align(Alignment.Center)
        )
    }
}

data class CardData(
    val id: Int,
    val image: Int,
    var favIcon: Boolean = false,
    var closeIcon: Boolean = false
)

@Preview
@Composable
fun HomeScreenPreview() {
    HomeScreen(rememberNavController())
}