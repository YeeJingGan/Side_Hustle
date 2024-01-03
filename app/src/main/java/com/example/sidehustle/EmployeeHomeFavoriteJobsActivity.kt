package com.example.sidehustle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sidehustle.databinding.ActivityEmployeeHomeFavoriteJobsBinding
import com.google.android.material.appbar.MaterialToolbar

class EmployeeHomeFavoriteJobsActivity : AppCompatActivity() {

    lateinit var binding: ActivityEmployeeHomeFavoriteJobsBinding
    lateinit var jobs: List<EntityJob>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =
            DataBindingUtil.setContentView(this, R.layout.activity_employee_home_favorite_jobs)

        populateJobs()

        setupRecyclerView()

        setupScreen()
    }

    private fun populateJobs() {
        // TODO: Replace with a real job list
        jobs = listOf(
            EntityJob(
                1,
                1,
                "Job1",
                "JobState1",
                "Address3",
                "70000",
                70,
                "2024-01-01",
                "2024-02-02",
                "10:00:00Z",
                "16:00:00Z",
                "jobDescription1","APPROVED"
            ),
            EntityJob(
                2,
                2,
                "Job2",
                "JobState2",
                "Address3",
                "70000",
                80,
                "2024-01-01",
                "2024-02-02",
                "10:00:00Z",
                "16:00:00Z",
                "jobDescription2","APPROVED"
            ),
            EntityJob(
                3,
                3,
                "Job3",
                "JobState3", "Address3",
                "70000",
                90,
                "2024-01-01",
                "2024-02-02",
                "10:00:00Z",
                "16:00:00Z",
                "jobDescription1","APPROVED"
            ),
        )
    }

    private fun setupRecyclerView() {
        binding.employeeFavoriteJobsRecyclerview.adapter = EmployeeHomeJobAdapter(jobs)
        binding.employeeFavoriteJobsRecyclerview.layoutManager = LinearLayoutManager(this)
    }

    private fun setupScreen() {
        setSupportActionBar(binding.employeeFavoriteJobsToolbar)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_36px)
            setDisplayShowTitleEnabled(false)
        }
    }

}