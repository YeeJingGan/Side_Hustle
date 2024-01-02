package com.example.sidehustle

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.sidehustle.databinding.ActivityEmployeeMyJobsNegotiatingJobDetailsBinding

class EmployeeMyJobsNegotiatingJobDetailsActivity : AppCompatActivity() {
    lateinit var binding: ActivityEmployeeMyJobsNegotiatingJobDetailsBinding
    var jobID: Long = -100
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_employee_my_jobs_negotiating_job_details
        )

        val intent = intent
        jobID = intent.getLongExtra("jobID", -100)
        Toast.makeText(this, jobID.toString(), Toast.LENGTH_SHORT).show()

        setListeners()
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
}