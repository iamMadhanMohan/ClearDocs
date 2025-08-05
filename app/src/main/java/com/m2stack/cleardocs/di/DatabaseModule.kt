package com.m2stack.cleardocs.di

import android.content.Context
import androidx.room.Room
import com.m2stack.cleardocs.data.local.ClearDocsDatabase
import com.m2stack.cleardocs.data.local.dao.ScanDocumentDao
import com.m2stack.cleardocs.data.repository.ScanDocumentRepositoryImpl
import com.m2stack.cleardocs.domain.repository.ScanDocumentRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): ClearDocsDatabase {
        return Room.databaseBuilder(
            context,
            ClearDocsDatabase::class.java,
            "cleardocs_db"
        ).build()
    }

    @Provides
    fun provideDao(db: ClearDocsDatabase): ScanDocumentDao = db.scanDocumentDao()

    @Provides
    @Singleton
    fun provideRepository(dao: ScanDocumentDao): ScanDocumentRepository {
        return ScanDocumentRepositoryImpl(dao)
    }
}
