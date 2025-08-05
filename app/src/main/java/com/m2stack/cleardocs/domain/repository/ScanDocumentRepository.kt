package com.m2stack.cleardocs.domain.repository

import com.m2stack.cleardocs.domain.model.ScanDocument
import kotlinx.coroutines.flow.Flow

interface ScanDocumentRepository {
    suspend fun insertDocument(doc: ScanDocument)
    fun getAllDocuments(): Flow<List<ScanDocument>>
    suspend fun deleteDocument(doc: ScanDocument)
}
