package com.example.sidehustle

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlin.math.floor

class EmployerHomeJobDetailsViewModel(private val application: Application) :
    AndroidViewModel(application) {
    private val employerRepository: EntityEmployerRepository
    private val jobRepository: EntityJobRepository
    private val ratingRepository: EntityRatingRepository
    private val requirementRepository: EntityRequirementRepository

    private val _starCount = MutableLiveData<Int>()
    val starCount: LiveData<Int> get() = _starCount

    private val _requirements = MutableLiveData<List<String>>()
    val requirements: LiveData<List<String>> get() = _requirements

    init {
        val database = SideHustleDatabase.getDatabase(application)
        employerRepository = EntityEmployerRepository(database.employerDao())
        jobRepository = EntityJobRepository(database.jobDao())
        ratingRepository = EntityRatingRepository(database.ratingDao())
        requirementRepository = EntityRequirementRepository(database.requirementDao())
    }

    suspend fun getJobByJobID(jobID: Long): EntityJob {
        return jobRepository.getByJobID(jobID)
    }

    suspend fun getEmployerByJobID(jobID: Long): EntityEmployer {
        return employerRepository.getEmployerByJobID(jobID)
    }


    fun getAverageRatingByJobIDAndCommenter(jobID: Long, commenter: String) {
        viewModelScope.launch {
            _starCount.postValue(
                ratingRepository.getAverageRatingByJobIDAndCommenter(
                    jobID,
                    commenter
                ).toInt()
            )
        }
    }

    suspend fun getRequirementsByJobID(jobID: Long): List<String> {
        return requirementRepository.getByJobID(jobID)
    }
}