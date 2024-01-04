package com.example.sidehustle

import androidx.lifecycle.LiveData

class EntityEmployerRepository(private val employerDao: EntityEmployerDao) {
    val allData: LiveData<List<EntityEmployer>> = employerDao.getAll()

    suspend fun insert(employer: EntityEmployer) {
        employerDao.insert(employer)
    }

    suspend fun getEmployerByJobID(jobID:Long):EntityEmployer{
        return employerDao.getEmployerByJobID(jobID)
    }

    suspend fun getEmployerByJobIDAndEmployeeID(jobID: Long,employeeID: Long):EntityEmployer{
        return employerDao.getEmployerByJobIDAndEmployeeID(jobID, employeeID)
    }

}