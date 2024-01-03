package com.example.sidehustle

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel(application: Application) : AndroidViewModel(application) {
    val employerRepository: EntityEmployerRepository

    init {
        val database = SideHustleDatabase.getDatabase(application)
        employerRepository = EntityEmployerRepository(database.employerDao())
    }

    fun insertEmployers(employers: List<EntityEmployer>) {
        viewModelScope.launch {
            for (i in 0..<employers.size) {
                employerRepository.insert(employers[i])
            }
        }
    }


}