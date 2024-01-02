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

// Sample to populate the entity

//RequirementEntity(1,"requirement1")
//RequirementEntity(2,"requirement2")
//RequirementEntity(3,"requirement3")
