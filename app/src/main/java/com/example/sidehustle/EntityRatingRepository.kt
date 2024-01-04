package com.example.sidehustle

import androidx.lifecycle.LiveData

class EntityRatingRepository(private val ratingDao: EntityRatingDao) {
    val readAllData: LiveData<List<EntityRating>> = ratingDao.readAllData()

    suspend fun addApplication(rating: EntityRating) {
        ratingDao.insert(rating)
    }

    suspend fun getAverageRatingByJobIDAndCommenter(jobID: Long, commenter: String): Double {
        val value = ratingDao.getAverageRatingByJobIDAndCommenter(jobID, commenter)
        return value ?: 0.0
    }

    suspend fun getAverageRatingByEmployeeIDAndCommenter(employeeID : Long,commenter : String):Double{
        return ratingDao.getAverageRatingByEmployeeIDAndCommenter(employeeID,commenter) ?: 0.0
    }

}