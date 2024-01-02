package com.example.sidehustle

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.sidehustle.databinding.ActivityEmployerMyJobsNegotiatingApplicantDetailsNegotiateBinding

class EmployerMyJobsNegotiatingApplicantDetailsNegotiateActivity : AppCompatActivity() {

    lateinit var binding: ActivityEmployerMyJobsNegotiatingApplicantDetailsNegotiateBinding
    var wagesAmount: Int = 10
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =
            DataBindingUtil.setContentView(this, R.layout.activity_employer_my_jobs_negotiating_applicant_details_negotiate)

        setSupportActionBar(binding.employerNegotiateWithEmployeeToolbar)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_36px)
            setDisplayShowTitleEnabled(false)
        }

        updateWages(wagesAmount)

        binding.employerNegotiateWithEmployeePlusButton.setOnClickListener {
            wagesAmount += 10
            updateWages(wagesAmount)
        }

        binding.employerNegotiateWithEmployeeMinusButton.setOnClickListener {
            if (wagesAmount > 10) {
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
        Toast.makeText(this, "HAHA NOT YET OFFERED NEED TO DATABASE", Toast.LENGTH_SHORT).show()
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