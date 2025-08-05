package com.m2stack.cleardocs.utils

import android.content.ContentValues
import android.content.Context
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import java.io.File

object ExportUtils {

    fun exportToDownloads(context: Context, file: File, displayName: String): Boolean {
        return try {
            val resolver = context.contentResolver
            val contentValues = ContentValues().apply {
                put(MediaStore.MediaColumns.DISPLAY_NAME, "$displayName.jpg")
                put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
            }

            val externalUri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                contentValues.put(
                    MediaStore.MediaColumns.RELATIVE_PATH,
                    Environment.DIRECTORY_DOWNLOADS + "/ClearDocs"
                )
                MediaStore.Downloads.EXTERNAL_CONTENT_URI
            } else {
                // Fallback: store in Pictures on older Android
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            }

            val uri = resolver.insert(externalUri, contentValues) ?: return false

            resolver.openOutputStream(uri)?.use { outputStream ->
                file.inputStream().use { inputStream ->
                    inputStream.copyTo(outputStream)
                }
            }

            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }


}
