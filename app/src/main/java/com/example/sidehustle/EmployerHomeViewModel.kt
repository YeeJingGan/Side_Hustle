package com.example.sidehustle

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class EmployerHomeViewModel(private val application: Application) : AndroidViewModel(application) {

    private val jobRepository: EntityJobRepository
    private val _query = MutableLiveData<String>()
    val searchQuery: LiveData<String> get() = _query

    val approvedJobs :LiveData<List<EntityJob>>
    val pendingJobs : LiveData<List<EntityJob>>

    init{
        val database = SideHustleDatabase.getDatabase(application)
        jobRepository = EntityJobRepository(database.jobDao())
        approvedJobs = jobRepository.getByEmployerIDAndStatus(1,"APPROVED")
        pendingJobs = jobRepository.getByEmployerIDAndStatus(1,"PENDING")
    }

    fun setSearchQuery(query:String){
        _query.value = query
    }
}