package com.example.sidehustle

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class EmployeeMyProfileViewModel(private val application: Application) :
    AndroidViewModel(application)  {
    private val employeeRepository: EntityEmployeeRepository
    private val employerRepository: EntityEmployerRepository
    private val ratingRepository: EntityRatingRepository

    private val _starCount = MutableLiveData<Int>()
    val starCount: LiveData<Int> get() = _starCount

    init {
        val database = SideHustleDatabase.getDatabase(application)
        employeeRepository = EntityEmployeeRepository(database.employeeDao())
        employerRepository = EntityEmployerRepository(database.employerDao())
        ratingRepository = EntityRatingRepository(database.ratingDao())
    }


    suspend fun getRatingsForEmployer(employerID: Long,commenter: String): List<EntityRating>{
        return ratingRepository.getRatingsForEmployer(employerID, commenter)
    }

    suspend fun getEmployeeByEmployeeID(employeeID: Long):EntityEmployee?{
        return employeeRepository.get(employeeID)
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

    suspend fun getEmployerByEmployerID(employerID: Long):EntityEmployer{
        return employerRepository.getEmployerByJobID(employerID)
    }
}