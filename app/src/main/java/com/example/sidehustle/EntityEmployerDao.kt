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
    fun getAll():LiveData<List<EntityEmployer>>

}