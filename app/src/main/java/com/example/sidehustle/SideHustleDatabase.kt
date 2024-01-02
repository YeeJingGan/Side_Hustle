package com.example.sidehustle

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [
        EntityApplication::class,
        EntityEmployee::class,
        EntityEmployer::class,
        EntityFavorite::class,
        EntityJob::class,
        EntityLanguage::class,
        EntityNegotiation::class,
        EntityRating::class,
        EntityRequirement::class,
        EntityResume::class
    ],
    exportSchema = false,
    version = 1
)
abstract class SideHustleDatabase : RoomDatabase() {
    abstract fun applicationDao(): EntityApplicationDao
    abstract fun employeeDao(): EntityEmployeeDao
    abstract fun employerDao(): EntityEmployerDao
    abstract fun favoriteDao(): EntityFavoriteDao
    abstract fun jobDao(): EntityJobDao
    abstract fun languageDao(): EntityLanguageDao
    abstract fun negotiationDao(): EntityNegotiationDao
    abstract fun ratingDao(): EntityRatingDao
    abstract fun requirementDao(): EntityRequirementDao
    abstract fun resumeDao(): EntityResumeDao

    companion object {
        @Volatile
        private var INSTANCE: SideHustleDatabase? = null

        fun getDatabase(context: Context): SideHustleDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SideHustleDatabase::class.java,
                    "side_hustle_schema"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}