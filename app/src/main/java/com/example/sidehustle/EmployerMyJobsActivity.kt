package com.example.sidehustle

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
        binding = DataBindingUtil.setContentView(this,R.layout.activity_employer_my_jobs)

        var viewPager = binding.employerMyJobsViewPager as ViewPager
        var tabLayout = binding.employerMyJobsOngoingTabLayout as TabLayout

        val fragmentAdapter = EmployerMyJobsFragmentAdapter(supportFragmentManager)
        fragmentAdapter.addFragment(EmployerOngoingFragment(),"Ongoing")
        fragmentAdapter.addFragment(EmployerNegotiatingFragment(),"Negotiating")
        fragmentAdapter.addFragment(EmployerOngoingFragment(),"History")

        viewPager.adapter = fragmentAdapter
        tabLayout.setupWithViewPager(viewPager)
    }
}