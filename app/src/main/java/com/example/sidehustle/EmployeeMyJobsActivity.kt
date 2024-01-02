package com.example.sidehustle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.ViewPager
import com.example.sidehustle.databinding.ActivityEmployeeMyJobsBinding
import com.google.android.material.tabs.TabLayout


class EmployeeMyJobsActivity : AppCompatActivity() {

    lateinit var binding: ActivityEmployeeMyJobsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_employee_my_jobs)

        var viewPager = binding.employeeMyJobsViewPager as ViewPager
        var tabLayout = binding.employeeMyJobsOngoingTabLayout as TabLayout

        val fragmentAdapter = EmployeeMyJobsFragmentAdapter(supportFragmentManager)
        fragmentAdapter.addFragment(EmployeeOngoingFragment(),"Ongoing")
        fragmentAdapter.addFragment(EmployeeNegotiatingFragment(),"Negotiating")
        fragmentAdapter.addFragment(EmployeeHistoryFragment(),"History")

        viewPager.adapter = fragmentAdapter
        tabLayout.setupWithViewPager(viewPager)

    }



}