package com.example.sidehustle

class EntityNegotiationRepository (private val negotiationDao: EntityNegotiationDao) {
    suspend fun getLatestNegotiationByEmployeeIDAndJobID(employeeID:Long, jobID:Long):EntityNegotiation{
        return negotiationDao.getLatestNegotiationByEmployeeIDAndJobID(employeeID,jobID)
    }
}