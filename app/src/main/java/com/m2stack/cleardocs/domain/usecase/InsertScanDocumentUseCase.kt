package com.m2stack.cleardocs.domain.usecase

import com.m2stack.cleardocs.domain.model.ScanDocument
import com.m2stack.cleardocs.domain.repository.ScanDocumentRepository
import javax.inject.Inject

class InsertScanDocumentUseCase @Inject constructor(
    private val repository: ScanDocumentRepository
) {
    suspend operator fun invoke(filePath: String, title: String) {
        val scanDoc = ScanDocument(
            title = title,
            filePath = filePath,
            timestamp = System.currentTimeMillis()
        )
        repository.insertDocument(scanDoc)
    }
}
