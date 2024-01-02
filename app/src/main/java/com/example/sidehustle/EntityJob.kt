package com.example.sidehustle

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "job_table", foreignKeys = [
        ForeignKey(
            entity = EntityEmployer::class,
            parentColumns = ["employerID"],
            childColumns = ["employerID"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
data class EntityJob(
    @PrimaryKey(autoGenerate = true)
    val jobID: Long = 0L,
    val employerID: Long,
    val title: String,
    val state: String,
    val wages: Int,
    val startDate: String,
    val endDate: String,
    val startTime: String,
    val endTime: String,
    val description: String
)

// Sample to populate the entity

//EntityJob(1,1,"Job1","JobState1",70,"2024-01-01","2024-02-02","10:00:00Z","16:00:00Z","jobDescription1")
//EntityJob(2,2,"Job2","JobState2",80,"2024-01-01","2024-02-02","10:00:00Z","16:00:00Z","jobDescription2")
//EntityJob(3,3,"Job3","JobState3",90,"2024-01-01","2024-02-02","10:00:00Z","16:00:00Z","jobDescription1")
