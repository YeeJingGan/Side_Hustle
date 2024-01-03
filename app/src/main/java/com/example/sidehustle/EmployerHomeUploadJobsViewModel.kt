package com.example.sidehustle

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class EmployerHomeUploadJobsViewModel (application: Application): AndroidViewModel(application){

    val allData: LiveData<List<EntityJob>>
    val repository : EntityJobRepository

    init{
        val database = SideHustleDatabase.getDatabase(application)
        val jobDao = database.jobDao()
        repository = EntityJobRepository(jobDao)
        allData = repository.allData
    }

    fun addJob(job: EntityJob){
        viewModelScope.launch {
            repository.insert(job)
        }
    }

}