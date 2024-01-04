package com.example.sidehustle

class EntityLanguageRepository(private val languageDao: EntityLanguageDao) {

    suspend fun getByEmployeeIDAndJobID(employeeID:Long,jobID:Long):List<EntityLanguage>{
        return languageDao.getByEmployeeIDAndJobID(employeeID, jobID)
    }
}