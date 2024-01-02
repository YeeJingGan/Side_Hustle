package com.example.sidehustle

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface EntityRatingDao {
    @Insert
    suspend fun insert(rating: EntityRating)

    @Update
    suspend fun update(rating: EntityRating)

    @Query("SELECT * FROM rating_table WHERE ratingID = :key")
    suspend fun get(key: Long): EntityRating?

    @Query("DELETE FROM rating_table")
    suspend fun clear()

}