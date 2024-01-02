package com.example.sidehustle

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.sidehustle.databinding.ActivityEmployeeMyJobsBinding


class EmployeeMyJobsActivity : AppCompatActivity() {

    lateinit var binding: ActivityEmployeeMyJobsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_employee_my_jobs)

        var viewPager = binding.employeeMyJobsViewPager
        var tabLayout = binding.employeeMyJobsOngoingTabLayout

        val fragmentAdapter = EmployeeMyJobsFragmentAdapter(supportFragmentManager)
        fragmentAdapter.addFragment(EmployeeMyJobsOngoingFragment(),"Ongoing")
        fragmentAdapter.addFragment(EmployeeMyJobsNegotiatingFragment(),"Negotiating")
        fragmentAdapter.addFragment(EmployeeMyJobsHistoryFragment(),"History")

        viewPager.adapter = fragmentAdapter
        tabLayout.setupWithViewPager(viewPager)

        setListeners()

    }

    private fun setListeners(){
        binding.employeeMyJobsOngoingBottomNav.apply {
            selectedItemId = R.id.bottom_nav_my_jobs_button
            setOnItemSelectedListener {
                when (it.itemId) {
                    R.id.bottom_nav_my_jobs_button -> false
                    R.id.bottom_nav_home_button -> {
                        finish()
                        startActivity(
                            Intent(
                                applicationContext,
                                EmployeeHomeActivity::class.java
                            )
                        )
                        true
                    }

                    R.id.bottom_nav_my_profile_button -> {
                        finish()
                        startActivity(
                            Intent(
                                applicationContext,
                                EmployeeMyProfileActivity::class.java
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