package com.example.sidehustle

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class EmployerMyJobsNegotiatingViewModel(private val application: Application) :
    AndroidViewModel(application) {
    private val jobRepository: EntityJobRepository
    val negotiatingJobs: LiveData<List<EntityJob>>
    private val employeeRepository: EntityEmployeeRepository
    val negotiatingEmployees: LiveData<List<EntityEmployee>>

    init {
        val database = SideHustleDatabase.getDatabase(application)
        jobRepository = EntityJobRepository(database.jobDao())
        employeeRepository = EntityEmployeeRepository(database.employeeDao())
        negotiatingJobs = jobRepository.getJobsStartingTodayOrLater()
        negotiatingEmployees = employeeRepository.allData

        // TODO : REPLACE WITH REALREAL ONCE HAVE APPLICATION
    }
}