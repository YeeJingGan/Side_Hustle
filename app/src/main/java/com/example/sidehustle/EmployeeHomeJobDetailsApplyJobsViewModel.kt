package com.example.sidehustle

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EmployeeHomeJobDetailsApplyJobsViewModel(private val application: Application): AndroidViewModel(application) {
    private val jobRepository: EntityJobRepository
    private val applicationRepository : EntityApplicationRepository
    private val employeeRepository : EntityEmployeeRepository
    private val negotiationRepository : EntityNegotiationRepository

    val allData: LiveData<List<EntityJob>>

    private val _selectedJob = MutableLiveData<EntityJob>()
    private val _selectedEmployee = MutableLiveData<EntityEmployee>()

    val selectedJob: LiveData<EntityJob> get() = _selectedJob

    init{
        val database = SideHustleDatabase.getDatabase(application)
        jobRepository = EntityJobRepository(database.jobDao())
        allData = jobRepository.getJobsStartingTodayOrLater()
        applicationRepository = EntityApplicationRepository(database.applicationDao())
        employeeRepository = EntityEmployeeRepository(database.employeeDao())
        negotiationRepository = EntityNegotiationRepository(database.negotiationDao())
    }

    fun get(jobID:Long){
        viewModelScope.launch(Dispatchers.IO){
            _selectedJob.postValue(jobRepository.get(jobID))
        }
    }

    suspend fun getEmployee(employeeID:Long):EntityEmployee?{
        return employeeRepository.get(employeeID)
    }

    fun insertApplication(application: EntityApplication) {
        viewModelScope.launch(Dispatchers.IO) {
            applicationRepository.addApplication(application)
        }
    }

    fun insertNegotiation(negotiation: EntityNegotiation) {
        viewModelScope.launch(Dispatchers.IO) {
            negotiationRepository.insert(negotiation)
        }
    }

}