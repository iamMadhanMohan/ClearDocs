package com.m2stack.cleardocs.utils

import android.content.ContentResolver
import android.content.Context
import android.net.Uri
import java.io.File
import java.io.FileOutputStream
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object FileUtils {

    fun saveUriToInternalStorage(
        context: Context,
        uri: Uri,
        folderName: String = "cleardocs"
    ): File? {
        return try {
            val resolver: ContentResolver = context.contentResolver
            val inputStream = resolver.openInputStream(uri) ?: return null

            val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
            val fileName = "scan_$timestamp.jpg"

            val dir = File(context.filesDir, folderName)
            if (!dir.exists()) dir.mkdirs()

            val file = File(dir, fileName)
            val outputStream = FileOutputStream(file)

            inputStream.copyTo(outputStream)
            inputStream.close()
            outputStream.close()

            file
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}
