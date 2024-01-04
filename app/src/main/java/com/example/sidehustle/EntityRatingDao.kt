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

    @Query("SELECT * FROM rating_table WHERE employeeID = :employeeID AND commenter LIKE :commenter")
    suspend fun getRatingByEmployeeIDAndCommenter(employeeID: Long,commenter: String):List<EntityRating>

    @Query("SELECT rating_table.* FROM rating_table " +
            "INNER JOIN application_table ON rating_table.employeeID = application_table.employeeID AND rating_table.jobID = application_table.jobID " +
            "INNER JOIN job_table ON application_table.jobID = job_table.jobID " +
            "WHERE job_table.employerID = :employerID AND rating_table.commenter = :commenter")
    suspend fun getRatingsForEmployer(employerID: Long,commenter: String): List<EntityRating>
}