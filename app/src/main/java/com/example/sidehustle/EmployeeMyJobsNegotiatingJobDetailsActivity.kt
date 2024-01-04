package com.example.sidehustle

import android.app.AlertDialog
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
import com.example.sidehustle.databinding.ActivityEmployeeMyJobsNegotiatingJobDetailsBinding
import kotlinx.coroutines.launch

class EmployeeMyJobsNegotiatingJobDetailsActivity : AppCompatActivity() {
    lateinit var binding: ActivityEmployeeMyJobsNegotiatingJobDetailsBinding
    lateinit var viewModel: EmployeeMyJobsNegotiatingJobDetailsViewModel
    var jobID: Long = -100
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_employee_my_jobs_negotiating_job_details
        )
        viewModel = ViewModelProvider(this).get(EmployeeMyJobsNegotiatingJobDetailsViewModel::class.java)
        val intent = intent
        jobID = intent.getLongExtra("jobID", -100)
        Toast.makeText(this, jobID.toString(), Toast.LENGTH_SHORT).show()

        getData(jobID)
        setListeners()

        viewModel.starCount.observe(this) {
            Log.i("AVG", it.toString())
            updateStarColors(it)
        }

        viewModel.viewModelScope.launch {
            binding.employer = viewModel.getEmployerByJobID(jobID)
            viewModel.getAverageRatingByJobIDAndCommenter(binding.employer!!.employerID, "EMPLOYEE")
        }
    }

    private fun setListeners() {
        binding.employeeMyJobsNegotiatingJobDetailsBackButton.setOnClickListener {
            finish()
        }
        binding.rejectOfferButton.setOnClickListener {
            showRejectConfirmationDialog()
        }
        binding.acceptOfferButton.setOnClickListener {
            showAcceptConfirmationDialog()
        }
        binding.negotiateOfferButton.setOnClickListener {
            val intent =
                Intent(this, EmployeeMyJobsNegotiatingJobDetailsNegotiateActivity::class.java)
            intent.putExtra("jobID", jobID)
            startActivity(intent)
        }
    }

    private fun showRejectConfirmationDialog() {
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setTitle("Reject Confirmation")
        dialogBuilder.setMessage("Are you sure you want to reject? This can't be undone")
        dialogBuilder.setPositiveButton("Reject") { dialog, which ->
            reject()
        }
        dialogBuilder.setNegativeButton("Cancel") { dialog, which ->
            dialog.dismiss()
        }
        dialogBuilder.create().show()
    }

    private fun reject() {
        // TODO : DATABASE LOGICS HERE
        Toast.makeText(this, "HAHA HVNT REJECT GT DATABSE NOT YET", Toast.LENGTH_SHORT).show()
    }

    private fun showAcceptConfirmationDialog() {
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setTitle("Accept Confirmation")
        dialogBuilder.setMessage("Are you sure you want to accept? This can't be undone")
        dialogBuilder.setPositiveButton("Accept") { dialog, which ->
            accept()
        }
        dialogBuilder.setNegativeButton("Cancel") { dialog, which ->
            dialog.dismiss()
        }
        dialogBuilder.create().show()
    }

    private fun accept() {
        // TODO : DATABASE LOGICS HERE
        Toast.makeText(this, "HAHA HVNT ACCCEPT GT DATABSE NOT YET", Toast.LENGTH_SHORT).show()
    }

    private fun getData(jobID: Long) {
        // TODO: RMB GET FROM DATABASE, GET STARS AS WELL AND REQUIREMENTS ALSO
//

        val adapter = EmployeeHomeJobDetailsRequirementAdapter(listOf("REQ 1", "REQ 2", "REQ 3"))

        binding.employeeMyJobsNegotiatingJobDetailsRecyclerView.adapter = adapter
        binding.employeeMyJobsNegotiatingJobDetailsRecyclerView.layoutManager =
            LinearLayoutManager(this)

        viewModel.selectedJob.observe(this, Observer { selectedJob -> selectedJob?.let{
            binding.employeeNegotiatingJobDetailsCompanyLocation.text = selectedJob.state
            binding.employeeNegotiatingJobDetailsOfferedPosition.text = selectedJob.title
            binding.employeeNegotiatingJobDetailsOfferedWages.text = "RM ${selectedJob.wages}"
            binding.employeeNegotiatingJobDetailsDate.text = "${selectedJob.startDate} to ${selectedJob.endDate}"
            binding.employeeNegotiatingJobDetailsTime.text = "${selectedJob.startTime} to ${selectedJob.endTime}"
            binding.employeeNegotiatingJobDetailsJobDesc.text = selectedJob.description
        }

        })
        viewModel.get(jobID)

    }

    private fun updateStarColors(starsCount: Int) {
        val stars = arrayOf(
            binding.employeeNegotiatingJobDetailsStar1,
            binding.employeeNegotiatingJobDetailsStar2,
            binding.employeeNegotiatingJobDetailsStar3,
            binding.employeeNegotiatingJobDetailsStar4,
            binding.employeeNegotiatingJobDetailsStar5
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

