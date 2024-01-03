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
