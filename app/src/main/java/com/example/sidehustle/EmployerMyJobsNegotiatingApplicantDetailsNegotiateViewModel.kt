package com.example.sidehustle

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class EmployerMyJobsNegotiatingApplicantDetailsNegotiateViewModel (private val application: Application) :
    AndroidViewModel(application) {
    private val jobRepository: EntityJobRepository
    private val negotiationRepository: EntityNegotiationRepository

    init {
        val database = SideHustleDatabase.getDatabase(application)
        jobRepository = EntityJobRepository(database.jobDao())
        negotiationRepository = EntityNegotiationRepository(database.negotiationDao())
    }

    suspend fun getByJobID(jobID: Long): EntityJob {
        return jobRepository.getByJobID(jobID)
    }

    suspend fun insert(negotiation: EntityNegotiation) {
        negotiationRepository.insert(negotiation)
    }
}