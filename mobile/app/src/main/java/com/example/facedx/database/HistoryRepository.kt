package com.example.facedx.database

class HistoryRepository(private val dao: HistoryDao) {

    val allHistory = dao.getAllHistory()

    suspend fun addHistory(
        imageUri: String,
        skinTitle: String,
        skinDesc: String,
        timestamp: Long = System.currentTimeMillis()
    ) {
        dao.insert(
            HistoryEntity(
                imageUri = imageUri,
                timestamp = timestamp,
                skinTitle = skinTitle,
                skinDesc = skinDesc
            )
        )
    }

    suspend fun clear() = dao.clearAll()
}
