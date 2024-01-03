package com.example.sidehustle

import androidx.lifecycle.LiveData
import org.w3c.dom.Entity

class EntityEmployeeRepository(private val employeeDao:EntityEmployeeDao) {

    val allData : LiveData<List<EntityEmployee>> = employeeDao.getAll()

    suspend fun insert(employee: EntityEmployee){
        employeeDao.insert(employee)
    }
}