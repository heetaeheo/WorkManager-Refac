package com.example.workmanagerrefact.di

import com.example.workmanagerrefact.api.FileApi
import com.example.workmanagerrefact.data.FileRepository
import com.example.workmanagerrefact.domain.FileRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideFileRepository(fileApi: FileApi): FileRepository{
        return FileRepositoryImpl(fileApi)
    }
}