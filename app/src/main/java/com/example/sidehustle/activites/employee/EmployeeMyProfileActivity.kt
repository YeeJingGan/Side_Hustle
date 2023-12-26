package com.example.sidehustle.activites.employee

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageButton
import androidx.viewpager2.widget.ViewPager2
import com.example.sidehustle.R
import com.example.sidehustle.Review
import com.example.sidehustle.ReviewsCommentsAdapter

class EmployeeMyProfileActivity : AppCompatActivity() {

    lateinit var viewPager2: ViewPager2
    lateinit var adapter: ReviewsCommentsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employee_my_profile)

        val reviews = createReviewsCommentsList()

        viewPager2 = findViewById(R.id.my_profile_viewpager2)
        adapter = ReviewsCommentsAdapter(reviews)

        viewPager2.adapter = adapter

        setListeners()

    }

    private fun setListeners() {
        findViewById<ImageButton>(R.id.my_jobs_button).setOnClickListener {
            finish()
            toAnotherActivity(it, EmployeeJobDetailsActivity::class.java)
        }

        findViewById<ImageButton>(R.id.home_button).setOnClickListener {
            finish()
            toAnotherActivity(it, EmployeeHomeActivity::class.java)
        }

        findViewById<ImageButton>(R.id.my_profile_settings_icon).setOnClickListener {
            toAnotherActivity(it, EmployeeSettingsActivity::class.java)
        }

        val previousButton = findViewById<ImageButton>(R.id.my_profile_previous_review_button)
        val nextButton = findViewById<ImageButton>(R.id.my_profile_next_review_button)
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

        previousButton.setOnClickListener {
            if (viewPager2.currentItem > 0) {
                viewPager2.currentItem -= 1
            }
            if (viewPager2.currentItem < adapter.itemCount - 1) {
                nextButton.visibility = View.VISIBLE
            }
            if (viewPager2.currentItem == 0) {
                it.visibility = View.INVISIBLE
            }
        }
    }

    private fun createReviewsCommentsList(): List<Review> {
        return listOf(
            Review(R.drawable.sample_profile_photo, "Employer 1", 5, "Content 1"),
            Review(R.drawable.sample_profile_photo, "Employer 2", 4, "Content 2"),
            Review(R.drawable.sample_profile_photo, "Employer 3", 3, "Content 3"),
            Review(R.drawable.sample_profile_photo, "Employer 4", 2, "Content 4"),
        )
    }

    private fun toAnotherActivity(view: View, destinationActivity: Class<*>) {
        view.context.also {
            val intent = Intent(it, destinationActivity)
            it.startActivity(intent)
        }
    }
    override fun onBackPressed() {
        super.onBackPressed()
        finishAffinity()
    }
}