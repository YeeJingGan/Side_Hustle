package com.example.sidehustle

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "requirement_table",
    foreignKeys = [
        ForeignKey(
            entity = EntityJob::class,
            parentColumns = ["jobID"],
            childColumns = ["jobID"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE,
        )
    ]
)
data class EntityRequirement(
    @PrimaryKey(autoGenerate = true)
    val requirementID: Long = 0L,
    val jobID: Long,
    val content: String,
)