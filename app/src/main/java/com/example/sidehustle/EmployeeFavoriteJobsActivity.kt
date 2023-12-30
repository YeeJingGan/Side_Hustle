package com.example.sidehustle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
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

        val jobList: List<LegacyJob> = createJobList()
        val jobAdapter = LegacyJobAdapter(this, jobList)

        listView.adapter = jobAdapter

    }

    private fun createJobList(): List<LegacyJob> {
// TODO: Replace with a real job list
        return mutableListOf(
            LegacyJob(R.drawable.sample_profile_photo, "Customer Service", 70, "Ampang", "KL"),
            LegacyJob(R.drawable.sample_profile_photo, "Customer Service", 70, "Ampang", "KL"),
            LegacyJob(R.drawable.sample_profile_photo, "Customer Service", 70, "Ampang", "KL"),
            LegacyJob(R.drawable.sample_profile_photo, "Customer Service", 70, "Ampang", "KL"),
            LegacyJob(R.drawable.sample_profile_photo, "Customer Service", 70, "Ampang", "KL"),
            LegacyJob(R.drawable.sample_profile_photo, "Customer Service", 70, "Ampang", "KL"),
            LegacyJob(R.drawable.sample_profile_photo, "Customer Service", 70, "Ampang", "KL"),
        )
    }
}