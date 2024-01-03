package com.example.sidehustle

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class EmployeeMyJobsOngoingViewModel(private val application: Application) :
    AndroidViewModel(application) {
    private val jobRepository: EntityJobRepository
    val ongoingJobs: LiveData<List<EntityJob>>

    init {
        val database = SideHustleDatabase.getDatabase(application)
        jobRepository = EntityJobRepository(database.jobDao())
        ongoingJobs = jobRepository.allData

        // TODO : REPLACE WITH REALREAL ONCE HAVE APPLICATION
    }
}