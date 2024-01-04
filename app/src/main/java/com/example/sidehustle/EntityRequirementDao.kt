package com.example.sidehustle

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface EntityRequirementDao {
    @Insert
    suspend fun insert(requirement: EntityRequirement)

    @Update
    suspend fun update(requirement: EntityRequirement)

    @Query("SELECT * FROM requirement_table WHERE requirementID = :key")
    suspend fun get(key: Long): EntityRequirement?

    @Query("DELETE FROM requirement_table")
    suspend fun clear()

    @Query("SELECT * FROM requirement_table")
    fun getAll(): LiveData<List<EntityRequirement>>

    @Query("SELECT content FROM requirement_table WHERE jobID = :jobID")
    suspend fun getByJobID(jobID:Long):List<String>
}