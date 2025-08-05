package com.m2stack.cleardocs.domain.usecase

import com.m2stack.cleardocs.domain.model.ScanDocument
import jakarta.inject.Inject
import java.io.File

class DeleteScanDocumentsUseCase @Inject constructor() {
    operator fun invoke(documents: List<ScanDocument>) {
        documents.forEach { File(it.filePath).delete() }
    }
}
