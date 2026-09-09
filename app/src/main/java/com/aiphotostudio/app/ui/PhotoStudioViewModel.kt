package com.aiphotostudio.app.ui

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aiphotostudio.app.data.EnhancementRepository
import com.aiphotostudio.app.data.FakeEnhancementRepository
import com.aiphotostudio.app.domain.EnhancementMode
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class PhotoStudioUiState(
    val selectedPhoto: Uri? = null,
    val selectedMode: EnhancementMode = EnhancementMode.PROFESSIONAL,
    val isEnhancing: Boolean = false,
    val enhancedPhoto: Uri? = null
)

class PhotoStudioViewModel(
    private val enhancementRepository: EnhancementRepository = FakeEnhancementRepository()
) : ViewModel() {
    private val _uiState = MutableStateFlow(PhotoStudioUiState())
    val uiState: StateFlow<PhotoStudioUiState> = _uiState.asStateFlow()

    fun selectPhoto(uri: Uri) {
        _uiState.value = _uiState.value.copy(selectedPhoto = uri, enhancedPhoto = null)
    }

    fun selectMode(mode: EnhancementMode) {
        _uiState.value = _uiState.value.copy(selectedMode = mode, enhancedPhoto = null)
    }

    fun enhance() {
        val state = _uiState.value
        val photo = state.selectedPhoto ?: return
        viewModelScope.launch {
            _uiState.value = state.copy(isEnhancing = true)
            val result = enhancementRepository.enhance(photo, state.selectedMode)
            _uiState.value = _uiState.value.copy(isEnhancing = false, enhancedPhoto = result)
        }
    }
}