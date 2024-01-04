package com.example.sidehustle

import android.util.Log
import androidx.lifecycle.LiveData

class EntityApplicationRepository(private val applicationDao: EntityApplicationDao) {

    val readAllData: LiveData<List<EntityApplication>> = applicationDao.readAllData()

    suspend fun addApplication(application: EntityApplication) {
        applicationDao.insert(application)
    }

    fun getApplicantsByJobID(jobID: Long): LiveData<List<EntityApplication>> {
        return applicationDao.getApplicantsByJobID(jobID)
    }

    suspend fun getApplicantCountByJobID(jobID:Long):Int{
        return applicationDao.getApplicantCountByJobID(jobID)
    }

    suspend fun updateStatus(employeeID: Long, jobID: Long, status :String){
        applicationDao.updateStatus(employeeID , jobID, status)
    }

    suspend fun getApplicationByEmployeeIDAndJobID(employeeID: Long,jobID: Long):EntityApplication{
        return applicationDao.getApplicationByEmployeeIDAndJobID(employeeID,jobID)
    }

}