package com.example.sidehustle

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel

class EmployerMyJobsNegotiatingApplicantDetailsViewModel(private val application: Application) :
    AndroidViewModel(application) {
    private val jobRepository: EntityJobRepository
    private val employeeRepository: EntityEmployeeRepository
    private val ratingRepository: EntityRatingRepository
    private val negotiationRepository: EntityNegotiationRepository
    private val languagesRepository: EntityLanguageRepository
    private val employerRepository: EntityEmployerRepository
    private val applicationRepository: EntityApplicationRepository

    init {
        val database = SideHustleDatabase.getDatabase(application)
        jobRepository = EntityJobRepository(database.jobDao())
        employeeRepository = EntityEmployeeRepository(database.employeeDao())
        ratingRepository = EntityRatingRepository(database.ratingDao())
        negotiationRepository = EntityNegotiationRepository(database.negotiationDao())
        languagesRepository = EntityLanguageRepository(database.languageDao())
        employerRepository = EntityEmployerRepository(database.employerDao())
        applicationRepository = EntityApplicationRepository(database.applicationDao())
    }

    suspend fun getEmployeeByEmployeeID(employeeID: Long): EntityEmployee? {
        return employeeRepository.get(employeeID)
    }

    suspend fun getByJobID(jobID: Long): EntityJob {
        return jobRepository.getByJobID(jobID)
    }

    suspend fun getLatestNegotiationByEmployeeIDAndJobID(
        employeeID: Long,
        jobID: Long
    ): EntityNegotiation {
        return negotiationRepository.getLatestNegotiationByEmployeeIDAndJobID(employeeID, jobID)
    }

    suspend fun getByEmployeeIDAndJobID(employeeID: Long, jobID: Long): List<EntityLanguage> {
        return languagesRepository.getByEmployeeIDAndJobID(employeeID, jobID)
    }

    suspend fun getEmployerByJobIDAndEmployeeID(jobID: Long, employeeID: Long): EntityEmployer {
        return employerRepository.getEmployerByJobIDAndEmployeeID(jobID, employeeID)
    }

    suspend fun getRatingByEmployeeIDAndCommenter(
        employeeID: Long,
        commenter: String
    ): List<EntityRating> {
        return ratingRepository.getRatingByEmployeeIDAndCommenter(employeeID, commenter)
    }

    suspend fun updateStatus(employeeID: Long, jobID: Long, status: String) {
        applicationRepository.updateStatus(employeeID, jobID, status)
    }

    suspend fun getApplicationByEmployeeIDAndJobID(employeeID: Long,jobID: Long):EntityApplication{
        return applicationRepository.getApplicationByEmployeeIDAndJobID(employeeID,jobID)
    }
}