package com.m2stack.cleardocs.data.mapper

import com.m2stack.cleardocs.data.local.entity.ScanDocumentEntity
import com.m2stack.cleardocs.domain.model.ScanDocument

fun ScanDocumentEntity.toDomain(): ScanDocument {
    return ScanDocument(id, title, filePath, timestamp)
}

fun ScanDocument.toEntity(): ScanDocumentEntity {
    return ScanDocumentEntity(id, title, filePath, timestamp)
}
