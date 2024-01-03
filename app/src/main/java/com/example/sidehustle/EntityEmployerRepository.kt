package com.example.sidehustle

import androidx.lifecycle.LiveData

class EntityEmployerRepository(private val employerDao: EntityEmployerDao) {
    val allData: LiveData<List<EntityEmployer>> = employerDao.getAll()

    suspend fun insert(employer: EntityEmployer) {
        employerDao.insert(employer)
    }

}