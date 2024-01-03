package com.example.sidehustle

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sidehustle.databinding.ActivityEmployeeHomeJobDetailsBinding

class EmployeeHomeJobDetailsActivity : AppCompatActivity() {
    var JOB_DETAILS_REQUEST_CODE: Int = 88
    lateinit var binding: ActivityEmployeeHomeJobDetailsBinding
    lateinit var job: EntityJob
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_employee_home_job_details)

        val intent = intent

        setSupportActionBar(binding.employeeHomeJobDetailsToolbar)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_36px)
            setDisplayShowTitleEnabled(false)
        }

        getData()

        setListeners()

        // TODO : CHECK POP WILL GET THE SAME ID OR NOT
        val jobID = intent.getLongExtra("jobID", -200)
        Toast.makeText(this, "JOBID is $jobID", Toast.LENGTH_SHORT).show()
    }

    private fun setListeners() {
        binding.employeeHomeJobDetailsApplyButton.setOnClickListener {
            apply(it)
        }
        binding.employeeHomeJobDetailsLoveButton.setOnClickListener {
            addToFavourites()
        }
    }

    private fun addToFavourites() {
        // TODO : ADD TO DATABASE

        Toast.makeText(this, "HAHAHA NOT YET IMPLEMENT DATABASE", Toast.LENGTH_SHORT)
            .show()
    }

    private fun apply(view: View) {
        val intent = Intent(view.context, EmployeeHomeJobDetailsApplyJobsActivity::class.java)
        intent.putExtra("jobID", job.jobID)
        startActivityForResult((intent),JOB_DETAILS_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == JOB_DETAILS_REQUEST_CODE && resultCode == RESULT_OK) {
            val JOBID = data?.getLongExtra("jobID",-100)
            Toast.makeText(this,"jobID is $JOBID",Toast.LENGTH_SHORT).show()
        }
    }
    private fun getData() {
        // TODO: RMB GET FROM DATABASE, GET STARS AS WELL AND REQUIREMENTS ALSO
        binding.employee = EntityEmployee(
            1,
            "Gan Yee Jing",
            "gyjemployee@email.com",
            "abc123",
        )

        job = EntityJob(
            1,
            1,
            "Job1",
            "JobState1", "Address3",
            "70000",
            70,
            "2024-01-01",
            "2024-02-02",
            "10:00:00Z",
            "16:00:00Z",
            "jobDescription1", "APPROVED"
        )
        binding.job = job

        val adapter = EmployeeHomeJobDetailsRequirementAdapter(listOf("REQ 1", "REQ 2", "REQ 3"))

        binding.employeeHomeJobDetailsRequirementsRecyclerview.adapter = adapter
        binding.employeeHomeJobDetailsRequirementsRecyclerview.layoutManager =
            LinearLayoutManager(this)

    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}