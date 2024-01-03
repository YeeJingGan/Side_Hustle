package com.example.sidehustle

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class EmployeeHomeViewModel(private val application: Application): AndroidViewModel(application) {

    private val jobRepository: EntityJobRepository

    private val _query = MutableLiveData<String>()
    val searchQuery: LiveData<String> get() = _query

    val applicableJobs : LiveData<List<EntityJob>>


    init{
        val database = SideHustleDatabase.getDatabase(application)
        jobRepository = EntityJobRepository(database.jobDao())
        applicableJobs = jobRepository.getJobsStartingTodayOrLater()
    }
    fun setSearchQuery(query:String){
        _query.value = query
    }
}