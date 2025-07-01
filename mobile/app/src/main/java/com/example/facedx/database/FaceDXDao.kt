package com.example.facedx.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface AboutDao {
    @Query("SELECT * FROM about LIMIT 1")
    suspend fun getAbout(): List<AboutEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(aboutList: List<AboutEntity>)
}

@Dao
interface FaqDao {
    @Query("SELECT * FROM faq")
    suspend fun getAllFaq(): List<FaqEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(faqList: List<FaqEntity>)
}

@Dao
interface SaranDao {
    @Query("SELECT * FROM saran")
    suspend fun getAllSaran(): List<SaranEntity>

    @Query("SELECT * FROM saran WHERE jenis_kulit = :jenisKulit LIMIT 1")
    suspend fun getByJenisKulit(jenisKulit: String): SaranEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(saranList: List<SaranEntity>)
}
