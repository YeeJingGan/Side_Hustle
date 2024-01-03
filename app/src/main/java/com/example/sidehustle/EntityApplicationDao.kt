package com.example.sidehustle

import android.app.Application
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
}