package com.example.workmanagerrefact.domain

import com.example.workmanagerrefact.data.FileRepository
import okhttp3.ResponseBody
import retrofit2.Response
import javax.inject.Inject

class DownloadImageUseCase @Inject constructor(
    private val fileRepository: FileRepository
) {
    suspend fun execute(): Response<ResponseBody>{
        return fileRepository.downloadImage()
    }
}