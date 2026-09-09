package com.aiphotostudio.app.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val StudioColors = lightColorScheme(
    primary = Color(0xFFD97757),
    onPrimary = Color.White,
    secondary = Color(0xFF557C78),
    background = Color(0xFFF7F4F0),
    surface = Color(0xFFFFFCF9),
    surfaceVariant = Color(0xFFECE7E1),
    onBackground = Color(0xFF292522),
    onSurface = Color(0xFF292522),
    onSurfaceVariant = Color(0xFF6E6760)
)

@Composable
fun AIPhotoStudioTheme(content: @Composable () -> Unit) {
    MaterialTheme(colorScheme = StudioColors, content = content)
}