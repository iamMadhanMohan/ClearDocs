package com.m2stack.cleardocs.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.m2stack.cleardocs.data.local.dao.ScanDocumentDao
import com.m2stack.cleardocs.data.local.entity.ScanDocumentEntity

@Database(entities = [ScanDocumentEntity::class], version = 1)
abstract class ClearDocsDatabase : RoomDatabase() {
    abstract fun scanDocumentDao(): ScanDocumentDao
}
