package com.example.sidehustle

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "resume_table", foreignKeys = [
        ForeignKey(
            entity = EntityEmployee::class,
            parentColumns = ["employeeID"],
            childColumns = ["employeeID"],
            onDelete = ForeignKey.CASCADE,
            onUpdate = ForeignKey.CASCADE
        )
    ]
)
data class EntityResume(
    @PrimaryKey(autoGenerate = true)
    val resumeID: Long = 0L,
    val employeeID: Long,
    val fileName: String,
    val fileContent: ByteArray?
)

// Sample to populate the entity

//ResumeEntity(1,1,"yeejingResume",byteArrayOf(0x48, 101, 108, 108, 111))
//ResumeEntity(2,2,"jieshenResume",byteArrayOf(0x48, 101, 108, 108, 111))
//ResumeEntity(3,3,"jeromeResume",byteArrayOf(0x48, 101, 108, 108, 111))
