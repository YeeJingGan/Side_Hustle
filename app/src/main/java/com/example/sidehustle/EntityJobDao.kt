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
}