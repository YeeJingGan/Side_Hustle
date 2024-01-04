package com.example.sidehustle

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface EntityRatingDao {
    @Insert
    suspend fun insert(rating: EntityRating)

    @Update
    suspend fun update(rating: EntityRating)

    @Query("SELECT * FROM rating_table WHERE ratingID = :key")
    suspend fun get(key: Long): EntityRating?

    @Query("DELETE FROM rating_table")
    suspend fun clear()

    @Query("SELECT * FROM rating_table ORDER BY ratingID ASC")
    fun readAllData(): LiveData<List<EntityRating>>

    @Query("SELECT AVG(rating) FROM rating_table WHERE jobID IN (SELECT jobID FROM job_table WHERE employerID = :employerID) AND commenter LIKE :commenter")
    suspend fun getAverageRatingByJobIDAndCommenter(employerID: Long, commenter: String): Double?

    @Query("SELECT AVG(rating) FROM rating_table WHERE employeeID = :employeeID AND commenter LIKE :commenter")
    suspend fun getAverageRatingByEmployeeIDAndCommenter(employeeID:Long,commenter: String):Double?
}