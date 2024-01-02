package com.example.sidehustle

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sidehustle.databinding.ActivityEmployeeHomeBinding

class EmployeeHomeActivity : AppCompatActivity() {

    lateinit var binding: ActivityEmployeeHomeBinding
    lateinit var jobs: List<EntityJob>
    lateinit var jobAdapter: EmployeeHomeJobAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_employee_home)

        populateJobs()

        setAdapter()

        setupRecyclerView()

        setListeners()
    }

    private fun setupRecyclerView() {
        binding.employeeHomeJobRecyclerview.adapter = jobAdapter
        binding.employeeHomeJobRecyclerview.layoutManager = LinearLayoutManager(this)
    }

    private fun populateJobs() {
        // TODO: Replace with a real job list
        jobs = listOf(
            EntityJob(
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
            ),
            EntityJob(
                2,
                2,
                "Job2",
                "JobState2",
                80,
                "2024-01-01",
                "2024-02-02",
                "10:00:00Z",
                "16:00:00Z",
                "jobDescription2"
            ),
            EntityJob(
                3,
                3,
                "Job3",
                "JobState3",
                90,
                "2024-01-01",
                "2024-02-02",
                "10:00:00Z",
                "16:00:00Z",
                "jobDescription1"
            ),
        )
    }

    private fun setAdapter() {
        jobAdapter = EmployeeHomeJobAdapter(jobs)
    }

    private fun setListeners() {
        binding.favoriteButton.setOnClickListener {
            startActivity((Intent(it.context, EmployeeHomeFavoriteJobsActivity::class.java)))
        }

        binding.employeeHomeBottomNav.apply {
            selectedItemId = R.id.bottom_nav_home_button
            setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.bottom_nav_home_button -> false
                    R.id.bottom_nav_my_jobs_button -> {
                        finish()
                        startActivity(
                            Intent(
                                applicationContext,
                                EmployeeMyJobsActivity::class.java
                            )
                        )
                        true
                    }

                    R.id.bottom_nav_my_profile_button -> {
                        finish()
                        startActivity(
                            Intent(
                                applicationContext,
                                EmployeeMyProfileActivity::class.java
                            )
                        )
                        true
                    }

                    else -> false
                }
            }
        }

        binding.employeeHomeSearchSearchview.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextChange(query: String?): Boolean {
                jobAdapter.filter.filter(query)

                return true
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(
                    binding.employeeHomeSearchSearchview.windowToken,
                    0
                )
                return true
            }
        })
    }

}