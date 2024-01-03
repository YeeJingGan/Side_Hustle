package com.example.sidehustle

import android.app.Application
import androidx.lifecycle.LiveData

class EntityApplicationRepository(private val applicationDao: EntityApplicationDao) {

        val readAllData : LiveData<List<EntityApplication>> = applicationDao.readAllData()

        suspend fun addApplication(application: EntityApplication){
            applicationDao.insert(application)
        }
}