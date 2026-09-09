package com.aiphotostudio.app.data

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.ColorMatrix
import android.graphics.ColorMatrixColorFilter
import android.graphics.Paint
import android.net.Uri
import androidx.core.content.FileProvider
import com.aiphotostudio.app.domain.EnhancementMode
import java.io.File
import java.io.FileOutputStream
import java.util.UUID

class LocalEnhancementRepository(private val context: Context) : EnhancementRepository {
    override suspend fun enhance(photoUri: Uri, mode: EnhancementMode): Uri {
        val source = context.contentResolver.openInputStream(photoUri)?.use(BitmapFactory::decodeStream)
            ?: error("Unable to decode the selected photo")
        val output = Bitmap.createBitmap(source.width, source.height, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(output)
        val paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            colorFilter = ColorMatrixColorFilter(colorMatrixFor(mode))
        }
        canvas.drawBitmap(source, 0f, 0f, paint)
        source.recycle()

        val file = File(context.cacheDir, "enhanced-${UUID.randomUUID()}.jpg")
        FileOutputStream(file).use { stream ->
            check(output.compress(Bitmap.CompressFormat.JPEG, 94, stream)) {
                "Unable to save the enhanced photo"
            }
        }
        output.recycle()
        return FileProvider.getUriForFile(context, "${context.packageName}.fileprovider", file)
    }

    private fun colorMatrixFor(mode: EnhancementMode): ColorMatrix = when (mode) {
        EnhancementMode.PROFESSIONAL -> ColorMatrix(floatArrayOf(
            1.08f, 0f, 0f, 0f, -6f,
            0f, 1.08f, 0f, 0f, -6f,
            0f, 0f, 1.08f, 0f, -6f,
            0f, 0f, 0f, 1f, 0f
        ))
        EnhancementMode.SOCIAL_MEDIA -> ColorMatrix().apply {
            setSaturation(1.28f)
            postConcat(ColorMatrix(floatArrayOf(
                1.08f, 0f, 0f, 0f, 4f,
                0f, 1.08f, 0f, 0f, 4f,
                0f, 0f, 1.08f, 0f, 4f,
                0f, 0f, 0f, 1f, 0f
            )))
        }
        EnhancementMode.ID_PHOTO -> ColorMatrix().apply {
            setSaturation(0.2f)
            postConcat(ColorMatrix(floatArrayOf(
                1.12f, 0f, 0f, 0f, 8f,
                0f, 1.12f, 0f, 0f, 8f,
                0f, 0f, 1.12f, 0f, 8f,
                0f, 0f, 0f, 1f, 0f
            )))
        }
    }
}