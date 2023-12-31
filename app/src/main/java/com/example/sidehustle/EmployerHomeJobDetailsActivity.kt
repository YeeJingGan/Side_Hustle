package com.example.sidehustle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class EmployerHomeJobDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employer_home_job_details)

        val intent = intent
        val jobID = intent.getLongExtra("jobID",-100)
        Log.i("INTENT",jobID.toString())
        // Test PASSED
    }
}