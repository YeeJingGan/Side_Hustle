package com.example.sidehustle

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "employer_table")
data class EntityEmployer(
    @PrimaryKey(autoGenerate = true)
    val employerID: Long = 0L,
    var employerUsername: String,
    val employerEmail: String,
    var employerPassword: String,
    var employerProfilePicture: ByteArray?
)

// Sample to populate the entity

//EmployerEntity(1, "Gan Yee Jing", "gyjemployer@email.com","abc123",byteArrayOf(0x48, 101, 108, 108, 111))
//EmployerEntity(2, "Yeap Jie Shen", "yjsemployer@email.com","abc123",byteArrayOf(0x48, 101, 108, 108, 111))
//EmployerEntity(3, "Jerome Subash", "jeromeemployer@email.com","abc123",byteArrayOf(0x48, 101, 108, 108, 111))