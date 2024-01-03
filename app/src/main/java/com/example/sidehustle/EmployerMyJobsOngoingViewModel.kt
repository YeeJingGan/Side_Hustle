package com.example.sidehustle

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class EmployerMyJobsOngoingViewModel(private val application: Application) :
    AndroidViewModel(application) {
    private val jobRepository: EntityJobRepository
    val ongoingJobs: LiveData<List<EntityJob>>
    private val employeeRepository: EntityEmployeeRepository
    val ongoingEmployees: LiveData<List<EntityEmployee>>

    init {
        val database = SideHustleDatabase.getDatabase(application)
        jobRepository = EntityJobRepository(database.jobDao())
        employeeRepository = EntityEmployeeRepository(database.employeeDao())
        ongoingJobs = jobRepository.getByEmployerIDJobsStartingTodayOrLater(1)
        ongoingEmployees = employeeRepository.allData

        // TODO : REPLACE WITH REALREAL ONCE HAVE APPLICATION
    }
}