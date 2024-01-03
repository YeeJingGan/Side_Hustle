package com.example.sidehustle

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class EmployeeMyJobsNegotiatingViewModel(private val application: Application) :
    AndroidViewModel(application) {
    private val jobRepository: EntityJobRepository
    val negotiatingJobs: LiveData<List<EntityJob>>

    init {
        val database = SideHustleDatabase.getDatabase(application)
        jobRepository = EntityJobRepository(database.jobDao())
        negotiatingJobs = jobRepository.allData

        // TODO : REPLACE WITH REALREAL ONCE HAVE APPLICATION
    }
}