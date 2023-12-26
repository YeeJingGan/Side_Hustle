package com.example.sidehustle.activites.employee

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.widget.Toolbar
import com.example.sidehustle.Job
import com.example.sidehustle.JobAdapter
import com.example.sidehustle.R
import com.google.android.material.appbar.MaterialToolbar

class EmployeeFavoriteJobsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employee_favorite_jobs)

        val toolbar = findViewById<MaterialToolbar>(R.id.employee_favorite_jobs_toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
//            setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_36px)
        }
        val listView = findViewById<ListView>(R.id.employee_favorite_jobs_list_view)

        val jobList: List<Job> = createJobList()
        val jobAdapter = JobAdapter(this, jobList)

        listView.adapter = jobAdapter

    }

    private fun createJobList(): List<Job> {
// TODO: Replace with a real job list
        return mutableListOf(
            Job(R.drawable.sample_profile_photo, "Customer Service", 70, "Ampang", "KL"),
            Job(R.drawable.sample_profile_photo, "Customer Service", 70, "Ampang", "KL"),
            Job(R.drawable.sample_profile_photo, "Customer Service", 70, "Ampang", "KL"),
            Job(R.drawable.sample_profile_photo, "Customer Service", 70, "Ampang", "KL"),
            Job(R.drawable.sample_profile_photo, "Customer Service", 70, "Ampang", "KL"),
            Job(R.drawable.sample_profile_photo, "Customer Service", 70, "Ampang", "KL"),
            Job(R.drawable.sample_profile_photo, "Customer Service", 70, "Ampang", "KL"),
        )
    }
}