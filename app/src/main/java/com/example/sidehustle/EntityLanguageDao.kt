package com.example.sidehustle

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface EntityLanguageDao {
    @Insert
    suspend fun insert(language: EntityLanguage)
    @Update
    suspend fun update(language: EntityLanguage)
    @Query("SELECT * FROM language_table WHERE languageID = :key")
    suspend fun get(key: Long): EntityLanguage?
    @Query("DELETE FROM language_table")
    suspend fun clear()

}