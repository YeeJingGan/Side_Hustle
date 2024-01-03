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
    val address: String,
    val postcode: String,
    val wages: Int,
    val startDate: String,
    val endDate: String,
    val startTime: String,
    val endTime: String,
    val description: String,
    val status: String
)
