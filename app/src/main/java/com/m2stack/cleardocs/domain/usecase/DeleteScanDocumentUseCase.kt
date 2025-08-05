package com.m2stack.cleardocs.domain.usecase

import com.m2stack.cleardocs.domain.model.ScanDocument
import com.m2stack.cleardocs.domain.repository.ScanDocumentRepository
import jakarta.inject.Inject
import java.io.File

class DeleteScanDocumentUseCase @Inject constructor(
    private val repository: ScanDocumentRepository
) {
    suspend operator fun invoke(doc: ScanDocument) {
        File(doc.filePath).delete() // Still in domain layer for now, could be wrapped if needed
        repository.deleteDocument(doc)
    }
}
