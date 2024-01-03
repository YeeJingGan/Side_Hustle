package com.example.sidehustle

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class EmployerHomeViewModel(private val application: Application) : AndroidViewModel(application) {

    private val jobRepository: EntityJobRepository
    private val applicationRepository: EntityApplicationRepository
    private val _query = MutableLiveData<String>()
    val searchQuery: LiveData<String> get() = _query

    val approvedJobs: LiveData<List<EntityJob>>
    val pendingJobs: LiveData<List<EntityJob>>

    private val _applicantCount = MutableLiveData<Int>()
    val applicantCount: LiveData<Int> get() = _applicantCount

    init {
        val database = SideHustleDatabase.getDatabase(application)
        jobRepository = EntityJobRepository(database.jobDao())
        applicationRepository = EntityApplicationRepository(database.applicationDao())
        approvedJobs = jobRepository.getByEmployerIDAndStatus(1, "APPROVED")
        pendingJobs = jobRepository.getByEmployerIDAndStatus(1, "PENDING")
    }

    fun setSearchQuery(query: String) {
        _query.value = query
    }

//    fun getApplicantCount(jobID: Long){
//        viewModelScope.launch {
//            _applicantCount.postValue(applicationRepository.getApplicantCountByJobID(jobID))
//        }
//    }

    suspend fun getApplicantCount(jobID: Long):Int {
        return applicationRepository.getApplicantCountByJobID(jobID)
    }
}