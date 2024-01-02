package com.example.sidehustle

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "application_table",
    primaryKeys = ["employeeID", "jobID"],
    foreignKeys = [
        ForeignKey(
            entity = EntityJob::class,
            parentColumns = ["jobID"],
            childColumns = ["jobID"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = EntityEmployee::class,
            parentColumns = ["employeeID"],
            childColumns = ["employeeID"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
data class EntityApplication(
    val employeeID: Long = 0L,
    val jobID: Long,
    var status: String
)


// Sample to populate the entity

//ApplicationEntity(1,1,1,"Negotiating")
//ApplicationEntity(2,2,2,"Rejected")
//ApplicationEntity(3,3,3,"Accepted")
//ApplicationEntity(3,3,3,"Ongoing")
//ApplicationEntity(3,3,3,"Ended")
