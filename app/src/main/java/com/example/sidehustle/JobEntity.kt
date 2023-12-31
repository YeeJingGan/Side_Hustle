package com.example.sidehustle

data class JobEntity(
    val jobID : Long,

    val employerID : Long,

    val jobName : String,

    val jobState : String,

    val pay : Int,

    val startDate : String,

    val endDate : String,

    val startTime : String,

    val endTime : String,

    val description : String
)

// Sample to populate the entity

//JobEntity(1,1,"Job1","JobState1",70,"2024-01-01","2024-02-02","10:00:00Z","16:00:00Z","jobDescription1")
//JobEntity(2,2,"Job2","JobState2",80,"2024-01-01","2024-02-02","10:00:00Z","16:00:00Z","jobDescription2")
//JobEntity(3,3,"Job3","JobState3",90,"2024-01-01","2024-02-02","10:00:00Z","16:00:00Z","jobDescription1")
