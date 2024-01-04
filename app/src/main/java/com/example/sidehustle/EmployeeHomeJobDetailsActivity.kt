package com.example.sidehustle

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sidehustle.databinding.ActivityEmployeeHomeJobDetailsBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class EmployeeHomeJobDetailsActivity : AppCompatActivity() {
    var JOB_DETAILS_REQUEST_CODE: Int = 88
    lateinit var binding: ActivityEmployeeHomeJobDetailsBinding
    lateinit var job: EntityJob
    lateinit var viewModel: EmployeeHomeJobDetailsViewModel
    var jobID : Long = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_employee_home_job_details)

        viewModel = ViewModelProvider(this).get(EmployeeHomeJobDetailsViewModel::class.java)

        val intent = intent

        setSupportActionBar(binding.employeeHomeJobDetailsToolbar)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_36px)
            setDisplayShowTitleEnabled(false)
        }


        // TODO : CHECK POP WILL GET THE SAME ID OR NOT
        jobID = intent.getLongExtra("jobID", -200)

        getData(jobID)

        setListeners(jobID)

        viewModel.starCount.observe(this) {
            Log.i("AVG", it.toString())
            updateStarColors(it)
        }

        viewModel.viewModelScope.launch {
            binding.employer = viewModel.getEmployerByJobID(jobID)
            viewModel.getAverageRatingByJobIDAndCommenter(binding.employer!!.employerID, "EMPLOYEE")
        }

    }

    private fun setListeners(jobID : Long) {
        binding.employeeHomeJobDetailsApplyButton.setOnClickListener {
            apply(it, jobID)
        }

    }

    private fun apply(view: View, jobID : Long) {
        val intent = Intent(view.context, EmployeeHomeJobDetailsApplyJobsActivity::class.java)
        intent.putExtra("jobID", jobID)
        startActivityForResult((intent),JOB_DETAILS_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == JOB_DETAILS_REQUEST_CODE && resultCode == RESULT_OK) {
            val JOBID = data?.getLongExtra("jobID",-100)
            Toast.makeText(this,"jobID is $JOBID",Toast.LENGTH_SHORT).show()
        }
    }
    private fun getData(jobID: Long) {
        // TODO: RMB GET FROM DATABASE, GET STARS AS WELL AND REQUIREMENTS ALSO
//

        val adapter = EmployeeHomeJobDetailsRequirementAdapter(listOf("Hard Working", "Punctual", "Friendly"))

        binding.employeeHomeJobDetailsRequirementsRecyclerview.adapter = adapter
        binding.employeeHomeJobDetailsRequirementsRecyclerview.layoutManager =
            LinearLayoutManager(this)

        viewModel.selectedJob.observe(this, Observer { selectedJob -> selectedJob?.let{
            binding.employeeJobDetailsCompanyLocation.text = selectedJob.state
            binding.employeeJobDetailsOfferedPosition.text = selectedJob.title
            binding.employeeJobDetailsOfferedWages.text = "RM ${selectedJob.wages}"
            binding.employeeJobDetailsDate.text = "${selectedJob.startDate} to ${selectedJob.endDate}"
            binding.employeeJobDetailsTime.text = "${selectedJob.startTime} to ${selectedJob.endTime}"
            binding.employeeJobDetailsJobDesc.text = selectedJob.description
        }

        })
        viewModel.get(jobID)

    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    private fun updateStarColors(starsCount: Int) {
        val stars = arrayOf(
            binding.employeeJobDetailsStar1,
            binding.employeeJobDetailsStar2,
            binding.employeeJobDetailsStar3,
            binding.employeeJobDetailsStar4,
            binding.employeeJobDetailsStar5
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