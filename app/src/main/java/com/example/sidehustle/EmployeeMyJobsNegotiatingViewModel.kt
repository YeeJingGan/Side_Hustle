package com.example.sidehustle

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData

class EmployeeMyJobsNegotiatingViewModel(private val application: Application) :
    AndroidViewModel(application) {
    private val negotiationRepository: EntityNegotiationRepository

    init {
        val database = SideHustleDatabase.getDatabase(application)
        negotiationRepository = EntityNegotiationRepository(database.negotiationDao())

    }

    suspend fun getLatestNegotiationByEmployeeIDAndJobID(
        employeeID: Long,
        jobID: Long
    ): EntityNegotiation {
        return negotiationRepository.getLatestNegotiationByEmployeeIDAndJobID(employeeID, jobID)
    }
}