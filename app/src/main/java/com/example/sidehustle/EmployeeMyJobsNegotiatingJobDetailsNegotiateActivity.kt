package com.example.sidehustle

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.sidehustle.databinding.ActivityEmployeeMyJobsNegotiatingJobDetailsNegotiateBinding

class EmployeeMyJobsNegotiatingJobDetailsNegotiateActivity : AppCompatActivity() {
    lateinit var binding: ActivityEmployeeMyJobsNegotiatingJobDetailsNegotiateBinding
    lateinit var viewModel: EmployeeMyJobsNegotiatingNegotiateViewModel
    var wagesAmount: Int = 10
    var finalWages: Int = 0
    var jobID: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =
            DataBindingUtil.setContentView(this, R.layout.activity_employee_my_jobs_negotiating_job_details_negotiate)
        viewModel =
            ViewModelProvider(this).get(EmployeeMyJobsNegotiatingNegotiateViewModel::class.java)
        val intent = intent

        jobID = intent.getLongExtra("jobID", -100)
        Log.i("JOB ID", jobID.toString())

        viewModel.selectedJob.observe(this, Observer { selectedJob ->
            selectedJob?.let {
                wagesAmount = selectedJob.wages
                finalWages =wagesAmount
                updateWages(wagesAmount)
            }
        })
        viewModel.get(jobID)


        binding.employeeNegotiateWithEmployerPlusButton.setOnClickListener {
            finalWages += 10
            updateWages(finalWages)
        }

        binding.employeeNegotiateWithEmployerMinusButton.setOnClickListener {
            if (finalWages > wagesAmount) {
                finalWages -= 10
                updateWages(finalWages)
            }
        }

        binding.employeeNegotiateWithEmployerResetButton.setOnClickListener {
            showResetConfirmationDialog()
        }

        binding.employeeNegotiateWithEmployerOfferButton.setOnClickListener {
            if (validateAllFields()) {
                showOfferConfirmationDialog()
            }
        }

        binding.employeeNegotiateBackButton.setOnClickListener {
            finish()
        }

    }

    private fun updateWages(amount: Int) {
        binding.employeeNegotiateWithEmployerWagesInput.text = "RM $amount"
    }

    private fun showResetConfirmationDialog() {
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setTitle("Reset Confirmation")
        dialogBuilder.setMessage("Are you sure you want to reset? This can't be undone")
        dialogBuilder.setPositiveButton("Reset") { dialog, which ->
            reset()
        }
        dialogBuilder.setNegativeButton("Cancel") { dialog, which ->
            dialog.dismiss()
        }
        dialogBuilder.create().show()
    }

    private fun reset() {
        currentFocus?.let {
            it.clearFocus()
            hideSoftInput(it)
        }

        binding.employeeNegotiateWithEmployerCommentInput.text = null
        updateWages(wagesAmount)
    }

    private fun validateAllFields(): Boolean {
        currentFocus?.let {
            it.clearFocus()
            hideSoftInput(it)
        }

        if (binding.employeeNegotiateWithEmployerCommentInput.text.isNullOrEmpty()) {
            Toast.makeText(this, "Comment is empty", Toast.LENGTH_SHORT).show()
            binding.employeeNegotiateWithEmployerCommentInput.apply {
                requestFocus()
                showSoftInput(this)
            }
        } else {
            // TODO : SET HERE ELSE IF , NOT LESS THAN INITIAL AMOUNT
            return true
        }
        return false
    }

    private fun showOfferConfirmationDialog() {
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setTitle("Offer Confirmation")
        dialogBuilder.setMessage("Are you sure you want to offer? This can't be undone")
        dialogBuilder.setPositiveButton("Offer") { dialog, which ->
            offer()
        }
        dialogBuilder.setNegativeButton("Cancel") { dialog, which ->
            dialog.dismiss()
        }
        dialogBuilder.create().show()
    }

    private fun offer() {
        // TODO: UPLOAD DATABASE HERE
        val negotiation = EntityNegotiation(
            0,
            2,
            jobID,
            finalWages,
            binding.employeeNegotiateWithEmployerCommentInput.toString(),
            "EMPLOYEE"
        )
        viewModel.insertNegotiation(negotiation)
        finish()
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