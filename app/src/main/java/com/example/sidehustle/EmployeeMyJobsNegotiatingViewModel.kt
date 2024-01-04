package com.example.sidehustle

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData

class EmployeeMyJobsNegotiatingViewModel(private val application: Application) :
    AndroidViewModel(application) {
    private val negotiationRepository: EntityNegotiationRepository
    private val jobRepository: EntityJobRepository

    val latestNegotiationsWithJobs: LiveData<List<EntityJob>>


    init {
        val database = SideHustleDatabase.getDatabase(application)
        negotiationRepository = EntityNegotiationRepository(database.negotiationDao())
        jobRepository = EntityJobRepository(database.jobDao())
        latestNegotiationsWithJobs = getLatestNegotiationsWithJobsByEmployeeID()

    }

    private fun getLatestNegotiationsWithJobsByEmployeeID(): LiveData<List<EntityJob>> {
        return liveData {
            //TODO: Update employee ID
            val latestNegotiations = negotiationRepository.getLatestNegotiationsByEmployeeID(2)
            val jobIDs = latestNegotiations.map { it.jobID }
            val jobs = jobRepository.getJobsByIds(jobIDs)
            emit(jobs)
        }}
}