package com.example.workmanagerrefact.domain

import java.io.File

interface ApplyColorFilterUseCase {
    suspend fun applyColorFilter(inputImage: File): File?
}