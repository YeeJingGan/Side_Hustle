package com.example.sidehustle

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class EmployeeMyJobsHistoryViewModel (private val application: Application) :
    AndroidViewModel(application) {
    private val jobRepository: EntityJobRepository
    val historyJobs: LiveData<List<EntityJob>>

    init {
        val database = SideHustleDatabase.getDatabase(application)
        jobRepository = EntityJobRepository(database.jobDao())
        historyJobs = jobRepository.allData

        // TODO : REPLACE WITH REALREAL ONCE HAVE APPLICATION
    }
}