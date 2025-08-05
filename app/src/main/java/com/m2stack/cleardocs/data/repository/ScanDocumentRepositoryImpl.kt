package com.m2stack.cleardocs.data.repository


import com.m2stack.cleardocs.data.local.dao.ScanDocumentDao
import com.m2stack.cleardocs.data.mapper.toDomain
import com.m2stack.cleardocs.data.mapper.toEntity
import com.m2stack.cleardocs.domain.model.ScanDocument
import com.m2stack.cleardocs.domain.repository.ScanDocumentRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ScanDocumentRepositoryImpl(
    private val dao: ScanDocumentDao
) : ScanDocumentRepository {

    override suspend fun insertDocument(doc: ScanDocument) {
        dao.insertDocument(doc.toEntity())
    }

    override fun getAllDocuments(): Flow<List<ScanDocument>> {
        return dao.getAllDocuments().map { list -> list.map { it.toDomain() } }
    }

    override suspend fun deleteDocument(doc: ScanDocument) {
        dao.deleteDocument(doc.toEntity())
    }
}
