package com.example.sidehustle

data class EntityNegotiation(
    val applicationID : Long,

    val pay : Int,

    val comment : String,

    val negotiatorRole : String
)

// Sample to populate the entity

//NegotiationEntity(1,70,"Comment1","EMPLOYEE")
//NegotiationEntity(2,80,"Comment2","EMPLOYEE")
//NegotiationEntity(3,90,"Comment3","EMPLOYER")
