package com.m2stack.cleardocs.domain.usecase

import com.m2stack.cleardocs.domain.model.ScanDocument
import com.m2stack.cleardocs.domain.repository.ScanDocumentRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetScanDocumentByIdUseCase @Inject constructor(
    private val repository: ScanDocumentRepository
) {
    operator fun invoke(id: Int): Flow<ScanDocument?> {
        return repository.getAllDocuments()
            .map { list -> list.find { it.id == id } }
    }
}
