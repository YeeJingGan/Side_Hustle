package com.example.sidehustle

import android.app.Application
import androidx.lifecycle.AndroidViewModel

class EmployerMyJobsNegotiatingViewModel(private val application: Application) :
    AndroidViewModel(application) {
    private val jobRepository: EntityJobRepository
    private val applicationRepository : EntityApplicationRepository
    private val employeeRepository: EntityEmployeeRepository
    private val ratingRepository : EntityRatingRepository
    private val negotiationRepository : EntityNegotiationRepository

    init {
        val database = SideHustleDatabase.getDatabase(application)
        jobRepository = EntityJobRepository(database.jobDao())
        employeeRepository = EntityEmployeeRepository(database.employeeDao())
        applicationRepository = EntityApplicationRepository(database.applicationDao())
        ratingRepository = EntityRatingRepository(database.ratingDao())
        negotiationRepository = EntityNegotiationRepository(database.negotiationDao())

        // TODO : REPLACE WITH REALREAL ONCE HAVE APPLICATION
    }

    suspend fun getByEmployerIDApprovedJobsWithApplications(employerID: Long): List<EntityJob> {
        return jobRepository.getByEmployerIDApprovedJobsWithApplications(employerID)
    }

    suspend fun getApplicantCount(jobID: Long):Int {
        return applicationRepository.getApplicantCountByJobID(jobID)
    }

    suspend fun getNegotiatingEmployees(jobID: Long):List<EntityEmployee>{
        return employeeRepository.getByJobIDNegotiatingEmployees(jobID)
    }

    suspend fun getAverageRatingByEmployeeIDAndCommenter(employerID: Long,commenter:String):Double{
        return ratingRepository.getAverageRatingByEmployeeIDAndCommenter(employerID,commenter)
    }

    suspend fun getLastestNegotiationByEmployeeIDAndJobID(employeeID:Long,jobID:Long):EntityNegotiation{
        return negotiationRepository.getLatestNegotiationByEmployeeIDAndJobID(employeeID,jobID)
    }

}