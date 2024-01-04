package com.example.sidehustle

import androidx.lifecycle.LiveData

class EntityJobRepository(private val jobDao: EntityJobDao) {

    val allData: LiveData<List<EntityJob>> = jobDao.getAll()

    suspend fun insert(job: EntityJob){
        jobDao.insert(job)
    }

    suspend fun update(job: EntityJob){
        jobDao.update(job)
    }

    suspend fun get(jobID:Long): EntityJob ? {
        return jobDao.get(jobID)
    }

    fun getByEmployerIDAndStatus(employerID:Long,status:String): LiveData<List<EntityJob>> {
        return jobDao.getByEmployerIDAndStatus(employerID, status)
    }

    fun getJobsStartingTodayOrLater():LiveData<List<EntityJob>>{
        return jobDao.getJobsStartingTomorrow()
    }

    suspend fun getByJobID(jobID: Long): EntityJob {
        return jobDao.getByJobID(jobID)
    }

    suspend fun getByEmployerIDApprovedJobsWithApplications(employerID: Long):List<EntityJob>{
        return jobDao.getByEmployerIDApprovedJobsWithApplications(employerID)
    }

}