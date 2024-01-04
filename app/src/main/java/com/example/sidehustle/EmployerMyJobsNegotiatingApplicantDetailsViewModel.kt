package com.example.sidehustle

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class EmployerMyJobsNegotiatingApplicantDetailsViewModel (private val application: Application) :
    AndroidViewModel(application) {
    private val jobRepository: EntityJobRepository
    private val employeeRepository: EntityEmployeeRepository
    private val ratingRepository : EntityRatingRepository
    private val negotiationRepository : EntityNegotiationRepository
    private val languagesRepository : EntityLanguageRepository

    init {
        val database = SideHustleDatabase.getDatabase(application)
        jobRepository = EntityJobRepository(database.jobDao())
        employeeRepository = EntityEmployeeRepository(database.employeeDao())
        ratingRepository = EntityRatingRepository(database.ratingDao())
        negotiationRepository = EntityNegotiationRepository(database.negotiationDao())
        languagesRepository = EntityLanguageRepository(database.languageDao())
    }

    suspend fun getEmployeeByEmployeeID(employeeID: Long):EntityEmployee?{
        return employeeRepository.get(employeeID)
    }
}