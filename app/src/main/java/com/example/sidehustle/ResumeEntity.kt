package com.example.sidehustle

data class ResumeEntity(
    val resumeID : Long,

    val employeeID : Long,

    val resumeFileName : String,

    val resumeFileContent : ByteArray
)

// Sample to populate the entity

//ResumeEntity(1,1,"yeejingResume",byteArrayOf(0x48, 101, 108, 108, 111))
//ResumeEntity(2,2,"jieshenResume",byteArrayOf(0x48, 101, 108, 108, 111))
//ResumeEntity(3,3,"jeromeResume",byteArrayOf(0x48, 101, 108, 108, 111))
