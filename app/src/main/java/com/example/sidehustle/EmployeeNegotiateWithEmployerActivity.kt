package com.example.sidehustle

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.sidehustle.databinding.ActivityEmployeeNegotiateWithEmployerBinding
import com.example.sidehustle.databinding.ActivityEmployerNegotiateWithEmployeeBinding

class EmployeeNegotiateWithEmployerActivity : AppCompatActivity() {
    lateinit var binding: ActivityEmployeeNegotiateWithEmployerBinding
    var wagesAmount: Int = 10
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =
            DataBindingUtil.setContentView(this, R.layout.activity_employee_negotiate_with_employer)

        val intent = intent

        val jobID = intent.getLongExtra("jobID", -100)
        Log.i("JOB ID", jobID.toString())

        setSupportActionBar(binding.employeeNegotiateWithEmployerToolbar)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_36px)
            setDisplayShowTitleEnabled(false)
        }

        updateWages(wagesAmount)

        binding.employeeNegotiateWithEmployerPlusButton.setOnClickListener {
            wagesAmount += 10
            updateWages(wagesAmount)
        }

        binding.employeeNegotiateWithEmployerMinusButton.setOnClickListener {
            if (wagesAmount > 10) {
                wagesAmount -= 10
                updateWages(wagesAmount)
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
        wagesAmount = 10
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