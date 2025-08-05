package com.m2stack.cleardocs.presentation.scanviewer

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.m2stack.cleardocs.domain.model.ScanDocument
import com.m2stack.cleardocs.domain.usecase.DeleteScanDocumentUseCase
import com.m2stack.cleardocs.domain.usecase.ExportScanDocumentUseCase
import com.m2stack.cleardocs.domain.usecase.GetScanDocumentByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


@HiltViewModel
class ScanDetailViewModel @Inject constructor(
    private val getDocById: GetScanDocumentByIdUseCase,
    private val deleteUseCase: DeleteScanDocumentUseCase,
    private val exportUseCase: ExportScanDocumentUseCase
) : ViewModel() {

    private val _scanDocument = MutableStateFlow<ScanDocument?>(null)
    val scanDocument: StateFlow<ScanDocument?> = _scanDocument

    fun loadDocument(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            getDocById(id).collect {
                _scanDocument.value = it
            }
        }
    }

    fun delete( onComplete: () -> Unit) {
        viewModelScope.launch {
            _scanDocument.value?.let {
                deleteUseCase(it)
                onComplete()
            }
        }
    }

    fun export(context: Context, onComplete: (Boolean) -> Unit) {
        viewModelScope.launch {
            _scanDocument.value?.let {
                val result = exportUseCase(context, it)
                onComplete(result)
            }
        }
    }
}

