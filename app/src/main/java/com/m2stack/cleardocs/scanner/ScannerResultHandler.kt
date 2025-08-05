package com.m2stack.cleardocs.scanner

import android.app.Activity
import android.content.Context
import android.widget.Toast
import androidx.activity.result.ActivityResult
import com.google.mlkit.vision.documentscanner.GmsDocumentScanningResult
import com.m2stack.cleardocs.presentation.home.ScanDocumentViewModel
import com.m2stack.cleardocs.utils.FileUtils
import java.io.File

object ScannerResultHandler {
    fun handleScannerResult(
        context: Context,
        result: ActivityResult,
        viewModel: ScanDocumentViewModel
    ) {
        if (result.resultCode == Activity.RESULT_OK) {
            val scannedResult = GmsDocumentScanningResult.fromActivityResultIntent(result.data)
            val pages = scannedResult?.pages

            if (!pages.isNullOrEmpty()) {
                val uri = pages[0].imageUri
                val savedFile: File? = FileUtils.saveUriToInternalStorage(context, uri)

                if (savedFile != null) {
                    val filePath = savedFile.absolutePath
                    val title = savedFile.nameWithoutExtension

                    viewModel.insertScanDocument(
                        filePath = filePath,
                        title = title
                    )
                    Toast.makeText(context, "Saved: $title", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Failed to save image", Toast.LENGTH_SHORT).show()
                }
            }
        } else {
            Toast.makeText(context, "Scan cancelled or failed", Toast.LENGTH_SHORT).show()
        }
    }
}