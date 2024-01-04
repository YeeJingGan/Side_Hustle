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

    fun getLatestNegotiationsByEmployeeID(employeeID: Long): LiveData<List<EntityNegotiation>> {
        return liveData {
            emit(negotiationRepository.getLatestNegotiationsByEmployeeID(employeeID))
        }
    }
}