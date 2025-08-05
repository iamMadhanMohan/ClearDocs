package com.m2stack.cleardocs.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.m2stack.cleardocs.domain.usecase.InsertScanDocumentUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScanDocumentViewModel @Inject constructor(
    private val insertScanDocumentUseCase: InsertScanDocumentUseCase
) : ViewModel() {

    fun insertScanDocument(filePath: String, title: String) {
        viewModelScope.launch(Dispatchers.IO) {
            insertScanDocumentUseCase(filePath, title)
        }
    }
}

