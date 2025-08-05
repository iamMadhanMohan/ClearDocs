package com.m2stack.cleardocs.presentation.scannedlist

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.m2stack.cleardocs.domain.model.ScanDocument
import com.m2stack.cleardocs.domain.repository.ScanDocumentRepository
import com.m2stack.cleardocs.domain.usecase.DeleteScanDocumentsUseCase
import com.m2stack.cleardocs.domain.usecase.ExportScanDocumentsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ScannedListViewModel @Inject constructor(
    private val repository: ScanDocumentRepository,
    private val deleteUseCase: DeleteScanDocumentsUseCase,
    private val exportUseCase: ExportScanDocumentsUseCase
) : ViewModel() {

    val scannedDocuments: StateFlow<List<ScanDocument>> =
        repository.getAllDocuments()
            .map { it.sortedByDescending { doc -> doc.timestamp } }
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    fun deleteDocuments(docs: List<ScanDocument>) {
        viewModelScope.launch {
            deleteUseCase(docs)
            docs.forEach { repository.deleteDocument(it) }
        }
    }

    fun exportDocuments(context: Context, docs: List<ScanDocument>, onComplete: (Int) -> Unit) {
        viewModelScope.launch {
            val count = exportUseCase(context, docs)
            withContext(Dispatchers.Main) { onComplete(count) }
        }
    }
}
