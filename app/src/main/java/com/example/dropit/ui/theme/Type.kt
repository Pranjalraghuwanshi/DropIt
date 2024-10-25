package com.example.dropit.ui.theme


import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.dropit.R

@RequiresApi(Build.VERSION_CODES.Q)
val fontRoboto = FontFamily(Font(R.font.roboto))
val fontRobotoMono = FontFamily(Font(R.font.roboto_mono))
val fontRobotoMonoThin = FontFamily(Font(R.font.roboto_mono_thin))
val fontMontserrat = FontFamily(Font(R.font.montserrat))

val durationTextStyle = TextStyle(
    fontSize = 20.sp,
    fontFamily = fontRobotoMono
)

var h1TextStyle = TextStyle(
    fontSize = 24.sp,
    fontFamily = fontMontserrat,
    fontWeight = FontWeight.Bold
)

var h2TextStyle = TextStyle(
    fontSize = 20.sp,
    fontFamily = fontMontserrat,
    fontWeight = FontWeight.Bold
)
var h3TextStyle = TextStyle(
    fontSize = 16.sp,
    fontFamily = fontMontserrat,
    fontWeight = FontWeight.Bold
)
var h4TextStyle = TextStyle(
    fontSize = 14.sp,
    fontFamily = fontMontserrat,
    fontWeight = FontWeight.Medium
)


val infoTextStyle = TextStyle(
    fontSize = 18.sp,
    fontFamily = fontMontserrat,
    fontWeight = FontWeight.SemiBold
)

@RequiresApi(Build.VERSION_CODES.Q)
val infoDescTextStyle = TextStyle(
    fontSize = 14.sp,
    fontFamily = fontRoboto
)

@RequiresApi(Build.VERSION_CODES.Q)
var taskDescTextStyle = TextStyle(
    fontSize = 12.sp,
    fontFamily = fontRoboto
)

@RequiresApi(Build.VERSION_CODES.Q)
var taskTextStyle = TextStyle(
    fontSize = 16.sp,
    fontFamily = fontRoboto,
)

var timerTextStyle = TextStyle(
    fontSize = 42.sp,
    fontFamily = fontRobotoMonoThin
)
