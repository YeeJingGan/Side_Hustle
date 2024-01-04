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
    suspend fun get(key: Long): EntityNegotiation

    @Query("DELETE FROM negotiation_table")
    suspend fun clear()

    @Query("SELECT * FROM negotiation_table WHERE employeeID = :employeeID AND jobID = :jobID ORDER BY negotiationID DESC LIMIT 1")
    suspend fun getLatestNegotiationByEmployeeIDAndJobID(employeeID:Long, jobID:Long):EntityNegotiation

    @Query("SELECT * FROM negotiation_table ORDER BY negotiationID ASC")
    fun getAll(): LiveData<List<EntityNegotiation>>

    @Query("SELECT * FROM negotiation_table WHERE employeeID = :employeeID AND (employeeID, jobID) IN (SELECT employeeID, jobID FROM negotiation_table WHERE employeeID = :employeeID GROUP BY employeeID, jobID HAVING MAX(negotiationID))")
    suspend fun getLatestNegotiationsByEmployeeID(employeeID: Long): List<EntityNegotiation>

}