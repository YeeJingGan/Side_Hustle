package com.example.sidehustle

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sidehustle.databinding.FragmentEmployeeMyJobsNegotiatingBinding


class EmployeeMyJobsNegotiatingFragment : Fragment() {
    lateinit var binding: FragmentEmployeeMyJobsNegotiatingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_employee_my_jobs_negotiating,container,false)
        val adapter = EmployeeNegotiatingAdapter(populateJobs())

        binding.employeeNegotiatingRecyclerview.adapter = adapter
        binding.employeeNegotiatingRecyclerview.layoutManager = LinearLayoutManager(requireContext())
        return binding.root
    }

    private fun populateJobs(): List<JobEntity> {
        return listOf(
            JobEntity(
                1,
                1,
                "Job1",
                "JobState1",
                70,
                "2024-01-01",
                "2024-02-02",
                "10:00:00Z",
                "16:00:00Z",
                "jobDescription1"
            ),
            JobEntity(
                2,
                2,
                "Job2",
                "JobState2",
                80,
                "2024-01-01",
                "2024-02-02",
                "10:00:00Z",
                "16:00:00Z",
                "jobDescription2"
            ),
            JobEntity(
                3,
                3,
                "Job3",
                "JobState3",
                90,
                "2024-01-01",
                "2024-02-02",
                "10:00:00Z",
                "16:00:00Z",
                "jobDescription3"
            )
        )
    }


}