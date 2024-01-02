package com.example.sidehustle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class EmployerMyJobsOngoingEmployeeDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employer_my_jobs_ongoing_employee_details)

        val intent = intent

        val employeeID = intent.getLongExtra("employeeID",-100)
        Log.i("INTENT",employeeID.toString())
    }
}