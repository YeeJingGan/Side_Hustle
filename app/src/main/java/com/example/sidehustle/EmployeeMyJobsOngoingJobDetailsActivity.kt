package com.example.sidehustle

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sidehustle.databinding.ActivityEmployeeMyJobsOngoingJobDetailsBinding
import kotlinx.coroutines.launch

class EmployeeMyJobsOngoingJobDetailsActivity : AppCompatActivity() {

    lateinit var binding : ActivityEmployeeMyJobsOngoingJobDetailsBinding
    lateinit var viewModel: EmployeeMyJobsOngoingJobDetailsViewModel
    var jobID : Long = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_employee_my_jobs_ongoing_job_details)
        viewModel = ViewModelProvider(this).get(EmployeeMyJobsOngoingJobDetailsViewModel::class.java)

        val intent = intent
        jobID = intent.getLongExtra("jobID",-100)
        Toast.makeText(this,jobID.toString(),Toast.LENGTH_SHORT).show()
        // TODO : RMB CHECK POP JOB ID VALUE

        setListeners()

        jobID = intent.getLongExtra("jobID", -200)
        Toast.makeText(this, "JOBID is $jobID", Toast.LENGTH_SHORT).show()

        getData(jobID)

        viewModel.starCount.observe(this) {
            Log.i("AVG", it.toString())
            updateStarColors(it)
        }
    }

    private fun getData(jobID: Long) {

        val adapter = EmployeeHomeJobDetailsRequirementAdapter(listOf("Hard Working", "Punctual", "Friendly"))

        binding.employeeMyJobsOngoingJobDetailsRecyclerview.adapter = adapter
        binding.employeeMyJobsOngoingJobDetailsRecyclerview.layoutManager =
            LinearLayoutManager(this)

        viewModel.selectedJob.observe(this, Observer { selectedJob -> selectedJob?.let{
            binding.employeeOngoingJobDetailsCompanyLocation.text = selectedJob.state
            binding.employeeOngoingJobDetailsOfferedPosition.text = selectedJob.title
            binding.employeeOngoingJobDetailsOfferedWages.text = "RM ${selectedJob.wages}"
            binding.employeeOngoingJobDetailsDate.text = "${selectedJob.startDate} to ${selectedJob.endDate}"
            binding.employeeOngoingJobDetailsTime.text = "${selectedJob.startTime} to ${selectedJob.endTime}"
            binding.employeeOngoingJobDetailsJobDesc.text = selectedJob.description
        }

        })
        viewModel.get(jobID)

        viewModel.viewModelScope.launch {
            val employer = viewModel.getEmployerByJobID(jobID)
            binding.employeeOngoingJobDetailsCompanyName.text = employer.employerUsername
        }

    }

    private fun setListeners(){
        binding.employeeMyJobsOngoingJobDetailsBackButton.setOnClickListener {
            finish()
        }
    }

    private fun updateStarColors(starsCount: Int) {
        val stars = arrayOf(
            binding.employeeOngoingJobDetailsStar1,
            binding.employeeOngoingJobDetailsStar2,
            binding.employeeOngoingJobDetailsStar3,
            binding.employeeOngoingJobDetailsStar4,
            binding.employeeOngoingJobDetailsStar5
        )

        for (i in 0..<stars.size) {
            if (i < starsCount) {
                stars[i].setImageResource(R.drawable.ic_star_24px)
                stars[i].setColorFilter(Color.parseColor("#FDB915"))
            } else {
                stars[i].setImageResource(R.drawable.ic_star_hollow_24px)
                stars[i].setColorFilter(Color.parseColor("#000000"))
            }
        }
    }
}