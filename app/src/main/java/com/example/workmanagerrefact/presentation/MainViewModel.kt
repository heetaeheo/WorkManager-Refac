package com.example.workmanagerrefact.presentation

import androidx.lifecycle.ViewModel
import androidx.work.*
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val workManager: WorkManager,
    private val downloadRequest: OneTimeWorkRequest,
    private val colorFilterRequest: OneTimeWorkRequest
) : ViewModel() {

}
