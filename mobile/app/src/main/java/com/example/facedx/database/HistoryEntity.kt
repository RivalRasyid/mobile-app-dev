package com.example.facedx.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "history")
data class HistoryEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val imageUri: String,
    val timestamp: Long,
    val skinTitle: String,
    val skinDesc: String
)
