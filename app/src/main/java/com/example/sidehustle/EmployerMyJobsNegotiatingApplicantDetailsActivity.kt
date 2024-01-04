package com.example.sidehustle

import android.content.Intent
import android.graphics.Color
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


        val intent = intent
        val employeeID = intent.getLongExtra("employeeID",-100)
        val jobID = intent.getLongExtra("jobID",-100)
        val starCount = intent.getIntExtra("starCount",0)

        if(employeeID == -100L || jobID == -100L){
            Toast.makeText(this,"Unknown Error Occured",Toast.LENGTH_SHORT).show()
            finish()
        }

        updateStarColors(starCount)




    }

    private fun updateStarColors(starsCount: Int) {
        val stars = arrayOf(
            binding.applicantsDetailsApplicantStar1,
            binding.applicantsDetailsApplicantStar2,
            binding.applicantsDetailsApplicantStar3,
            binding.applicantsDetailsApplicantStar4,
            binding.applicantsDetailsApplicantStar5
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

    private fun showConfirmationDialog(message : String){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Confirmation")
        builder.setMessage("You are ${message}ING this applicant. Are you sure you want to proceed?")

        builder.setPositiveButton("$message"){
            _, _ ->

            val toast = Toast.makeText(this, "This applicant is ${message}ED", Toast.LENGTH_SHORT)
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