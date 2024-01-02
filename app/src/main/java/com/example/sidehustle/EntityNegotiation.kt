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

// Sample to populate the entity

//NegotiationEntity(1,70,"Comment1","EMPLOYEE")
//NegotiationEntity(2,80,"Comment2","EMPLOYEE")
//NegotiationEntity(3,90,"Comment3","EMPLOYER")
