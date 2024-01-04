package com.example.sidehustle

import android.app.AlertDialog
import android.content.Intent
import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sidehustle.databinding.ActivityEmployeeHomeJobDetailsApplyJobsBinding
import kotlinx.coroutines.launch

class EmployeeHomeJobDetailsApplyJobsActivity : AppCompatActivity() {

    lateinit var binding: ActivityEmployeeHomeJobDetailsApplyJobsBinding
    lateinit var languageAdapter: EmployeeHomeJobDetailsApplyJobsLanguageAdapter
    lateinit var viewModel: EmployeeHomeJobDetailsApplyJobsViewModel
    var finalJobID: Long = -100
    var wagesAmount: Int = 20
    var finalWages: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_employee_home_job_details_apply_jobs
        )
        viewModel =
            ViewModelProvider(this).get(EmployeeHomeJobDetailsApplyJobsViewModel::class.java)

        val intent = intent
        val jobID = intent.getLongExtra("jobID", -100)
        Toast.makeText(this, "JOBID is $jobID", Toast.LENGTH_SHORT).show()

        finalJobID = jobID

        getData(finalJobID)


    }

    private fun setupScreen(wagesAmount: Int) {
        setSupportActionBar(binding.employeeHomeApplyJobsToolbar)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_36px)
            setDisplayShowTitleEnabled(false)
        }
        updateWages(wagesAmount)

    }

    private fun getData(finalJobID: Long) {
        // TODO : GET DATA HERE
        viewModel.selectedJob.observe(this, Observer { selectedJob ->
            selectedJob?.let {
                wagesAmount = selectedJob.wages
                finalWages =wagesAmount
                setupScreen(wagesAmount)
                setRecyclerView()
                setListeners()
            }
        })
        viewModel.get(finalJobID)

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
        // TODO : UPLOAD TO DATABASE HERE
        //job,comment,pay,negotiator
        viewModel.viewModelScope.launch {
            val employee = viewModel.getEmployee(2)
            if (employee != null) {
                val application = EntityApplication(
                    2,
                    finalJobID,
                    "NEGOTIATING"
                )
                viewModel.insertApplication(application)
                val negotiation = EntityNegotiation(
                    0,
                    2,
                    finalJobID,
                    finalWages,
                    binding.jobApplicationComment.toString(),
                    "EMPLOYEE"
                )
                viewModel.insertNegotiation(negotiation)
                Toast.makeText(this@EmployeeHomeJobDetailsApplyJobsActivity, "Application sent", Toast.LENGTH_SHORT)
                    .show()
            } else {
                Toast.makeText(applicationContext, "An unknown error occured", Toast.LENGTH_SHORT)
                    .show()
            }
                setResult(RESULT_OK, Intent().putExtra("jobID", finalJobID))

        }
        binding.employeeApplicationApplyButton.visibility = View.GONE
        finish()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed() // This will navigate back and finish the activity
                return true
            }

            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        setResult(RESULT_OK, Intent().putExtra("jobID", finalJobID))
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

    private fun reset() {
        currentFocus?.let {
            it.clearFocus()
            hideSoftInput(it)
        }

        binding.apply {
            employerHomeApplyJobsScrollview.fullScroll(View.FOCUS_UP)
            jobApplicationComment.text = null
            updateWages(wagesAmount)
            languageAdapter.removeAllLanguages()
            employeeApplicationMinusLanguageButton.visibility = View.GONE
        }
    }

    private fun setRecyclerView() {
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
            finalWages += 10
            updateWages(finalWages)
        }
        binding.employeeApplicationMinusWagesButton.setOnClickListener {
            if (finalWages > wagesAmount) {
                finalWages -= 10
                updateWages(finalWages)
            }
        }
        binding.employeeApplicationAddLanguageButton.setOnClickListener {
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

    private fun updateWages(amount: Int) {
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