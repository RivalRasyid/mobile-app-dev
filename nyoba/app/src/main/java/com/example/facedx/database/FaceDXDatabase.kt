package com.example.facedx.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [SaranEntity::class, FaqEntity::class, AboutEntity::class],
    version = 3,
    exportSchema = false
)
abstract class FaceDXDatabase : RoomDatabase() {
    abstract fun saranDao(): SaranDao
    abstract fun faqDao(): FaqDao
    abstract fun aboutDao(): AboutDao

    companion object {
        @Volatile private var INSTANCE: FaceDXDatabase? = null

        fun getDatabase(ctx: Context): FaceDXDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE
                    ?: Room.databaseBuilder(
                        ctx.applicationContext,
                        FaceDXDatabase::class.java,
                        "facedx.db"
                    )
                        .createFromAsset("facedx.db")
                        .fallbackToDestructiveMigration()
                        .build()
                        .also { INSTANCE = it }
            }
    }
}
