package com.example.sidehustle

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class EmployeeHomeFavoriteJobsViewModel(private val application: Application): AndroidViewModel(application) {
    private val repository: EntityFavouriteRepository

    val allData: LiveData<List<EntityFavorite>>

    init{
        val database = SideHustleDatabase.getDatabase(application)
        repository = EntityFavouriteRepository(database.favoriteDao())
        allData = repository.getAll()
    }


}