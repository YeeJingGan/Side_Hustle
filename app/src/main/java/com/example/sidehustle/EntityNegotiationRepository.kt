package com.example.sidehustle

class EntityNegotiationRepository (private val negotiationDao: EntityNegotiationDao) {
    suspend fun getLastestNegotiationByEmployeeIDAndJobID(employeeID:Long,jobID:Long):EntityNegotiation{
        return negotiationDao.getLastestNegotiationByEmployeeIDAndJobID(employeeID,jobID)
    }
}