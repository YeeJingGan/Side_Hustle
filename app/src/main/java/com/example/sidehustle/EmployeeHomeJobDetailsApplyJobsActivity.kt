package com.example.sidehustle

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sidehustle.databinding.ActivityEmployeeHomeJobDetailsApplyJobsBinding

class EmployeeHomeJobDetailsApplyJobsActivity : AppCompatActivity() {

    lateinit var binding: ActivityEmployeeHomeJobDetailsApplyJobsBinding
    var wagesAmount : Int = 10
    lateinit var languageAdapter : EmployeeHomeJobDetailsApplyJobsLanguageAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_employee_home_job_details_apply_jobs
        )

        val intent = intent
        val jobID = intent.getLongExtra("jobID", -100)
        Toast.makeText(this, "JOBID is $jobID", Toast.LENGTH_SHORT).show()

        setupScreen()

        getData()

        setRecyclerView()

        setListeners()
    }

    private fun setupScreen() {
        setSupportActionBar(binding.employeeHomeApplyJobsToolbar)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_36px)
            setDisplayShowTitleEnabled(false)
        }

        updateWages(wagesAmount)

    }

    private fun getData() {
        // TODO : GET DATA HERE
        return
    }

    private fun validateAllFields(): Boolean {
        currentFocus?.let {
            it.clearFocus()
            hideSoftInput(it)
        }

        if (binding.jobApplicationComment.text.isNullOrEmpty()) {
            Toast.makeText(this, "Comment is empty", Toast.LENGTH_SHORT).show()
            binding.jobApplicationComment.apply {
                requestFocus()
                showSoftInput(this)
            }
        } else {
            return true
        }
        return false
    }

    private fun showApplyConfirmationButton() {
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setTitle("Apply Confirmation")
        dialogBuilder.setMessage("Are you sure you want to apply? This can't be undone")
        dialogBuilder.setPositiveButton("Apply") { dialog, which ->
            apply()
        }
        dialogBuilder.setNegativeButton("Cancel") { dialog, which ->
            dialog.dismiss()
        }
        dialogBuilder.create().show()
    }

    private fun apply() {
        Toast.makeText(this, "HAHA NOT YET APPLY NEED DATABASE", Toast.LENGTH_SHORT).show()
        // TODO : UPLOAD TO DATABASE HERE
        finish()
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

    private fun reset(){
        currentFocus?.let {
            it.clearFocus()
            hideSoftInput(it)
        }

        binding.apply {
            employerHomeApplyJobsScrollview.fullScroll(View.FOCUS_UP)
            jobApplicationComment.text = null
            wagesAmount = 10
            updateWages(wagesAmount)
            languageAdapter.removeAllLanguages()
            employeeApplicationMinusLanguageButton.visibility = View.GONE
        }
    }

    private fun setRecyclerView(){
        languageAdapter = EmployeeHomeJobDetailsApplyJobsLanguageAdapter(mutableListOf(""))
        binding.employeeHomeApplyJobsRecyclerview.adapter = languageAdapter
        binding.employeeHomeApplyJobsRecyclerview.layoutManager = LinearLayoutManager(this)
    }

    private fun setListeners() {
        binding.employeeApplicationApplyButton.setOnClickListener {
            if (validateAllFields()) {
                showApplyConfirmationButton()
            }
        }
        binding.employeeApplicationResetButton.setOnClickListener {
            showResetConfirmationDialog()
        }
        binding.employeeApplicationAddWagesButton.setOnClickListener {
            wagesAmount += 10
            updateWages(wagesAmount)
        }
        binding.employeeApplicationMinusWagesButton.setOnClickListener {
            if(wagesAmount > 10){
                wagesAmount -= 10
                updateWages(wagesAmount)
            }
        }
        binding.employeeApplicationAddLanguageButton.setOnClickListener{
            languageAdapter.addLanguage()
            if (languageAdapter.itemCount > 1) {
                binding.employeeApplicationMinusLanguageButton.visibility = View.VISIBLE
            }
        }
        binding.employeeApplicationMinusLanguageButton.setOnClickListener {
            if (languageAdapter.itemCount > 1) {
                languageAdapter.removeLanguage(binding.employeeHomeApplyJobsRecyclerview)
            }
            if (languageAdapter.itemCount == 1) {
                binding.employeeApplicationMinusLanguageButton.visibility = View.GONE
            }
        }
    }

    private fun updateWages(amount: Int){
        binding.employeeApplicationExpectedPay.text = "RM ${amount}"
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