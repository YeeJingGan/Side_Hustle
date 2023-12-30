package com.example.sidehustle

data class RatingEntity(
    val applicationID : Long,

    val starCount : Int,

    val comment : String,

    val commenterRole : String
)

// Sample to populate the entity

//RatingEntity(1,5,"yeejingComment","EMPLOYEE")
//RatingEntity(2,4,"jieshenComment","EMPLOYEE")
//RatingEntity(3,3,"jeromeComment","EMPLOYER")
