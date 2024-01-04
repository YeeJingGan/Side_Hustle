package com.example.sidehustle

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface EntityResumeDao {
    @Insert
    suspend fun insert(resume: EntityResume)
    @Update
    suspend fun update(resume: EntityResume)
    @Query("SELECT * FROM resume_table WHERE employeeID = :key")
    suspend fun get(key: Long): EntityResume?
    @Query("DELETE FROM resume_table")
    suspend fun clear()
    @Query("SELECT * FROM resume_table ORDER BY employeeID DESC")
    fun getAll():LiveData<List<EntityResume>>

}