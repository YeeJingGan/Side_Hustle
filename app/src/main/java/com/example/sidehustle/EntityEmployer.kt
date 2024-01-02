package com.example.sidehustle

data class EntityEmployer(
    val employerID : Long,

    var employerUsername : String,

    val employerEmail : String,

    var employerPassword : String,

    var employerProfilePicture : ByteArray
)

// Sample to populate the entity

//EmployerEntity(1, "Gan Yee Jing", "gyjemployer@email.com","abc123",byteArrayOf(0x48, 101, 108, 108, 111))
//EmployerEntity(2, "Yeap Jie Shen", "yjsemployer@email.com","abc123",byteArrayOf(0x48, 101, 108, 108, 111))
//EmployerEntity(3, "Jerome Subash", "jeromeemployer@email.com","abc123",byteArrayOf(0x48, 101, 108, 108, 111))