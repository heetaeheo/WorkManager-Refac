package com.example.workmanagerrefact.presentation

import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.core.net.toUri
import androidx.work.CoroutineWorker
import androidx.work.ForegroundInfo
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import com.example.workmanagerrefact.R
import com.example.workmanagerrefact.domain.DownloadImageUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import kotlin.random.Random
import kotlin.random.Random.Default.nextInt

class DownloadWorker @AssistedInject constructor(
    @Assisted private val context: Context,
    @Assisted private val workerParameters: WorkerParameters,
    private val downloadImageUseCase: DownloadImageUseCase
) : CoroutineWorker(context, workerParameters) {

    override suspend fun doWork(): Result {
        startForegroundService()
        delay(5000L)
        val response = downloadImageUseCase.execute()
        response.body()?.let { body ->
            return withContext(Dispatchers.IO) {
                val file = File(context.cacheDir, "image.jpg")
                val outputStream = FileOutputStream(file)
                outputStream.use { stream ->
                    val result = kotlin.runCatching {
                        stream.write(body.bytes())
                    }
                    return@withContext when {
                        result.isSuccess -> Result.success(
                            workDataOf(
                                WorkerKeys.IMAGE_URI to file.toUri().toString()
                            )
                        )
                        result.isFailure -> Result.failure(
                            workDataOf(
                                WorkerKeys.ERROR_MSG to (result.exceptionOrNull()?.localizedMessage ?: "Unknown error")
                            )
                        )
                        else -> Result.failure(
                            workDataOf(
                                WorkerKeys.ERROR_MSG to "Unknown error"
                            )
                        )
                    }
                }
            }
        }
        if (!response.isSuccessful) {
            if (response.code().toString().startsWith("5")) {
                return Result.retry()
            }
            return Result.failure(
                workDataOf(
                    WorkerKeys.ERROR_MSG to "Network error"
                )
            )
        }
        return Result.failure(
            workDataOf(
                WorkerKeys.ERROR_MSG to "Unknown error"
            )
        )
    }



    companion object {
        const val DOWNLOAD_CHANNEL_ID = "download_channel"
    }

    private suspend fun startForegroundService() {
        setForeground(
            ForegroundInfo(
                nextInt(),
                NotificationCompat.Builder(context, DOWNLOAD_CHANNEL_ID)
                    .setSmallIcon(R.drawable.ic_launcher_background)
                    .setContentText(context.getString(R.string.download_notification_content_text))
                    .setContentTitle(context.getString(R.string.download_notification_content_title))
                    .build()
            )
        )
    }

    interface ChildWorkerFacotry{
        fun create(context: Context, workerParams: WorkerParameters): DownloadWorker
    }

}