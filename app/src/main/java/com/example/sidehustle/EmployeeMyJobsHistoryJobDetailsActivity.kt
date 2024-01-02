package com.example.sidehustle

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.sidehustle.databinding.ActivityEmployeeHomeJobDetailsBinding
import com.example.sidehustle.databinding.ActivityEmployeeMyJobsHistoryJobDetailsBinding

class EmployeeMyJobsHistoryJobDetailsActivity : AppCompatActivity() {

    lateinit var binding : ActivityEmployeeMyJobsHistoryJobDetailsBinding
    var jobID : Long = -100
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_employee_my_jobs_history_job_details)

        val intent = intent
        jobID = intent.getLongExtra("jobID",-100)
        Toast.makeText(this,jobID.toString(),Toast.LENGTH_SHORT).show()

        setListeners()
    }

    private fun setListeners(){
        binding.employeeMyJobsHistoryJobDetailsBackButton.setOnClickListener {
            finish()
        }
        binding.employeeHistoryCommentButton.setOnClickListener {
            val intent = Intent(this,EmployeeMyJobsHistoryJobDetailsCommentRatingActivity::class.java)
            intent.putExtra("jobID",jobID)
            startActivity(intent)
        }
    }
}