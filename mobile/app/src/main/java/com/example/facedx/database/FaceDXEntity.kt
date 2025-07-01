package com.example.facedx.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "about")
data class AboutEntity(
    @PrimaryKey val id: Int,
    val content: String
)

@Entity(tableName = "faq")
data class FaqEntity(
    @PrimaryKey val id: Int,
    val question: String,
    val answer: String
)

@Entity(tableName = "saran")
data class SaranEntity(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "jenis_kulit")  val jenisKulit: String,
    val deskripsi: String,
    val ringkasan: String,
    val perawatan: String
)

