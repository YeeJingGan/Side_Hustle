package com.example.sidehustle

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sidehustle.databinding.ActivityEmployeeHomeBinding

class EmployeeHomeActivity : AppCompatActivity() {

    lateinit var binding: ActivityEmployeeHomeBinding
    lateinit var jobAdapter: EmployeeHomeJobAdapter
    lateinit var viewModel: EmployeeHomeViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_employee_home)

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(EmployeeHomeViewModel::class.java)

        viewModel.searchQuery.observe(this) { query ->
            jobAdapter.filter.filter(query)
        }

        jobAdapter = EmployeeHomeJobAdapter(emptyList())

        viewModel.applicableJobs.observe(this) { jobs ->
            jobAdapter = EmployeeHomeJobAdapter(jobs)
            binding.employeeHomeJobRecyclerview.adapter = jobAdapter
        }


        setupRecyclerView()

        setListeners()
    }

    private fun setupRecyclerView() {
        binding.employeeHomeJobRecyclerview.adapter = jobAdapter
        binding.employeeHomeJobRecyclerview.layoutManager = LinearLayoutManager(this)
    }


    private fun setListeners() {

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
                viewModel.setSearchQuery(query ?: "")

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

    override fun onResume() {
        super.onResume()
        Toast.makeText(this, "HIHIHI ${intent.getLongExtra("jobID", -100)}", Toast.LENGTH_LONG)
            .show()
    }

}