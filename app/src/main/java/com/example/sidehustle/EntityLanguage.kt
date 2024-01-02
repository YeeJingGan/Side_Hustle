package com.example.sidehustle

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "language_table",
    foreignKeys = [
        ForeignKey(
            entity = EntityApplication::class,
            parentColumns = ["employeeID", "jobID"],
            childColumns = ["employeeID", "jobID"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        ),
    ]
)
data class EntityLanguage(
    @PrimaryKey(autoGenerate = true)
    val languageID: Long = 0L,
    val employeeID: Long,
    val jobID: Long,
    val language: String,
    val proficiency: String
)

// Sample to populate the entity

//LanguageEntity(1,"English","Moderate")
//LanguageEntity(2,"Mandarin","Good")
//LanguageEntity(3,"Bahasa Melayu","Excellent")
