package com.example.workmanagerrefact.domain

import com.example.workmanagerrefact.api.FileApi
import com.example.workmanagerrefact.data.FileRepository
import okhttp3.ResponseBody
import retrofit2.Response
import javax.inject.Inject

class FileRepositoryImpl @Inject constructor(
    private val fileApi: FileApi
): FileRepository{

    override suspend fun downloadImage(): Response<ResponseBody> {
        return fileApi.downloadImage()
    }

}