package com.example.sidehustle

import android.app.AlertDialog
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.sidehustle.databinding.ActivityEmployerMyJobsHistoryEmployeeListCommentRatingBinding

class EmployerMyJobsHistoryEmployeeListCommentRatingActivity : AppCompatActivity() {

    lateinit var binding: ActivityEmployerMyJobsHistoryEmployeeListCommentRatingBinding
    var starsCount: Int = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(
            this,
            R.layout.activity_employer_my_jobs_history_employee_list_comment_rating
        )

        setSupportActionBar(binding.employerCommentRatingToolbar)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_arrow_back_white_36px)
            setDisplayShowTitleEnabled(false)
        }

        binding.employerCommentRatingResetButton.setOnClickListener {
            showResetConfirmationDialog()
        }

        binding.employerCommentRatingSubmitButton.setOnClickListener {
            if (validateAllFields()) {
                showSubmitConfirmationDialog()
            }
        }

        binding.employerCommentRatingStar1.setOnClickListener {
            onStarClicked(binding.employerCommentRatingStar1)
        }
        binding.employerCommentRatingStar2.setOnClickListener {
            onStarClicked(binding.employerCommentRatingStar2)
        }
        binding.employerCommentRatingStar3.setOnClickListener {
            onStarClicked(binding.employerCommentRatingStar3)
        }
        binding.employerCommentRatingStar4.setOnClickListener {
            onStarClicked(binding.employerCommentRatingStar4)
        }
        binding.employerCommentRatingStar5.setOnClickListener {
            onStarClicked(binding.employerCommentRatingStar5)
        }
    }

    private fun onStarClicked(imageView: ImageView) {
        starsCount = imageView.tag.toString().toInt()
        updateStarColors(starsCount)
    }

    private fun updateStarColors(starsCount: Int) {
        val stars = arrayOf(
            binding.employerCommentRatingStar1,
            binding.employerCommentRatingStar2,
            binding.employerCommentRatingStar3,
            binding.employerCommentRatingStar4,
            binding.employerCommentRatingStar5
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

        binding.employerCommentRatingTvComment.text = null
        starsCount = 1
        updateStarColors(starsCount)
    }

    private fun validateAllFields(): Boolean {
        currentFocus?.let {
            it.clearFocus()
            hideSoftInput(it)
        }

        if (binding.employerCommentRatingTvComment.text.isNullOrEmpty()) {
            Toast.makeText(this, "Comment is empty", Toast.LENGTH_SHORT).show()
            binding.employerCommentRatingTvComment.apply {
                requestFocus()
                showSoftInput(this)
            }
        } else {
            return true
        }
        return false
    }

    private fun showSubmitConfirmationDialog() {
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setTitle("Submit Confirmation")
        dialogBuilder.setMessage("Are you sure you want to submit? This can't be undone")
        dialogBuilder.setPositiveButton("Offer") { dialog, which ->
            submit()
        }
        dialogBuilder.setNegativeButton("Cancel") { dialog, which ->
            dialog.dismiss()
        }
        dialogBuilder.create().show()
    }

    private fun submit() {
        // TODO: UPLOAD DATABASE HERE
        Toast.makeText(this, "HAHA NOT YET SUBMITTED NEED TO DATABASE", Toast.LENGTH_SHORT).show()
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