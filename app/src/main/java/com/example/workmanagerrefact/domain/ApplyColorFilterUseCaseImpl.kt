package com.example.workmanagerrefact.domain

import android.content.Context
import android.graphics.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream

class ApplyColorFilterUseCaseImpl(
    private val context: Context
) : ApplyColorFilterUseCase {
    override suspend fun applyColorFilter(inputImage: File): File? {
        val bmp = BitmapFactory.decodeFile(inputImage.absolutePath)
        val resultBmp = bmp.copy(bmp.config, true)
        val paint = Paint()
        paint.colorFilter = LightingColorFilter(0x08FF04, 1)
        val canvas = Canvas(resultBmp)
        canvas.drawBitmap(resultBmp, 0f, 0f, paint)

        return withContext(Dispatchers.IO) {
            val resultImageFile = File(context.cacheDir, "new-image.jpg")
            val outputStream = FileOutputStream(resultImageFile)
            val successful = resultBmp.compress(
                Bitmap.CompressFormat.JPEG,
                90,
                outputStream
            )
            if (successful) {
                resultImageFile
            } else null
        }
    }
}
