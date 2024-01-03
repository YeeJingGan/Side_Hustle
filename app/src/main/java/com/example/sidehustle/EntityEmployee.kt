package com.example.sidehustle

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "employee_table")
data class EntityEmployee(
    @PrimaryKey(autoGenerate = true)
    val employeeID: Long = 0L,
    var employeeUsername: String,
    val employeeEmail: String,
    var employeePassword: String
)

// Sample to populate the entity

//EmployeeEntity(1, "Gan Yee Jing", "gyjemployee@email.com","abc123",byteArrayOf(0x48, 101, 108, 108, 111))
//EmployeeEntity(2, "Yeap Jie Shen", "yjsemployee@email.com","abc123",byteArrayOf(0x48, 101, 108, 108, 111))
//EmployeeEntity(3, "Jerome Subash", "jeromeemployee@email.com","abc123",byteArrayOf(0x48, 101, 108, 108, 111))
