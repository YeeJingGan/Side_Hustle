package com.example.sidehustle

import androidx.lifecycle.LiveData

class EntityEmployeeRepository(private val employeeDao:EntityEmployeeDao) {

    val allData : LiveData<List<EntityEmployee>> = employeeDao.getAll()

    suspend fun insert(employee: EntityEmployee){
        employeeDao.insert(employee)
    }

    suspend fun getByJobIDNegotiatingEmployees(jobId: Long): List<EntityEmployee>{
        return employeeDao.getByJobIDNegotiatingEmployees(jobId)
    }

    suspend fun get(key:Long):EntityEmployee?{
        return employeeDao.get(key)
    }
}