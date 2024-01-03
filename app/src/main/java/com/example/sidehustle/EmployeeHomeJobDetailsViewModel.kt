package com.example.sidehustle

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EmployeeHomeJobDetailsViewModel(private val application: Application): AndroidViewModel(application) {

    private val jobRepository: EntityJobRepository
    private val employerRepository: EntityEmployerRepository
    private val ratingRepository: EntityRatingRepository


    val allData: LiveData<List<EntityJob>>


    private val _selectedJob = MutableLiveData<EntityJob>()
    private val _starCount = MutableLiveData<Int>()

    val selectedJob: LiveData<EntityJob> get() = _selectedJob
    val starCount: LiveData<Int> get() = _starCount


    init{
        val database = SideHustleDatabase.getDatabase(application)
        jobRepository = EntityJobRepository(database.jobDao())
        allData = jobRepository.getJobsStartingTodayOrLater()
        employerRepository = EntityEmployerRepository(database.employerDao())
        ratingRepository = EntityRatingRepository(database.ratingDao())

    }

    fun updateJob(job: EntityJob) {
        viewModelScope.launch(Dispatchers.IO) {
            jobRepository.update(job)
        }
    }

    fun get(jobID:Long){
        viewModelScope.launch(Dispatchers.IO){
            _selectedJob.postValue(jobRepository.get(jobID))
        }
    }

    suspend fun getEmployerByJobID(jobID: Long): EntityEmployer {
        return employerRepository.getEmployerByJobID(jobID)
    }


    fun getAverageRatingByJobIDAndCommenter(jobID: Long, commenter: String){
        viewModelScope.launch {
            _starCount.postValue(ratingRepository.getAverageRatingByJobIDAndCommenter(jobID, commenter).toInt())
        }
    }
}