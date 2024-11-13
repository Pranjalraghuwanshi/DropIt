package com.example.dropit.ui.presentation.common

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForwardIos
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.rememberSwipeableState
import androidx.compose.material.swipeable
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.dropit.ui.theme.ColorOrange

@Composable
private fun DraggableControl(
    modifier: Modifier,
    progress: Float
) {
    val isConfirmed = remember { derivedStateOf { progress >= 0.8f } }

    Box(
        modifier = modifier
            .padding(4.dp)
            .shadow(
                elevation = 2.dp,
                CircleShape,
                clip = false
            )
            .background(Color.White, CircleShape),
        contentAlignment = Alignment.Center
    ) {
        Crossfade(targetState = isConfirmed.value) { confirmed ->
            if (confirmed) {
                Icon(
                    imageVector = Icons.Filled.Done,
                    contentDescription = "Confirm Icon",
                    tint = ColorOrange
                )
            } else {
                Icon(
                    imageVector = Icons.Filled.ArrowForwardIos,
                    contentDescription = "Forward Icon",
                    tint = ColorOrange
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ConfirmationButton(
    modifier: Modifier = Modifier,
    onMove: () -> Unit
) {
    val width = 350.dp
    val dragSize = 50.dp
    val swipeableState = rememberSwipeableState(initialValue = ConfirmationState.DEFAULT)
    val sizePx = with(LocalDensity.current) { (width - dragSize).toPx() }
    val anchors = mapOf(0f to ConfirmationState.DEFAULT, sizePx to ConfirmationState.CONFIRMED)
    val progress = remember {
        derivedStateOf {
            if (swipeableState.offset.value == 0f) 0f else swipeableState.offset.value / sizePx
        }
    }

    // Trigger navigation when confirmed
    androidx.compose.runtime.LaunchedEffect(swipeableState.currentValue) {
        if (swipeableState.currentValue == ConfirmationState.CONFIRMED) {
            onMove()
        }
    }

    Box(
        modifier = modifier
            .width(width)
            .swipeable(
                state = swipeableState,
                anchors = anchors,
                thresholds = { _, _ -> FractionalThreshold(0.5f) },
                orientation = Orientation.Horizontal
            )
            .background(ColorOrange, RoundedCornerShape(dragSize))
    ) {
        androidx.compose.foundation.layout.Column(
            Modifier
                .align(Alignment.Center)
                .graphicsLayer(alpha = 1f - progress.value),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            androidx.compose.material3.Text(
                text = "Swipe It.",
                color = Color.White,
                fontSize = 18.sp,
                fontFamily = FontFamily.Cursive
            )
        }

        DraggableControl(
            modifier = Modifier
                .offset { IntOffset(swipeableState.offset.value.toInt(), 0) }
                .size(dragSize),
            progress = progress.value
        )
    }
}

enum class ConfirmationState {
    DEFAULT,
    CONFIRMED
}