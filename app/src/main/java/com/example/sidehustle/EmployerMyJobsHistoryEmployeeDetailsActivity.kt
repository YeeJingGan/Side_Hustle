package com.example.sidehustle

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.sidehustle.databinding.ActivityEmployerMyJobsHistoryEmployeeDetailsBinding

class EmployerMyJobsHistoryEmployeeDetailsActivity : AppCompatActivity() {

    lateinit var binding:ActivityEmployerMyJobsHistoryEmployeeDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_employer_my_jobs_history_employee_details)

        binding.employerMyJobsHistoryEmployeeDetailsBackButton.setOnClickListener { finish() }

        binding.pendingCommentButton.setOnClickListener{
            val context = it.context
            val intent = Intent(context,EmployerMyJobsHistoryEmployeeDetailsCommentRatingActivity::class.java)
            startActivity(intent)
        }
    }
}