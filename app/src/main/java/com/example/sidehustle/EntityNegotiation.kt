package com.example.sidehustle

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "negotiation_table",
    foreignKeys = [
        ForeignKey(
            entity = EntityApplication::class,
            parentColumns = ["employeeID", "jobID"],
            childColumns = ["employeeID", "jobID"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        ),
    ])
data class EntityNegotiation(
    @PrimaryKey(autoGenerate = true)
    val negotiationID: Long,
    val employeeID : Long,
    val jobID : Long,
    val pay: Int,
    val comment: String,
    val negotiator: String,
)
