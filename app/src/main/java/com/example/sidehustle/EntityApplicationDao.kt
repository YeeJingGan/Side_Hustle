package com.example.sidehustle

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface EntityApplicationDao {
    @Insert
    suspend fun insert(application: EntityApplication)

    @Update
    suspend fun update(application: EntityApplication)

    @Query("SELECT * FROM application_table WHERE jobID = :key")
    suspend fun get(key: Long): EntityApplication?

    @Query("DELETE FROM application_table")
    suspend fun clear()

    @Query("SELECT * FROM application_table ORDER BY jobID ASC, employeeID ASC")
    fun readAllData(): LiveData<List<EntityApplication>>

    @Query("SELECT * FROM application_table WHERE jobID = :jobID")
    fun getApplicantsByJobID(jobID: Long): LiveData<List<EntityApplication>>

    @Query("SELECT COUNT(DISTINCT employeeID) FROM application_table WHERE jobID = :jobID")
    suspend fun getApplicantCountByJobID(jobID:Long):Int

    @Query("UPDATE application_table SET status = :status WHERE employeeID = :employeeID AND jobID = :jobID")
    suspend fun updateStatus(employeeID: Long, jobID: Long, status :String)

    @Query("SELECT * FROM application_table WHERE employeeID = :employeeID AND jobID =:jobID")
    suspend fun getApplicationByEmployeeIDAndJobID(employeeID: Long,jobID: Long):EntityApplication

}