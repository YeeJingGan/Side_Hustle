package com.example.sidehustle

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sidehustle.databinding.ActivityEmployerHomeBinding

class EmployerHomeActivity : AppCompatActivity() {

    lateinit var binding: ActivityEmployerHomeBinding
    lateinit var approvedJobsAdapter: EmployerHomeJobAdapter
    lateinit var pendingJobsAdapter: EmployerHomeJobAdapter
    lateinit var viewModel: EmployerHomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_employer_home)

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(EmployerHomeViewModel::class.java)

        approvedJobsAdapter = EmployerHomeJobAdapter(emptyList())
        pendingJobsAdapter = EmployerHomeJobAdapter(emptyList())

        viewModel.searchQuery.observe(this) { query ->
            approvedJobsAdapter.filter.filter(query)
            approvedJobsAdapter.filter.filter(query)
        }

        viewModel.approvedJobs.observe(this){ jobs ->
            approvedJobsAdapter = EmployerHomeJobAdapter(jobs)
            binding.employerHomeRecyclerviewApprovedJobs.adapter = approvedJobsAdapter
        }

        viewModel.pendingJobs.observe(this){ jobs ->
            pendingJobsAdapter = EmployerHomeJobAdapter(jobs)
            binding.employerHomeRecyclerviewPendingApproval.adapter = pendingJobsAdapter
        }


        setupRecyclerView()

        setListeners()
    }

    private fun setupRecyclerView() {
        binding.employerHomeRecyclerviewApprovedJobs.adapter = approvedJobsAdapter
        binding.employerHomeRecyclerviewApprovedJobs.layoutManager = LinearLayoutManager(this)

        binding.employerHomeRecyclerviewPendingApproval.adapter = pendingJobsAdapter
        binding.employerHomeRecyclerviewPendingApproval.layoutManager = LinearLayoutManager(this)
    }

    private fun setListeners() {
        binding.employerHomeButtonExpand1.setOnClickListener {
            val approvedJobsRecyclerView = binding.employerHomeRecyclerviewApprovedJobs

            if (approvedJobsRecyclerView.visibility == View.VISIBLE) {
                approvedJobsRecyclerView.visibility = View.GONE
                binding.employerHomeButtonExpand1.setImageResource(R.drawable.ic_expand)

            } else {
                approvedJobsRecyclerView.visibility = View.VISIBLE
                binding.employerHomeButtonExpand1.setImageResource(R.drawable.ic_expand_less)
            }
        }

        binding.employerHomeButtonExpand2.setOnClickListener {
            val pendingJobsRecyclerView = binding.employerHomeRecyclerviewPendingApproval

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
                                applicationContext, EmployerMyJobsActivity::class.java
                            )
                        )
                        true
                    }

                    R.id.bottom_nav_my_profile_button -> {
                        finish()
                        startActivity(
                            Intent(
                                applicationContext, EmployerMyProfileActivity::class.java
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

        binding.employerHomeSearchSearchview.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener {
            override fun onQueryTextChange(query: String?): Boolean {
                viewModel.setSearchQuery(query?:"")
                return true
            }

            override fun onQueryTextSubmit(query: String?): Boolean {
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(
                    binding.employerHomeSearchSearchview.windowToken, 0
                )
                return true
            }
        })
    }
    private fun hideSoftInput(view: View) {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    private fun showSoftInput(view: View) {
        val imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
    }
}