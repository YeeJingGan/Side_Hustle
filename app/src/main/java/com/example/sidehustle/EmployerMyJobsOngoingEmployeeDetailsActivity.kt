package com.example.sidehustle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import com.example.sidehustle.databinding.ActivityEmployerMyJobsOngoingEmployeeDetailsBinding

class EmployerMyJobsOngoingEmployeeDetailsActivity : AppCompatActivity() {

    lateinit var binding: ActivityEmployerMyJobsOngoingEmployeeDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_employer_my_jobs_ongoing_employee_details)


        val intent = intent

        val employeeID = intent.getLongExtra("employeeID",-100)
        Log.i("INTENT",employeeID.toString())

        binding.employerMyJobsOngoingEmployeeDetailsBackButton.setOnClickListener { finish() }
    }
}