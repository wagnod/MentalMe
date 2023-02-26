package com.wagnod.core_ui.theme

import androidx.compose.material.Colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

val lightTextPrimary = Color(0xFF191A1C)
@get:Composable
val Colors.textPrimary: Color
    get() = lightTextPrimary

val lightTextColorSecondary = Color(0xFF9194A6)
@get:Composable
val Colors.textColorSecondary: Color
    get() = lightTextColorSecondary

@get:Composable
val Colors.link: Color
    get() = Color(0xFF007AFF)

val primaryGraphics = Color(0xFF2A6049)
@get:Composable
val Colors.graphicsPrimary: Color
    get() = primaryGraphics

val errorRed = Color(0xFFE34446)
@get:Composable
val Colors.graphicsRed: Color
    get() = errorRed

val lightBackgroundPrimary = Color.White
@get:Composable
val Colors.backgroundPrimary: Color
    get() = lightBackgroundPrimary

@get:Composable
val Colors.backgroundSecondary: Color
    get() = Color(0xFFF4F5F8)

@get:Composable
val Colors.backgroundBrand: Color
    get() = primaryGraphics

val shadowPrimary = Color(0xFFA3A5A4)
@get:Composable
val Colors.shadowsPrimary: Color
    get() = shadowPrimary