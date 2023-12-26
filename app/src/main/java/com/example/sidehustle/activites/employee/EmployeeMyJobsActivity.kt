package com.example.sidehustle.activites.employee

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.sidehustle.R

class EmployeeMyJobsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employee_my_jobs)
    }
    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }
}