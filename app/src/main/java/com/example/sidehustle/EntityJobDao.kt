package com.example.sidehustle

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface EntityJobDao {
    @Insert
    suspend fun insert(job: EntityJob)

    @Update
    suspend fun update(job: EntityJob)

    @Query("SELECT * FROM job_table WHERE jobID = :key")
    suspend fun get(key: Long): EntityJob?

    @Query("DELETE FROM job_table")
    suspend fun clear()

    @Query("SELECT * FROM job_table ORDER BY jobID ASC")
    fun getAll(): LiveData<List<EntityJob>>

    @Query("SELECT * FROM job_table WHERE jobID = :jobID")
    suspend fun getByJobID(jobID: Long): EntityJob

    @Query("SELECT * FROM job_table WHERE employerID = :employerID AND status LIKE :status ")
    fun getByEmployerIDAndStatus(employerID: Long, status: String): LiveData<List<EntityJob>>

    @Query("SELECT * FROM job_table WHERE date(substr(startDate, 7, 4) || '-' || substr(startDate, 4, 2) || '-' || substr(startDate, 1, 2)) >= date('now') AND status LIKE 'APPROVED'")
    fun getJobsStartingTodayOrLater(): LiveData<List<EntityJob>>

    @Query("SELECT * FROM job_table WHERE date(substr(startDate, 7, 4) || '-' || substr(startDate, 4, 2) || '-' || substr(startDate, 1, 2)) >= date('now') AND status LIKE 'APPROVED' AND employerID = :employerID")
    fun getByEmployerIDJobsStartingTodayOrLater(employerID: Long): LiveData<List<EntityJob>>
}