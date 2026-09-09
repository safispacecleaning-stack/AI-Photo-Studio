package com.aiphotostudio.app.data

import android.net.Uri
import com.aiphotostudio.app.domain.EnhancementMode

class FakeEnhancementRepository : EnhancementRepository {
    override suspend fun enhance(photoUri: Uri, mode: EnhancementMode): Uri = photoUri
}