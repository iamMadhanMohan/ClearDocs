package com.m2stack.cleardocs.scanner

import android.app.Activity
import android.util.Log
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.IntentSenderRequest
import com.google.mlkit.vision.documentscanner.GmsDocumentScannerOptions
import com.google.mlkit.vision.documentscanner.GmsDocumentScanning

object ScannerLauncher {
    fun launchScanner(
        activity: Activity,
        scannerLauncher: ActivityResultLauncher<IntentSenderRequest>
    ) {
        val options = GmsDocumentScannerOptions.Builder()
            .setGalleryImportAllowed(false)
            .setPageLimit(1)
            .setResultFormats(GmsDocumentScannerOptions.RESULT_FORMAT_JPEG)
            .build()

        val scanner = GmsDocumentScanning.getClient(options)

        scanner.getStartScanIntent(activity)
            .addOnSuccessListener { intentSender ->
                val request = IntentSenderRequest.Builder(intentSender).build()
                scannerLauncher.launch(request)
            }
            .addOnFailureListener { e ->
                Log.e("Scanner", "Failed to launch scanner", e)
            }
    }
}