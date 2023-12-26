package com.example.sidehustle.activites.employer

import android.content.Intent
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.sidehustle.R
import com.example.sidehustle.Review
import com.example.sidehustle.ReviewsCommentsAdapter
import com.example.sidehustle.activites.employee.EmployeeHomeActivity
import com.example.sidehustle.activites.employee.EmployeeJobDetailsActivity
import com.example.sidehustle.activites.employee.EmployeeMyJobsActivity
import com.example.sidehustle.activites.employee.EmployeeMyProfileActivity
import com.example.sidehustle.activites.employee.EmployeeSettingsActivity
import com.example.sidehustle.activites.employee.EmployeeUploadResumeActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

class EmployerMyProfileActivity : AppCompatActivity() {
    lateinit var viewPager2: ViewPager2
    lateinit var adapter: ReviewsCommentsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employer_my_profile)
        val reviews = createReviewsCommentsList()

        viewPager2 = findViewById(R.id.my_profile_viewpager2)
        adapter = ReviewsCommentsAdapter(reviews)

        viewPager2.adapter = adapter

        setListeners()

        findViewById<BottomNavigationView>(R.id.employer_my_profile_bottom_nav).apply {
            selectedItemId = R.id.bottom_nav_my_profile_button
            setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.bottom_nav_my_profile_button -> false
                    R.id.bottom_nav_my_jobs_button -> {
                        finish()
                        startActivity(
                            Intent(
                                applicationContext,
                                EmployeeMyJobsActivity::class.java
                            )
                        )
                        true
                    }

                    R.id.bottom_nav_home_button-> {
                        finish()
                        startActivity(
                            Intent(
                                applicationContext,
                                EmployeeHomeActivity::class.java
                            )
                        )
                        true
                    }

                    else -> false
                }
            }
        }
    }


    private fun setListeners() {

        findViewById<ImageButton>(R.id.my_profile_settings_icon).setOnClickListener {
            toAnotherActivity(it, EmployerSettingsActivity::class.java)
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