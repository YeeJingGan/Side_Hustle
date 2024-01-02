package com.example.sidehustle

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import com.example.sidehustle.databinding.ActivityEmployerMyJobsNegotiatingApplicantDetailsBinding

class EmployerMyJobsNegotiatingApplicantDetailsActivity : AppCompatActivity() {

    lateinit var binding: ActivityEmployerMyJobsNegotiatingApplicantDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employer_my_jobs_negotiating_applicant_details)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_employer_my_jobs_negotiating_applicant_details)

        binding.employerMyJobsNegotiatingApplicantDetailsBackButton.setOnClickListener { finish() }

        binding.rejectApplicantButton.setOnClickListener{
            showConfirmationDialog("REJECT")
        }

        binding.acceptApplicantButton.setOnClickListener{
            showConfirmationDialog("ACCEPT")
        }

        binding.negotiateApplicantButton.setOnClickListener{
            val context = it.context
            val intent = Intent(context, EmployerMyJobsNegotiatingApplicantDetailsNegotiateActivity::class.java)
            startActivity(intent)
        }
    }

    private fun showConfirmationDialog(message : String){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Confirmation")
        builder.setMessage("You are ${message}ing this applicant. Are you sure you want to proceed?")

        builder.setPositiveButton("$message"){
            _, _ ->

            val toast = Toast.makeText(this, "This applicant is ${message}ed", Toast.LENGTH_SHORT)
            toast.show()

            binding.rejectApplicantButton.isEnabled = false
            binding.rejectApplicantButton.visibility = View.GONE
            binding.negotiateApplicantButton.isEnabled = false
            binding.negotiateApplicantButton.visibility = View.GONE
            binding.acceptApplicantButton.isEnabled = false
            binding.acceptApplicantButton.visibility = View.GONE

            //TODO: Remove applicant from list
        }

        builder.setNegativeButton("CANCEL"){ _, _ ->

        }

        builder.show()
    }
}