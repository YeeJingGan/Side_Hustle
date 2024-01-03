package com.example.sidehustle

import androidx.lifecycle.LiveData

class EntityFavouriteRepository(private val favoriteDao: EntityFavoriteDao) {
    val allData : LiveData<List<EntityFavorite>> = favoriteDao.getAll()

    suspend fun insert(favorite: EntityFavorite) {
        favoriteDao.insert(favorite)
    }

    suspend fun delete(favorite: EntityFavorite) {
        favoriteDao.delete(favorite)
    }

    suspend fun getByJobAndEmployee(jobID: Long,employeeID :Long): EntityFavorite?{
        return favoriteDao.getByJobAndEmployee(jobID, employeeID)
    }

    fun getAll():LiveData<List<EntityFavorite>>{
        return favoriteDao.getAll()
    }
}