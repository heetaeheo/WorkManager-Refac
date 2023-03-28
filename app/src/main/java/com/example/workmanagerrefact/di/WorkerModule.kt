package com.example.workmanagerrefact.di

import androidx.hilt.work.HiltWorkerFactory
import androidx.work.WorkerFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object WorkerModule {
    @Provides
    fun provideHiltWorkerFactory(workerFactory: HiltWorkerFactory): WorkerFactory {
        return workerFactory
    }


}
