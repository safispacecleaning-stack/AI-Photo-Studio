package com.aiphotostudio.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.aiphotostudio.app.ui.PhotoStudioScreen
import com.aiphotostudio.app.ui.theme.AIPhotoStudioTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AIPhotoStudioTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    PhotoStudioScreen()
                }
            }
        }
    }
}