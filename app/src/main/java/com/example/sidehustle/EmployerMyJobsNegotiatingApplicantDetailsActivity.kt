package com.example.sidehustle

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sidehustle.databinding.ActivityEmployerMyJobsNegotiatingApplicantDetailsBinding
import kotlinx.coroutines.launch

class EmployerMyJobsNegotiatingApplicantDetailsActivity : AppCompatActivity() {

    lateinit var binding: ActivityEmployerMyJobsNegotiatingApplicantDetailsBinding
    lateinit var viewModel: EmployerMyJobsNegotiatingApplicantDetailsViewModel
    var starCount: Int = 0
    lateinit var employee: EntityEmployee
    lateinit var job: EntityJob
    lateinit var negotiation: EntityNegotiation
    lateinit var languages: List<EntityLanguage>
    lateinit var ratings: List<EntityRating>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_employer_my_jobs_negotiating_applicant_details
        )

        binding.employerMyJobsNegotiatingApplicantDetailsBackButton.setOnClickListener { finish() }

        binding.rejectApplicantButton.setOnClickListener {
            showConfirmationDialog("REJECT")
        }

        binding.acceptApplicantButton.setOnClickListener {
            showConfirmationDialog("ACCEPT")
        }

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(EmployerMyJobsNegotiatingApplicantDetailsViewModel::class.java)

        val intent = intent
        val employeeID = intent.getLongExtra("employeeID", -100)
        val jobID = intent.getLongExtra("jobID", -100)
        starCount = intent.getIntExtra("starCount", 0)

        if (employeeID == -100L || jobID == -100L) {
            Toast.makeText(this, "Unknown Error Occured", Toast.LENGTH_SHORT).show()
            finish()
        }

        updateStarColors(starCount)

        binding.employerMyJobsNegotiatingAppplicantDetailsRecyclerview.adapter =
            EmployerMyJobsNegotiatingApplicantDetailsAdapter(
                emptyList()
            )
        binding.employerMyJobsNegotiatingAppplicantDetailsRecyclerview.layoutManager =
            LinearLayoutManager(this)

        binding.employerMyJobsNegotiatingApplicantDetailsViewpager.adapter =
            EmployerNegotiatingApplicantsDetailsReviewsCommentsAdapter(
                emptyList(), viewModel
            )

        viewModel.viewModelScope.launch {
            employee = viewModel.getEmployeeByEmployeeID(employeeID)!!
            binding.employee = employee
            job = viewModel.getByJobID(jobID)
            binding.job = job
            negotiation = viewModel.getLatestNegotiationByEmployeeIDAndJobID(employeeID, jobID)
            binding.negotiation = negotiation

            if (negotiation.negotiator.contains("EMPLOYER")) {
                binding.applicantsDetailsCommentHeader.text = "Your Comment"
                binding.rejectApplicantButton.isEnabled = false
                binding.rejectApplicantButton.visibility = View.GONE
                binding.negotiateApplicantButton.isEnabled = false
                binding.negotiateApplicantButton.visibility = View.GONE
                binding.acceptApplicantButton.isEnabled = false
                binding.acceptApplicantButton.visibility = View.GONE
            }

            languages = viewModel.getByEmployeeIDAndJobID(employeeID, jobID)

            binding.employerMyJobsNegotiatingAppplicantDetailsRecyclerview.adapter =
                EmployerMyJobsNegotiatingApplicantDetailsAdapter(
                    languages
                )

            ratings = viewModel.getRatingByEmployeeIDAndCommenter(employeeID, "EMPLOYER")
            val adapter = EmployerNegotiatingApplicantsDetailsReviewsCommentsAdapter(
                ratings, viewModel
            )
            binding.employerMyJobsNegotiatingApplicantDetailsViewpager.adapter = adapter

            val previousButton = binding.employerMyJobsNegotiatingApplicantDetailsPreviousButton
            val nextButton = binding.employerMyJobsNegotiatingApplicantDetailsNextButton
            val viewPager2 = binding.employerMyJobsNegotiatingApplicantDetailsViewpager

            if (ratings.size < 2) {
                nextButton.visibility = View.INVISIBLE
            }
            nextButton.setOnClickListener {
                if (viewPager2.currentItem < adapter.itemCount - 1) {
                    viewPager2.currentItem += 1
                }
                if (viewPager2.currentItem > 0) {
                    previousButton.visibility = View.VISIBLE
                }
                if (viewPager2.currentItem == adapter.itemCount - 1) {
                    it.visibility = View.INVISIBLE
                }
            }

            val application = viewModel.getApplicationByEmployeeIDAndJobID(employeeID, jobID)
            if (application.status != "NEGOTIATING"){
                binding.rejectApplicantButton.isEnabled = false
                binding.rejectApplicantButton.visibility = View.GONE
                binding.negotiateApplicantButton.isEnabled = false
                binding.negotiateApplicantButton.visibility = View.GONE
                binding.acceptApplicantButton.isEnabled = false
                binding.acceptApplicantButton.visibility = View.GONE
            }

        }

        binding.negotiateApplicantButton.setOnClickListener {
            val context = it.context
            val intentComment = Intent(
                context,
                EmployerMyJobsNegotiatingApplicantDetailsNegotiateActivity::class.java
            )
            intentComment.putExtra("jobID",job.jobID)
            intentComment.putExtra("employeeID",employee.employeeID)
            startActivity(intentComment)
        }
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

    private fun showConfirmationDialog(message: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Confirmation")
        builder.setMessage("You are ${message}ING this applicant. Are you sure you want to proceed?")

        builder.setPositiveButton("$message") { _, _ ->

            val toast = Toast.makeText(this, "This applicant is ${message}ED", Toast.LENGTH_SHORT)
            toast.show()

            binding.rejectApplicantButton.isEnabled = false
            binding.rejectApplicantButton.visibility = View.GONE
            binding.negotiateApplicantButton.isEnabled = false
            binding.negotiateApplicantButton.visibility = View.GONE
            binding.acceptApplicantButton.isEnabled = false
            binding.acceptApplicantButton.visibility = View.GONE


            updateStatus(message)

            //TODO: Remove applicant from list
        }

        builder.setNegativeButton("CANCEL") { _, _ ->

        }

        builder.show()
    }

    private fun updateStatus(message: String){
        viewModel.viewModelScope.launch {
            viewModel.updateStatus(employee.employeeID, job.jobID, "${message}ED")
            if ("${message}ED".contains("REJECTED")) {
                finish()
            }
        }
    }
}