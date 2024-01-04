package com.example.sidehustle

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface EntityEmployerDao {
    @Insert
    suspend fun insert(employer: EntityEmployer)

    @Update
    suspend fun update(employer: EntityEmployer)

    @Query("SELECT * FROM employer_table WHERE employerID = :key")
    suspend fun get(key: Long): EntityEmployer?

    @Query("DELETE FROM employer_table")
    suspend fun clear()

    @Query("SELECT * FROM employer_table ORDER BY employerID ASC")
    fun getAll(): LiveData<List<EntityEmployer>>

    @Query("SELECT employer_table.* FROM employer_table INNER JOIN job_table ON employer_table.employerID = job_table.employerID WHERE job_table.jobID = :jobID")
    suspend fun getEmployerByJobID(jobID: Long):EntityEmployer

    @Query("SELECT employer_table.* FROM employer_table " +
            "INNER JOIN job_table ON employer_table.employerID = job_table.employerID " +
            "INNER JOIN rating_table ON job_table.employerID = rating_table.employeeID AND job_table.jobID = rating_table.jobID " +
            "WHERE rating_table.employeeID = :employeeId AND rating_table.jobID = :jobId")
    suspend fun getEmployerByJobIDAndEmployeeID(employeeId: Long, jobId: Long): EntityEmployer

}