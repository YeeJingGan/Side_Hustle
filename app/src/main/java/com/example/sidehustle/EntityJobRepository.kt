package com.example.sidehustle

import androidx.lifecycle.LiveData

class EntityJobRepository(private val jobDao: EntityJobDao) {

    val allData: LiveData<List<EntityJob>> = jobDao.getAll()

    suspend fun insert(job: EntityJob) {
        jobDao.insert(job)
    }

    fun getByEmployerIDAndStatus(employerID: Long, status: String): LiveData<List<EntityJob>> {
        return jobDao.getByEmployerIDAndStatus(employerID, status)
    }

    fun getJobsStartingTodayOrLater(): LiveData<List<EntityJob>> {
        return jobDao.getJobsStartingTodayOrLater()
    }

    fun getByEmployerIDJobsStartingTodayOrLater(employerID: Long): LiveData<List<EntityJob>> {
        return jobDao.getByEmployerIDJobsStartingTodayOrLater(employerID)
    }

    suspend fun getByJobID(jobID: Long): EntityJob {
        return jobDao.getByJobID(jobID)
    }

}