package com.example.sidehustle

import androidx.lifecycle.LiveData


class EntityNegotiationRepository(private val negotiationDao: EntityNegotiationDao) {

    val allData: LiveData<List<EntityNegotiation>> = negotiationDao.getAll()

    suspend fun insert(negotiation: EntityNegotiation) {
        negotiationDao.insert(negotiation)
    }

    suspend fun update(negotiation: EntityNegotiation) {
        negotiationDao.update(negotiation)
    }

    suspend fun getByID(negotiationID: Long): EntityNegotiation {
        return negotiationDao.get(negotiationID)
    }

    suspend fun getLatestNegotiationByEmployeeIDAndJobID(
        employeeID: Long,
        jobID: Long
    ): EntityNegotiation {
        return negotiationDao.getLatestNegotiationByEmployeeIDAndJobID(employeeID, jobID)
    }

    suspend fun getLatestNegotiationsByEmployeeID(employeeID: Long): List<EntityNegotiation> {
        return negotiationDao.getLatestNegotiationsByEmployeeID(employeeID)
    }
}

