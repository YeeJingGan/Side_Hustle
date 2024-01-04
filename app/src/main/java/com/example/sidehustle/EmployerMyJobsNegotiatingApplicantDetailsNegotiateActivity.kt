package com.example.sidehustle

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.sidehustle.databinding.ActivityEmployerMyJobsNegotiatingApplicantDetailsNegotiateBinding
import kotlinx.coroutines.launch

class EmployerMyJobsNegotiatingApplicantDetailsNegotiateActivity : AppCompatActivity() {
    lateinit var viewModel: EmployerMyJobsNegotiatingApplicantDetailsNegotiateViewModel
    lateinit var binding: ActivityEmployerMyJobsNegotiatingApplicantDetailsNegotiateBinding
    var wagesAmount: Int = 10
    lateinit var job: EntityJob
    var employeeID = -100L
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =
            DataBindingUtil.setContentView(
                this,
                R.layout.activity_employer_my_jobs_negotiating_applicant_details_negotiate
            )

        binding.employerMyJobsNegotiatingApplicantDetailsNegotiateBackButton.setOnClickListener { finish() }

        updateWages(wagesAmount)

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(EmployerMyJobsNegotiatingApplicantDetailsNegotiateViewModel::class.java)

        val intent = intent
        val jobID = intent.getLongExtra("jobID", -100)
        employeeID = intent.getLongExtra("employeeID", -100)

        if (jobID == -100L || employeeID == -100L) {
            Toast.makeText(this, "Unknown Error Occured", Toast.LENGTH_SHORT).show()
            finish()
        }
        viewModel.viewModelScope.launch {
            job = viewModel.getByJobID(jobID)
            wagesAmount = job.wages
            updateWages(wagesAmount)
        }

        binding.employerNegotiateWithEmployeePlusButton.setOnClickListener {
            wagesAmount += 10
            updateWages(wagesAmount)
        }

        binding.employerNegotiateWithEmployeeMinusButton.setOnClickListener {
            if (wagesAmount > job.wages) {
                wagesAmount -= 10
                updateWages(wagesAmount)
            }
        }

        binding.employerNegotiateWithEmployeeResetButton.setOnClickListener {
            showResetConfirmationDialog()
        }

        binding.employerNegotiateWithEmployeeOfferButton.setOnClickListener {
            if (validateAllFields()) {
                showOfferConfirmationDialog()
            }
        }

    }

    private fun updateWages(amount: Int) {
        binding.employerNegotiateWithEmployeeWagesInput.text = "RM $amount"
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

        binding.employerNegotiateWithEmployeeCommentInput.text = null
        wagesAmount = 10
        updateWages(wagesAmount)
    }

    private fun validateAllFields(): Boolean {
        currentFocus?.let {
            it.clearFocus()
            hideSoftInput(it)
        }

        if (binding.employerNegotiateWithEmployeeCommentInput.text.isNullOrEmpty()) {
            Toast.makeText(this, "Comment is empty", Toast.LENGTH_SHORT).show()
            binding.employerNegotiateWithEmployeeCommentInput.apply {
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

        viewModel.viewModelScope.launch {
            val comment = binding.employerNegotiateWithEmployeeCommentInput.text.toString()
            Log.i("SADASHDHAS",job.jobID.toString()+employeeID.toString()+comment)
            viewModel.insert(
                EntityNegotiation(0, employeeID,job.jobID, wagesAmount, comment, "EMPLOYER")
            )
        }
        Toast.makeText(this, "Job Offered", Toast.LENGTH_SHORT).show()
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