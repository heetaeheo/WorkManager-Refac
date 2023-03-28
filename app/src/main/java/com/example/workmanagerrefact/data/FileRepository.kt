package com.example.workmanagerrefact.data

import okhttp3.ResponseBody
import retrofit2.Response

interface FileRepository {
    suspend fun downloadImage(): Response<ResponseBody>
}