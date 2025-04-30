package com.example.diceroller.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF1565C0), // Material Blue 800
    secondary = Color(0xFF1E88E5), // Material Blue 600
    tertiary = Color(0xFF42A5F5), // Material Blue 400
    background = Color(0xFF0D47A1) // Dark Blue
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF1976D2), // Material Blue 700
    secondary = Color(0xFF2196F3), // Material Blue 500
    tertiary = Color(0xFF64B5F6), // Material Blue 300
    background = Color(0xFFBBDEFB) // Light Blue
)

@Composable
fun DiceRollerTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}