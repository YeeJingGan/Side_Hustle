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

    lateinit var binding: ActivityEmployeeHomeJobDetailsBinding
    lateinit var job: EntityJob
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_employee_home_job_details)


        setSupportActionBar(binding.emplyeeHomeJobDetailsToolbar)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
        }

        getData()

        setListeners()

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
        val intent = Intent(view.context, EmployeeMyJobsNegotiatingJobDetailsNegotiateActivity::class.java)
        intent.putExtra("jobID", job.jobID)
        view.context.startActivity((intent))
    }

    private fun getData() {
        // TODO: RMB GET FROM DATABASE, GET STARS AS WELL AND REQUIREMENTS ALSO
        binding.employee = EntityEmployee(
            1,
            "Gan Yee Jing",
            "gyjemployee@email.com",
            "abc123",
            byteArrayOf(0x48, 101, 108, 108, 111)
        )

        job = EntityJob(
            1,
            1,
            "Job1",
            "JobState1",
            70,
            "2024-01-01",
            "2024-02-02",
            "10:00:00Z",
            "16:00:00Z",
            "jobDescription1"
        )
        binding.job = job

        val adapter = EmployeeHomeJobDetailsApplyJobsRequirementAdapter(listOf("REQ 1", "REQ 2", "REQ 3"))

        binding.employeeHomeJobDetailsRequirementsRecyclerview.adapter = adapter
        binding.employeeHomeJobDetailsRequirementsRecyclerview.layoutManager =
            LinearLayoutManager(this)

    }
}