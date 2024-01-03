package com.example.sidehustle

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface EntityEmployeeDao {
    @Insert
    suspend fun insert(employee: EntityEmployee)

    @Update
    suspend fun update(employee: EntityEmployee)

    @Query("SELECT * FROM employee_table WHERE employeeID = :key")
    suspend fun get(key: Long): EntityEmployee?

    @Query("DELETE FROM employee_table")
    suspend fun clear()

    @Query("SELECT * FROM employee_table ORDER BY employeeID ASC")
    fun getAll():LiveData<List<EntityEmployee>>
}