package com.example.workmanagerrefact.presentation

import android.content.Context
import androidx.work.WorkerParameters

interface ChildWorkerFactory {
    fun create(context: Context, workerParams: WorkerParameters): DownloadWorker
}