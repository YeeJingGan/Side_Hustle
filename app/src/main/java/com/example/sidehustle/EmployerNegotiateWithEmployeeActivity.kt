package com.example.sidehustle

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.sidehustle.databinding.ActivityEmployerHomeUploadJobsBinding
import com.example.sidehustle.databinding.ActivityEmployerNegotiateWithEmployeeBinding

class EmployerNegotiateWithEmployeeActivity : AppCompatActivity() {

    lateinit var binding: ActivityEmployerNegotiateWithEmployeeBinding
    var wagesAmount: Int = 10
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =
            DataBindingUtil.setContentView(this, R.layout.activity_employer_negotiate_with_employee)

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

    private fun validateAllFields(){
        currentFocus?.let {
            it.clearFocus()
            hideSoftInput(it)
        }

        if(binding.employerNegotiateWithEmployeeCommentInput.text.isNullOrEmpty()){
            Toast.makeText(this,"Comment is empty",Toast.LENGTH_SHORT).show()
            binding.employerNegotiateWithEmployeeCommentInput.apply {
                requestFocus()
                showSoftInput(this)
            }
        }else{
            // TODO : SET HERE NOT LESS THAN INITIAL AMOUNT
        }
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