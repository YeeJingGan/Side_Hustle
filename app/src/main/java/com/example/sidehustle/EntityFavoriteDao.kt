package com.example.sidehustle

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface EntityFavoriteDao {
    @Insert
    suspend fun insert(favorite: EntityFavorite)

    @Update
    suspend fun update(favorite: EntityFavorite)

    @Query("SELECT * FROM favorite_table WHERE jobID = :jobID AND employeeID = :employeeID")
    suspend fun getByJobAndEmployee(jobID: Long,employeeID :Long): EntityFavorite?

    @Query("DELETE FROM favorite_table")
    suspend fun clear()

}