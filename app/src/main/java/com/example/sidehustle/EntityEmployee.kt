package com.example.sidehustle

data class EntityEmployee(
    val employeeID: Long,

    var employeeUsername: String,

    val employeeEmail: String,

    var employeePassword: String,

    var employeeProfilePicture : ByteArray
)

// Sample to populate the entity

//EmployeeEntity(1, "Gan Yee Jing", "gyjemployee@email.com","abc123",byteArrayOf(0x48, 101, 108, 108, 111))
//EmployeeEntity(2, "Yeap Jie Shen", "yjsemployee@email.com","abc123",byteArrayOf(0x48, 101, 108, 108, 111))
//EmployeeEntity(3, "Jerome Subash", "jeromeemployee@email.com","abc123",byteArrayOf(0x48, 101, 108, 108, 111))
