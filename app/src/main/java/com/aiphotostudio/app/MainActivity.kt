package com.aiphotostudio.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.lifecycle.viewmodel.compose.viewModel
import com.aiphotostudio.app.data.LocalEnhancementRepository
import com.aiphotostudio.app.ui.PhotoStudioScreen
import com.aiphotostudio.app.ui.PhotoStudioViewModel
import com.aiphotostudio.app.ui.theme.AIPhotoStudioTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AIPhotoStudioTheme {
                val factory = viewModelFactory {
                    initializer {
                        PhotoStudioViewModel(LocalEnhancementRepository(applicationContext))
                    }
                }
                Surface(modifier = Modifier.fillMaxSize()) {
                    PhotoStudioScreen(viewModel(factory = factory))
                }
            }
        }
    }
}