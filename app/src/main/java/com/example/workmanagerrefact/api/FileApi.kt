package com.example.workmanagerrefact.api

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET

interface FileApi {
    @GET("/wp-content/uploads/2022/02/220849-scaled.jpg")
    suspend fun downloadImage(): Response<ResponseBody>
}