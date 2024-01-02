package com.example.sidehustle

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface EntityNegotiationDao {
    @Insert
    suspend fun insert(negotiation: EntityNegotiation)

    @Update
    suspend fun update(negotiation: EntityNegotiation)

    @Query("SELECT * FROM negotiation_table WHERE negotiationID = :key")
    suspend fun get(key: Long): EntityNegotiation?

    @Query("DELETE FROM negotiation_table")
    suspend fun clear()
}