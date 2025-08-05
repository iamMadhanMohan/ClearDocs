package com.m2stack.cleardocs.domain.usecase

import android.content.Context
import com.m2stack.cleardocs.domain.model.ScanDocument
import com.m2stack.cleardocs.utils.ExportUtils
import jakarta.inject.Inject
import java.io.File

class ExportScanDocumentUseCase @Inject constructor() {
    suspend operator fun invoke(context: Context, doc: ScanDocument): Boolean {
        val file = File(doc.filePath)
        return ExportUtils.exportToDownloads(context, file, doc.title)
    }
}
