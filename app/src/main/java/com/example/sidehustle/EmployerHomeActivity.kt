package com.example.sidehustle

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sidehustle.databinding.ActivityEmployerHomeBinding

class EmployerHomeActivity : AppCompatActivity() {

    lateinit var binding: ActivityEmployerHomeBinding
    lateinit var jobs: List<JobEntity>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_employer_home)

        binding.employerHomeButtonExpand1.setOnClickListener {
            var approvedJobsRecyclerView = binding.employerHomeRecyclerviewApprovedJobs

            if (approvedJobsRecyclerView.visibility == View.VISIBLE) {
                approvedJobsRecyclerView.visibility = View.GONE
                binding.employerHomeButtonExpand1.setImageResource(R.drawable.ic_expand)

            } else {
                approvedJobsRecyclerView.visibility = View.VISIBLE
                binding.employerHomeButtonExpand1.setImageResource(R.drawable.ic_expand_less)
            }
        }

        binding.employerHomeButtonExpand2.setOnClickListener {
            var pendingJobsRecyclerView = binding.employerHomeRecyclerviewPendingApproval

            if (pendingJobsRecyclerView.visibility == View.VISIBLE) {
                pendingJobsRecyclerView.visibility = View.GONE
                binding.employerHomeButtonExpand2.setImageResource(R.drawable.ic_expand)
            } else {
                pendingJobsRecyclerView.visibility = View.VISIBLE
                binding.employerHomeButtonExpand2.setImageResource(R.drawable.ic_expand_less)

            }
        }

        binding.employerHomeBottomNav.apply {
            selectedItemId = R.id.bottom_nav_home_button
            setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.bottom_nav_home_button -> false
                    R.id.bottom_nav_my_jobs_button -> {
                        finish()
                        startActivity(
                            Intent(
                                applicationContext,
                                EmployerMyJobsOngoingActivity::class.java
                            )
                        )
                        true
                    }

                    R.id.bottom_nav_my_profile_button -> {
                        finish()
                        startActivity(
                            Intent(
                                applicationContext,
                                EmployerMyProfileActivity::class.java
                            )
                        )
                        true
                    }

                    else -> false
                }
            }
        }

        binding.employerHomeButtonUpload.setOnClickListener {
            startActivity(Intent(applicationContext, EmployerHomeUploadJobsActivity::class.java))
        }

        populateJobs()

        val approvedJobsAdapter = EmployerHomeJobAdapter(jobs)
        val pendingJobsAdapter = EmployerHomeJobAdapter(jobs)

        binding.employerHomeSearchSearchview.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextChange(newText: String?): Boolean {
                approvedJobsAdapter.filter.filter(newText)
                pendingJobsAdapter.filter.filter(newText)

                return true
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(
                    binding.employerHomeSearchSearchview.windowToken,
                    0
                )
                return true
            }
        })



        binding.employerHomeRecyclerviewApprovedJobs.adapter = approvedJobsAdapter
        binding.employerHomeRecyclerviewApprovedJobs.layoutManager = LinearLayoutManager(this)

        binding.employerHomeRecyclerviewPendingApproval.adapter = pendingJobsAdapter
        binding.employerHomeRecyclerviewPendingApproval.layoutManager = LinearLayoutManager(this)


    }

    private fun populateJobs() {
        jobs = listOf(
            JobEntity(
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
            JobEntity(
                2,
                2,
                "Job2",
                "JobState3",
                80,
                "2024-01-01",
                "2024-02-02",
                "10:00:00Z",
                "16:00:00Z",
                "jobDescription2"
            ),
            JobEntity(
                3,
                3,
                "Job3",
                "JobState3",
                90,
                "2024-01-01",
                "2024-02-02",
                "10:00:00Z",
                "16:00:00Z",
                "jobDescription3"
            )
        )
    }
}