package com.m2stack.cleardocs.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.m2stack.cleardocs.data.local.entity.ScanDocumentEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ScanDocumentDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDocument(doc: ScanDocumentEntity)

    @Query("SELECT * FROM scanned_documents ORDER BY timestamp DESC")
    fun getAllDocuments(): Flow<List<ScanDocumentEntity>>

    @Delete
    suspend fun deleteDocument(doc: ScanDocumentEntity)

    @Query("DELETE FROM scanned_documents")
    suspend fun deleteAll()
}
