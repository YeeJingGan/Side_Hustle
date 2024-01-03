package com.example.sidehustle

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class EmployerMyJobsHistoryViewModel (private val application: Application) :
    AndroidViewModel(application) {
    private val jobRepository: EntityJobRepository
    val historyJobs: LiveData<List<EntityJob>>
    private val employeeRepository: EntityEmployeeRepository
    val historyEmployees: LiveData<List<EntityEmployee>>

    init {
        val database = SideHustleDatabase.getDatabase(application)
        jobRepository = EntityJobRepository(database.jobDao())
        employeeRepository = EntityEmployeeRepository(database.employeeDao())
        historyJobs = jobRepository.getJobsStartingTodayOrLater()
        historyEmployees = employeeRepository.allData

        // TODO : REPLACE WITH REALREAL ONCE HAVE APPLICATION
    }
}