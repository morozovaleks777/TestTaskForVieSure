package com.morozov.testtaskforviesure.ui.theme

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.morozov.testtaskforviesure.R


class BookAppTypography(
    val display1: TextStyle,
    val display2: TextStyle,
    val articleHeader: TextStyle,
    val h1: TextStyle,
    val h2: TextStyle,
    val h3: TextStyle,
    val h4: TextStyle,
    val h5: TextStyle,
    val p1: TextStyle,
    val p2: TextStyle,
    val cta: TextStyle,
    val cta2: TextStyle,
    val sub2: TextStyle,
    val sub1: TextStyle,
    val micro: TextStyle,
    val microBold: TextStyle,
    val hashTag: TextStyle,
    val numbers1: TextStyle,
    val numbers2: TextStyle,
    val colorLabel: TextStyle,

    val topicTitle: TextStyle,
    val p2SpanStyle: SpanStyle,
    val sub2SpanStyle: SpanStyle,
    val microSpanStyle: SpanStyle,

    val title1: TextStyle,

    val speedControl: TextStyle
)

val LibreFranklinFontFamily = FontFamily(
    Font(R.font.libre_franklin_thin, FontWeight.Thin), //100
    Font(R.font.libre_franklin_extra_light, FontWeight.ExtraLight), //200
    Font(R.font.libre_franklin_light, FontWeight.Light), //300
    Font(R.font.libre_franklin_regular, FontWeight.Normal), //400
    Font(R.font.libre_franklin_medium, FontWeight.Medium), //500
    Font(R.font.libre_franklin_semi_bold, FontWeight.SemiBold), //600
    Font(R.font.libre_franklin_bold, FontWeight.Bold), //700
    Font(R.font.libre_franklin_extra_bold, FontWeight.ExtraBold), //800
    Font(R.font.libre_franklin_black, FontWeight.Black), //900
)

val DefaultTypography = BookAppTypography(
    display1 = TextStyle(
        fontFamily = LibreFranklinFontFamily,
        fontSize = 42.sp,
        lineHeight = 50.4.sp,
        fontWeight = FontWeight.SemiBold,
    ),
    display2 = TextStyle(
        fontFamily = LibreFranklinFontFamily,
        fontSize = 36.sp,
        lineHeight = 43.2.sp,
        fontWeight = FontWeight.SemiBold,
        letterSpacing = 1.44.sp,
    ),
    articleHeader = TextStyle(
        fontFamily = LibreFranklinFontFamily,
        fontSize = 29.sp,
        lineHeight = 34.8.sp,
        fontWeight = FontWeight.Normal,
    ),
    h1 = TextStyle(
        fontFamily = LibreFranklinFontFamily,
        fontSize = 29.sp,
        lineHeight = 34.8.sp,
        fontWeight = FontWeight.SemiBold,
        letterSpacing = 1.16.sp,
    ),
    h2 = TextStyle(
        fontFamily = LibreFranklinFontFamily,
        fontSize = 24.sp,
        lineHeight = 28.8.sp,
        fontWeight = FontWeight.SemiBold,
        letterSpacing = 1.16.sp,
    ),
    h3 = TextStyle(
        fontFamily = LibreFranklinFontFamily,
        fontSize = 20.sp,
        lineHeight = 24.sp,
        fontWeight = FontWeight.SemiBold,
        letterSpacing = 2.sp,
    ),
    h4 = TextStyle(
        fontFamily = LibreFranklinFontFamily,
        fontSize = 17.sp,
        lineHeight = 20.4.sp,
        fontWeight = FontWeight.SemiBold,
        letterSpacing = 1.7.sp,
    ),
    h5 = TextStyle(
        fontFamily = LibreFranklinFontFamily,
        fontSize = 14.sp,
        lineHeight = 16.8.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = 1.4.sp,
    ),
    p1 = TextStyle(
        fontFamily = LibreFranklinFontFamily,
        fontSize = 17.sp,
        lineHeight = 25.5.sp,
        fontWeight = FontWeight.Normal,
        letterSpacing = 0.34.sp,
    ),
    p2 = TextStyle(
        fontFamily = LibreFranklinFontFamily,
        fontSize = 14.sp,
        lineHeight = 21.sp,
        fontWeight = FontWeight.Normal,
        letterSpacing = 0.28.sp,
    ),
    p2SpanStyle = SpanStyle(
        fontFamily = LibreFranklinFontFamily,
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal,
        letterSpacing = 0.28.sp,
    ),
    cta = TextStyle(
        fontFamily = LibreFranklinFontFamily,
        fontSize = 14.sp,
        lineHeight = 15.4.sp,
        fontWeight = FontWeight.SemiBold,
        textAlign = TextAlign.Center,
        letterSpacing = 0.84.sp,
    ),
    cta2 = TextStyle(
        fontFamily = LibreFranklinFontFamily,
        fontSize = 12.sp,
        lineHeight = 15.6.sp,
        fontWeight = FontWeight.SemiBold,
        letterSpacing = 0.48.sp,
    ),
    sub1 = TextStyle(
        fontFamily = LibreFranklinFontFamily,
        fontSize = 14.sp,
        lineHeight = 21.sp,
        fontWeight = FontWeight.SemiBold,
    ),
    sub2 = TextStyle(
        fontFamily = LibreFranklinFontFamily,
        fontSize = 12.sp,
        lineHeight = 14.4.sp,
        fontWeight = FontWeight.SemiBold,
    ),
    sub2SpanStyle = SpanStyle(
        fontFamily = LibreFranklinFontFamily,
        fontSize = 12.sp,
        fontWeight = FontWeight.SemiBold,
    ),
    microSpanStyle = SpanStyle(
        fontFamily = LibreFranklinFontFamily,
        fontSize = 12.sp,
        fontWeight = FontWeight.Normal,
        letterSpacing = 0.24.sp,
    ),
    micro = TextStyle(
        fontFamily = LibreFranklinFontFamily,
        fontSize = 12.sp,
        lineHeight = 18.sp,
        fontWeight = FontWeight.Normal,
        letterSpacing = 0.24.sp,
    ),
    microBold = TextStyle(
        fontFamily = LibreFranklinFontFamily,
        fontSize = 12.sp,
        lineHeight = 18.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = 0.24.sp,
    ),
    //used only in TopicsCard for #
    hashTag = TextStyle(
        fontFamily = LibreFranklinFontFamily,
        fontSize = 28.sp,
        lineHeight = 33.6.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFF525252),
        letterSpacing = 1.12.sp,
    ),
    numbers2 = TextStyle(
        fontFamily = LibreFranklinFontFamily,
        fontSize = 12.sp,
        lineHeight = 18.sp,
        fontWeight = FontWeight.Normal,
        letterSpacing = 0.96.sp,
    ),
    numbers1 = TextStyle(
        fontFamily = LibreFranklinFontFamily,
        fontSize = 14.sp,
        lineHeight = 21.sp,
        fontWeight = FontWeight.Normal,
        letterSpacing = 1.12.sp,
    ),
    colorLabel = TextStyle(
        fontFamily = LibreFranklinFontFamily,
        fontSize = 16.sp,
        lineHeight = 19.2.sp,
        fontWeight = FontWeight.SemiBold,
    ),
    topicTitle = TextStyle(
        fontFamily = LibreFranklinFontFamily,
        fontSize = 42.sp,
        lineHeight = 50.4.sp,
        fontWeight = FontWeight.SemiBold,
    ),
    speedControl = TextStyle(
        fontFamily = LibreFranklinFontFamily,
        fontSize = 14.sp,
        lineHeight = 23.8.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = 0.28.sp,
    ),
    title1 = TextStyle(
        fontFamily = LibreFranklinFontFamily,
        fontSize = 22.sp,
        lineHeight = 33.sp,
        fontWeight = FontWeight.Normal,
    ),
)

val SmallTypography = BookAppTypography(
    display1 = TextStyle(
        fontFamily = LibreFranklinFontFamily,
        fontSize = 33.6.sp,
        lineHeight = 40.32.sp,
        fontWeight = FontWeight.SemiBold
    ),
    display2 = TextStyle(
        fontFamily = FontFamily.Default,
        fontSize = 29.sp,
        lineHeight = 34.6.sp,
        fontWeight = FontWeight.SemiBold,
        letterSpacing = 1.15.sp,
    ),
    articleHeader = TextStyle(
        fontFamily = LibreFranklinFontFamily,
        fontSize = 23.2.sp,
        lineHeight = 27.84.sp,
        fontWeight = FontWeight.Normal,
    ),
    h1 = TextStyle(
        fontFamily = LibreFranklinFontFamily,
        fontSize = 27.sp,
        lineHeight = 32.sp,
        fontWeight = FontWeight.SemiBold,
        letterSpacing = 1.16.sp,
    ),
    h2 = TextStyle(
        fontFamily = LibreFranklinFontFamily,
        fontSize = 20.sp,
        lineHeight = 22.sp,
        fontWeight = FontWeight.SemiBold,
        letterSpacing = 0.95.sp,
    ),
    h3 = TextStyle(
        fontFamily = LibreFranklinFontFamily,
        fontSize = 18.sp,
        lineHeight = 22.sp,
        fontWeight = FontWeight.SemiBold,
        letterSpacing = 2.sp,
    ),
    h4 = TextStyle(
        fontFamily = LibreFranklinFontFamily,
        fontSize = 13.6.sp,
        lineHeight = 16.32.sp,
        fontWeight = FontWeight.SemiBold,
        letterSpacing = 1.7.sp,
    ),
    h5 = TextStyle(
        fontFamily = FontFamily.Default,
        fontSize = 12.sp,
        lineHeight = 14.sp,
        fontWeight = FontWeight.SemiBold,
        letterSpacing = 1.2.sp,
    ),
    p1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontSize = 14.sp,
        lineHeight = 21.sp,
        fontWeight = FontWeight.Normal,
        letterSpacing = 0.28.sp,
    ),
    p2 = TextStyle(
        fontFamily = FontFamily.Default,
        fontSize = 12.sp,
        lineHeight = 18.sp,
        fontWeight = FontWeight.Normal,
        letterSpacing = 0.24.sp,
    ),
    p2SpanStyle = SpanStyle(
        fontFamily = LibreFranklinFontFamily,
        fontSize = 12.sp,
        fontWeight = FontWeight.Normal,
        letterSpacing = 0.24.sp,
    ),
    cta = TextStyle(
        fontFamily = FontFamily.Default,
        fontSize = 12.sp,
        lineHeight = 12.4.sp,
        fontWeight = FontWeight.SemiBold,
        color = Color(0xFFFFFFFF),
        textAlign = TextAlign.Center,
        letterSpacing = 0.68.sp,
    ),
    cta2 = TextStyle(
        fontFamily = LibreFranklinFontFamily,
        fontSize = 12.sp,
        lineHeight = 12.4.sp,
        fontWeight = FontWeight.SemiBold,
        letterSpacing = 0.48.sp,
    ),
    sub1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontSize = 12.sp,
        lineHeight = 18.sp,
        fontWeight = FontWeight.SemiBold,
    ),
    sub2 = TextStyle(
        fontFamily = FontFamily.Default,
        fontSize = 10.sp,
        lineHeight = 12.sp,
        fontWeight = FontWeight.SemiBold,
    ),
    sub2SpanStyle = SpanStyle(
        fontFamily = FontFamily.Default,
        fontSize = 10.sp,
        fontWeight = FontWeight.SemiBold,
    ),
    microSpanStyle = SpanStyle(
        fontFamily = LibreFranklinFontFamily,
        fontSize = 10.sp,
        fontWeight = FontWeight.Normal,
        letterSpacing = 0.20.sp,
    ),
    micro = TextStyle(
        fontFamily = FontFamily.Default,
        fontSize = 10.sp,
        lineHeight = 16.sp,
        fontWeight = FontWeight.Normal,
        letterSpacing = 0.2.sp,
    ),
    microBold = TextStyle(
        fontFamily = FontFamily.Default,
        fontSize = 10.sp,
        lineHeight = 16.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = 0.2.sp,
    ),
    //used only in TopicsCard for #
    hashTag = TextStyle(
        fontFamily = FontFamily.Default,
        fontSize = 28.sp,
        lineHeight = 33.6.sp,
        fontWeight = FontWeight.Bold,
        color = Color(0xFF525252),
        letterSpacing = 1.12.sp,
    ),
    numbers1 = TextStyle(
        fontFamily = LibreFranklinFontFamily,
        fontSize = 11.sp,
        lineHeight = 17.sp,
        fontWeight = FontWeight.Normal,
        letterSpacing = 0.9.sp,
    ),
    numbers2 = TextStyle(
        fontFamily = FontFamily.Default,
        fontSize = 10.sp,
        lineHeight = 16.sp,
        fontWeight = FontWeight.Normal,
        letterSpacing = 0.78.sp,
    ),
    colorLabel = TextStyle(
        fontFamily = LibreFranklinFontFamily,
        fontSize = 12.8.sp,
        lineHeight = 15.36.sp,
        fontWeight = FontWeight.SemiBold,
    ),
    topicTitle = TextStyle(
        fontFamily = LibreFranklinFontFamily,
        fontSize = 32.sp,
        lineHeight = 38.4.sp,
        fontWeight = FontWeight.SemiBold,
    ),
    speedControl = TextStyle(
        fontFamily = LibreFranklinFontFamily,
        fontSize = 12.sp,
        lineHeight = 19.sp,
        fontWeight = FontWeight.Bold,
        letterSpacing = 0.22.sp,
    ),
    title1 = TextStyle(
        fontFamily = LibreFranklinFontFamily,
        fontSize = 18.sp,
        lineHeight = 26.sp,
        fontWeight = FontWeight.Normal,
    ),
)
