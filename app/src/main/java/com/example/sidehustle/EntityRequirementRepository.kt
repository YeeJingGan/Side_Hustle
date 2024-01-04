package com.example.sidehustle

import androidx.lifecycle.LiveData

class EntityRequirementRepository(private val requirementDao: EntityRequirementDao) {

    val allData: LiveData<List<EntityRequirement>> = requirementDao.getAll()

    suspend fun insert(requirement: EntityRequirement) {
        requirementDao.insert(requirement)
    }

    suspend fun getByJobID(jobID:Long):List<String>{
        return requirementDao.getByJobID(jobID)
    }
}