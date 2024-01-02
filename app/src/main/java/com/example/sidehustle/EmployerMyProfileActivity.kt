package com.example.sidehustle

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.bottomnavigation.BottomNavigationView

class EmployerMyProfileActivity : AppCompatActivity() {
    lateinit var viewPager2: ViewPager2
    lateinit var adapter: EmployeeMyProfileReviewsCommentsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_employer_my_profile)
        val reviews = createReviewsCommentsList()

        viewPager2 = findViewById(R.id.my_profile_viewpager2)
        adapter = EmployeeMyProfileReviewsCommentsAdapter(reviews)

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
            toAnotherActivity(it, EmployerMyProfileSettingsActivity::class.java)
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

    private fun createReviewsCommentsList(): List<LegacyReview> {
        return listOf(
            LegacyReview(R.drawable.sample_profile_photo, "Employer 1", 5, "Content 1"),
            LegacyReview(R.drawable.sample_profile_photo, "Employer 2", 4, "Content 2"),
            LegacyReview(R.drawable.sample_profile_photo, "Employer 3", 3, "Content 3"),
            LegacyReview(R.drawable.sample_profile_photo, "Employer 4", 2, "Content 4"),
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
//        finishAffinity()
    }
}