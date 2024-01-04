package com.example.sidehustle

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.viewpager2.widget.ViewPager2
import com.example.sidehustle.databinding.ActivityEmployerMyProfileBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.launch

class EmployerMyProfileActivity : AppCompatActivity() {
    lateinit var viewPager2: ViewPager2
    lateinit var adapter: EmployerMyProfileAdapter
    lateinit var binding : ActivityEmployerMyProfileBinding
    lateinit var viewModel:EmployerMyProfileViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_employer_my_profile)

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        ).get(EmployerMyProfileViewModel::class.java)


        viewPager2 = binding.myProfileViewpager2
        viewPager2.adapter = EmployerMyProfileAdapter(emptyList(),viewModel)

        viewModel.viewModelScope.launch {
            val ratings = viewModel.getRatingsForEmployer(1,"EMPLOYEE")
            adapter = EmployerMyProfileAdapter(ratings,viewModel)
            viewPager2.adapter = adapter

            if(ratings.size < 2){
                binding.myProfileNextReviewButton.visibility = View.INVISIBLE
            }

            val employer = viewModel.getEmployerByEmployerID(1)

            binding.employer = employer
        }

        viewModel.getAverageRatingByJobIDAndCommenter(1,"EMPLOYEE")

        viewModel.starCount.observe(this) {
            updateStarColors(it)
        }
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
                                EmployerMyJobsActivity::class.java
                            )
                        )
                        true
                    }

                    R.id.bottom_nav_home_button-> {
                        finish()
                        startActivity(
                            Intent(
                                applicationContext,
                                EmployerHomeActivity::class.java
                            )
                        )
                        true
                    }

                    else -> false
                }
            }
        }
    }
    private fun updateStarColors(starsCount: Int) {
        val stars = arrayOf(
            binding.myProfileStar1,
            binding.myProfileStar2,
            binding.myProfileStar3,
            binding.myProfileStar4,
            binding.myProfileStar5
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

    private fun setListeners() {

        findViewById<ImageButton>(R.id.my_profile_settings_icon).setOnClickListener {
            toAnotherActivity(it, EmployerMyProfileSettingsActivity::class.java)
        }


        val previousButton = binding.myProfilePreviousReviewButton
        val nextButton = binding.myProfileNextReviewButton
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

    private fun toAnotherActivity(view: View, destinationActivity: Class<*>) {
        view.context.also {
            val intent = Intent(it, destinationActivity)
            it.startActivity(intent)
        }
    }

}