package com.m2stack.cleardocs.domain.model

data class ScanDocument(
    val id: Int = 0,
    val title: String,
    val filePath: String,
    val timestamp: Long
)
