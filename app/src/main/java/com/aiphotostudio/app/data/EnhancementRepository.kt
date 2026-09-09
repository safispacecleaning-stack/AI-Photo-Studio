package com.aiphotostudio.app.data

import android.net.Uri
import com.aiphotostudio.app.domain.EnhancementMode

interface EnhancementRepository {
    suspend fun enhance(photoUri: Uri, mode: EnhancementMode): Uri
}