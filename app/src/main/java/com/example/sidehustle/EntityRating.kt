package com.example.sidehustle

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "rating_table",
    foreignKeys = [
        ForeignKey(
            entity = EntityApplication::class,
            parentColumns = ["employeeID", "jobID"],
            childColumns = ["employeeID", "jobID"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
data class EntityRating(
    @PrimaryKey(autoGenerate = true)
    val ratingID: Long = 0L,
    val employeeID: Long,
    val jobID: Long,
    val rating: Int,
    val comment: String,
    val commenter: String,
)
