package com.m2stack.cleardocs.domain.usecase

import android.content.Context
import com.m2stack.cleardocs.domain.model.ScanDocument
import com.m2stack.cleardocs.utils.ExportUtils
import jakarta.inject.Inject
import java.io.File

class ExportScanDocumentsUseCase @Inject constructor() {
    operator fun invoke(context: Context, documents: List<ScanDocument>): Int {
        return documents.count {
            ExportUtils.exportToDownloads(context, File(it.filePath), it.title)
        }
    }
}
