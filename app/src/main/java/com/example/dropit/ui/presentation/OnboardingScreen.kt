package com.example.dropit.ui.presentation

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.dropit.MainActivity
import com.example.dropit.R
import com.example.dropit.ui.theme.ColorBlue
import com.example.dropit.ui.theme.ColorOrange
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import kotlinx.coroutines.launch

data class OnBoardingData(
    val image: Int, val title: String,
    val desc: String,
    val backgroundColor: Color,
    val mainColor: Color = ColorBlue
)

@Composable
fun OnboardingScreen(navController: NavHostController, context: MainActivity) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(color = Color.Transparent, darkIcons = true)

    Surface(modifier = Modifier.fillMaxSize()) {
        val items = listOf(
            OnBoardingData(
                R.drawable.onboard,
                "Hmmm, Healthy Food",
                "A variety of healthy foods made by the best chefs. Ingredients are easy to find. all delicious flavors can only be found at cookbunda",
                backgroundColor = Color(0xFF0189C5),
                mainColor = ColorOrange
            ),
            OnBoardingData(
                R.drawable.onboardd,
                "Fresh Drinks, Stay Fresh",
                "Not only food. we provide clear healthy drink options for you. Fresh taste always accompanies you",
                backgroundColor = Color(0xFFE4AF19),
                mainColor = ColorOrange
            ),
            OnBoardingData(
                R.drawable.onboarddd,
                "Let's Cooking",
                "Are you ready to make a dish for your friends or family? create an account and cook",
                backgroundColor = Color(0xFF96E172),
                mainColor = ColorOrange
            )
        )

        val pagerState = rememberPagerState(
            pageCount = { items.size },
            initialPage = 0,
        )

        OnBoardingPager(
            item = items, pagerState = pagerState, modifier = Modifier
                .fillMaxWidth()
                .background(color = ColorBlue)
        )
    }
}


@Composable
fun OnBoardingPager(
    item: List<OnBoardingData>,
    pagerState: PagerState,
    modifier: Modifier = Modifier
) {
    val coroutineScope = rememberCoroutineScope()

    Box(modifier = modifier) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            HorizontalPager(state = pagerState) { page ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(item[page].backgroundColor),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(id = item[page].image),
                        contentDescription = item[page].title,
                        modifier = Modifier
                            .size(600.dp)
                            .align(Alignment.CenterHorizontally)
                    )
                }
            }
        }

        Box(modifier = Modifier.align(Alignment.BottomCenter)) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(340.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White),
                elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                shape = RoundedCornerShape(8.dp)
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    PagerIndicator(items = item, currentPage = pagerState.currentPage)
                    Text(
                        text = item[pagerState.currentPage].title,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 20.dp, end = 30.dp),
                        color = item[pagerState.currentPage].mainColor,
                        fontFamily = FontFamily.Cursive,
                        textAlign = TextAlign.Right,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.ExtraBold
                    )

                    Text(
                        text = item[pagerState.currentPage].desc,
                        modifier = Modifier.padding(top = 20.dp, start = 40.dp, end = 20.dp),
                        color = Color.Gray,
                        fontFamily = FontFamily.Cursive,
                        fontSize = 17.sp,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.ExtraLight
                    )
                }
                Box(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(30.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        if (pagerState.currentPage != 2) {
                            OutlinedButton(
                                onClick = {
                                    coroutineScope.launch {
                                        val nextPage = pagerState.currentPage + 1
                                        if (nextPage < pagerState.pageCount) {
                                            pagerState.animateScrollToPage(nextPage)
                                        }
                                    }
                                },
                                border = BorderStroke(2.dp, item[pagerState.currentPage].mainColor),
                                shape = CircleShape,
                                colors = ButtonDefaults.outlinedButtonColors(contentColor = item[pagerState.currentPage].mainColor),
                                modifier = Modifier.size(65.dp)
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.forward),
                                    contentDescription = "Next",
                                    tint = item[pagerState.currentPage].mainColor,
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                        } else {
                            Button(
                                onClick = { /* Navigate to Home Screen */ },
                                modifier = Modifier.fillMaxWidth(),
                                colors = ButtonDefaults.buttonColors(containerColor = item[pagerState.currentPage].mainColor),
                                contentPadding = PaddingValues(vertical = 12.dp),
                                elevation = ButtonDefaults.buttonElevation(defaultElevation = 0.dp)
                            ) {
                                Text(
                                    text = "Get Started",
                                    color = Color.White,
                                    fontSize = 16.sp
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun PagerIndicator(currentPage: Int, items: List<OnBoardingData>) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.padding(top = 20.dp)
    ) {
        items.forEachIndexed { index, item ->
            Indicator(isSelected = index == currentPage, color = item.mainColor)
        }
    }
}

@Composable
fun Indicator(isSelected: Boolean, color: Color) {
    val width = animateDpAsState(targetValue = if (isSelected) 40.dp else 10.dp)

    Box(
        modifier = Modifier
            .padding(4.dp)
            .height(10.dp)
            .width(width.value)
            .clip(CircleShape)
            .background(if (isSelected) color else Color.Gray.copy(alpha = 0.5f))
    )
}
