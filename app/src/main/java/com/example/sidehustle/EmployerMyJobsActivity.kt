package com.example.sidehustle

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.ViewPager
import com.example.sidehustle.databinding.ActivityEmployerMyJobsBinding
import com.google.android.material.tabs.TabLayout

class EmployerMyJobsActivity : AppCompatActivity() {

    lateinit var binding: ActivityEmployerMyJobsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_employer_my_jobs)

        var viewPager = binding.employerMyJobsViewPager
        var tabLayout = binding.employerMyJobsOngoingTabLayout

        val fragmentAdapter = EmployerMyJobsFragmentAdapter(supportFragmentManager)
        fragmentAdapter.addFragment(EmployerMyJobsOngoingFragment(), "Ongoing")
        fragmentAdapter.addFragment(EmployerMyJobsNegotiatingFragment(), "Negotiating")
        fragmentAdapter.addFragment(EmployerMyJobsHistoryFragment(), "History")

        viewPager.adapter = fragmentAdapter
        tabLayout.setupWithViewPager(viewPager)

        binding.employerMyJobsOngoingBottomNav.apply {
            selectedItemId = R.id.bottom_nav_my_jobs_button
            setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.bottom_nav_my_jobs_button -> false
                    R.id.bottom_nav_home_button -> {
                        finish()
                        startActivity(
                            Intent(
                                applicationContext,
                                EmployerHomeActivity::class.java
                            )
                        )
                        true
                    }

                    R.id.bottom_nav_my_profile_button -> {
                        finish()
                        startActivity(
                            Intent(
                                applicationContext,
                                EmployerMyProfileActivity::class.java
                            )
                        )
                        true
                    }

                    else -> false
                }
            }
        }
    }
}