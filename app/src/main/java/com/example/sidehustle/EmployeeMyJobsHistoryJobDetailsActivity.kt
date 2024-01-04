package com.example.sidehustle

import android.content.Intent
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
import com.example.sidehustle.databinding.ActivityEmployeeHomeJobDetailsBinding
import com.example.sidehustle.databinding.ActivityEmployeeMyJobsHistoryJobDetailsBinding
import kotlinx.coroutines.launch

class EmployeeMyJobsHistoryJobDetailsActivity : AppCompatActivity() {

    lateinit var binding : ActivityEmployeeMyJobsHistoryJobDetailsBinding
    lateinit var viewModel: EmployeeMyJobsHistoryJobDetailsViewModel
    var jobID : Long = -100
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_employee_my_jobs_history_job_details)
        viewModel = ViewModelProvider(this).get(EmployeeMyJobsHistoryJobDetailsViewModel::class.java)
        val intent = intent
        jobID = intent.getLongExtra("jobID",-100)
        Toast.makeText(this,jobID.toString(),Toast.LENGTH_SHORT).show()

        setListeners()

        getData(jobID)

        viewModel.starCount.observe(this) {
            Log.i("AVG", it.toString())
            updateStarColors(it)
        }

        viewModel.viewModelScope.launch {
            binding.employer = viewModel.getEmployerByJobID(jobID)
            viewModel.getAverageRatingByJobIDAndCommenter(binding.employer!!.employerID, "EMPLOYEE")
        }
    }

    private fun setListeners(){
        binding.employeeMyJobsHistoryJobDetailsBackButton.setOnClickListener {
            finish()
        }
        binding.employeeHistoryCommentButton.setOnClickListener {
            val intent = Intent(this,EmployeeMyJobsHistoryJobDetailsCommentRatingActivity::class.java)
            intent.putExtra("jobID",jobID)
            startActivity(intent)
        }
    }

    private fun getData(jobID: Long) {
        // TODO: RMB GET FROM DATABASE, GET STARS AS WELL AND REQUIREMENTS ALSO
//

        val adapter = EmployeeHomeJobDetailsRequirementAdapter(listOf("Hard Working", "Punctual", "Friendly"))

        binding.employeeMyJobsHistoryJobDetailsRecyclerview.adapter = adapter
        binding.employeeMyJobsHistoryJobDetailsRecyclerview.layoutManager =
            LinearLayoutManager(this)

        viewModel.selectedJob.observe(this, Observer { selectedJob -> selectedJob?.let{
            binding.employeeHistoryPendingCommentCompanyLocation.text = selectedJob.state
            binding.employeeHistoryPendingCommentOfferedPosition.text = selectedJob.title
            binding.employeeHistoryPendingCommentOfferedWages.text = "RM ${selectedJob.wages}"
            binding.employeeHistoryPendingCommentDate.text = "${selectedJob.startDate} to ${selectedJob.endDate}"
            binding.employeeHistoryPendingCommentTime.text = "${selectedJob.startTime} to ${selectedJob.endTime}"
            binding.employeeHistoryPendingCommentJobDesc.text = selectedJob.description
        }

        })
        viewModel.get(jobID)

    }

    private fun updateStarColors(starsCount: Int) {
        val stars = arrayOf(
            binding.employeeHistoryPendingCommentStar1,
            binding.employeeHistoryPendingCommentStar2,
            binding.employeeHistoryPendingCommentStar3,
            binding.employeeHistoryPendingCommentStar4,
            binding.employeeHistoryPendingCommentStar5
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