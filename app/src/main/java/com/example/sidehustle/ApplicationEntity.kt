package com.example.sidehustle

data class ApplicationEntity(
    val employeeID : Long,

    val jobID : Long,

    val applicationID : Long,

    var applicationStatus : String
)

// Sample to populate the entity

//ApplicationEntity(1,1,1,"Negotiating")
//ApplicationEntity(2,2,2,"Rejected")
//ApplicationEntity(3,3,3,"Accepted")
//ApplicationEntity(3,3,3,"Ongoing")
//ApplicationEntity(3,3,3,"Ended")
