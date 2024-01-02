package com.example.sidehustle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.sidehustle.databinding.ActivityEmployeeMyJobsOngoingJobDetailsBinding

class EmployeeMyJobsOngoingJobDetailsActivity : AppCompatActivity() {

    lateinit var binding : ActivityEmployeeMyJobsOngoingJobDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_employee_my_jobs_ongoing_job_details)

        val intent = intent
        val jobID = intent.getLongExtra("jobID",-100)
        Toast.makeText(this,jobID.toString(),Toast.LENGTH_SHORT).show()
        // TODO : RMB CHECK POP JOB ID VALUE

        setListeners()
    }

    private fun setListeners(){
        binding.employeeMyJobsOngoingJobDetailsBackButton.setOnClickListener {
            finish()
        }
    }
}